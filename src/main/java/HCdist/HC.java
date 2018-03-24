package HCdist;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.InputStreamReader;

public class HC {
	public static void main(String args[])
		throws IOException, ClassNotFoundException, InterruptedException{
		
		System.out.println("Code Started. . ");
		
		if(args.length != 6){
			System.err.println("Usage: HC <inputAttrPath> <inputDataPath> <outputPath> <NumOfInstances> <NumOfPartitions> <samplingPercentage>");
			System.exit(-1);
		}
		
		//Setup the configuration object
		Configuration conf = new Configuration();
		
		System.out.println("Reading Attributes File . .");
		
		//Read from local attributes file
		FileSystem fs = FileSystem.get(conf);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(new Path(args[0]))));
		String attributes = "";
		String line = "";
		while((line = br.readLine()) != null){
			attributes = attributes + line + "\n";
		}
		
		System.out.println("\r Read Attributes. . ");
		
		//Calculate and pass mean of each attribute to mapper
		String attrMean = attributesMean.calculate(attributes, args[1], conf);
		
		conf.set("attrMean", attrMean);
		
		String numOfInstances  = args[3];
		String numOfPartitions = args[4];
		String samplingPercentage = args[5];
		
		//Passing variables configs to mappers and reducers
		conf.set("attributes",attributes);
		conf.set("numOfInstances", numOfInstances);
		conf.set("numOfPartitions", numOfPartitions);
		conf.set("samplingPercentage", samplingPercentage);
		
		System.out.println("Starting Job . . ");
		
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(HC.class);
		job.setJobName("HC distributed");
		
		System.out.println("\r Job Started \n Now starting Mapper and reducer. . ");
		FileInputFormat.addInputPath(job,new Path(args[1]));
		FileOutputFormat.setOutputPath(job,new Path(args[2]));
		
		job.setMapperClass(HCMapper.class);
		job.setReducerClass(HCapproxReducer.class);
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		System.out.println("Started and completed . . ");
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
