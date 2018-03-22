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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HC {
	public static void main(String args[])
		throws IOException, ClassNotFoundException, InterruptedException{
		
		if(args.length != 5){
			System.err.println("Usage: HC <inputAttrPath> <inputDataPath> <outputPath> <NumOfInstances> <NumOfPartitions>");
			System.exit(-1);
		}
		
		//Setup the configuration object
		Configuration conf = new Configuration();
		
		//Read from local attributes file
		FileSystem fs = FileSystem.get(conf);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(new Path(args[0]))));
		String attributes = "";
		String line = "";
		while((line = br.readLine()) != null){
			attributes = attributes + line + "\n";
		}
		
		//Calculate and pass mean of each attribute to mapper
		String attrMean = attributesMean.calculate(attributes, args[1], conf);
		
		conf.set("attrMean", attrMean);
		
		String numOfInstances  = args[3];
		String numOfPartitions = args[4];
		
		//Passing variables configs to mappers and reducers
		conf.set("attributes",attributes);
		conf.set("numOfInstances", numOfInstances);
		conf.set("numOfPartitions", numOfPartitions);
		
		Job job = new Job(conf);
		job.setJarByClass(HC.class);
		job.setJobName("HC distributed");
		
		FileInputFormat.addInputPath(job,new Path(args[1]));
		FileOutputFormat.setOutputPath(job,new Path(args[2]));
		
		job.setMapperClass(HCMapper.class);
		job.setReducerClass(HCexactReducer.class);
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
