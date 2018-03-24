package HCdist;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.conf.Configuration;

public class HCMapper 
	extends Mapper<LongWritable, Text, LongWritable, Text> {
	
	int counter = 0;              
	int numberOfPartitions = 2;  //Determines How many partitions you want!
	double totalSum = 0.0;
	
	public void map(LongWritable inputKey, Text inputValue, Context context)
		throws IOException, InterruptedException{
		
		Configuration conf  = context.getConfiguration();
		
		int numOfInstances  = Integer.parseInt(conf.get("numOfInstances"));
		int numOfPartitions = Integer.parseInt(conf.get("numOfPartitions"));
		
		//Determines when to break to next partition..
		int breakCounter = numOfInstances/numOfPartitions;  
		
		counter = counter + 1;
		
		if(counter % breakCounter == 0 && numberOfPartitions < numOfPartitions + 1){
			numberOfPartitions++;
		}
		LongWritable outputKey = new LongWritable(numberOfPartitions);
		
		String attributes   = conf.get("attributes");
		String attrMean     = conf.get("attrMean");
		String currInstance = inputValue.toString();
		String tempArffData = attributes + "\n" + attrMean + "\n" + currInstance;
		
		InputStream inputStream = new ByteArrayInputStream(tempArffData.getBytes(StandardCharsets.UTF_8));
		
		ArffLoader loader = new ArffLoader();
		loader.setSource(inputStream);
		
		Instances instances = loader.getDataSet();
		DistanceFunction m_disance = new EuclideanDistance();
		m_disance.setInstances(instances);
		double distanceWithMean = m_disance.distance(instances.get(0), instances.get(1));
		double squaredDIstanceWithMean = distanceWithMean * distanceWithMean;
		String out = inputValue.toString() + "," + squaredDIstanceWithMean;
		
		totalSum += squaredDIstanceWithMean;
		
		if(counter >= numOfInstances){
			context.write(new LongWritable(1), new Text(totalSum + ""));
		}
		context.write(outputKey, new Text(out));
	}
}
