package HCdist;

import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.conf.Configuration;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;
import weka.clusterers.HierarchicalClusterer;

public class HCapproxReducer 
	extends Reducer<LongWritable, Text, LongWritable, Text>{
	Double totalDistance = new Double(0.0);
	public void reduce(LongWritable mapOutKey, Iterable<Text> mapOutVal, Context context)
		throws IOException, InterruptedException{
		
//		if(!mapOutKey.equals(new LongWritable(1))){
//			TreeMap<Double, Integer> tmap = new TreeMap<Double, Integer>();
//			
//			//Building the Arff string		
//			//Step 1 - Fetch the attribute from config
//			Configuration config = context.getConfiguration();
//			int numberOfInstances = Integer.parseInt(config.get("numOfInstances"));
//			
//			String attributes = config.get("attributes");
//			
//			//Step 2 - Append attributes and build the Arff data string
//			String arffString = attributes;
//			
//			//Create a string array
//			StringBuilder sb = new StringBuilder();
//			
//			//Total distance this reducer has to compute
//			Double totalDist = 0.0;
//			String[] dataArr = new String[1]; 
//					
//			for(Text textInstance : mapOutVal){
//				
//				// Finding totalDistance and storing each distance
//				String[] temp   = textInstance.toString().split(",");
//				String strDist  = temp[temp.length - 1];
//				Double dist     = Double.parseDouble(strDist);
//				totalDist      += dist;
//				String data     = textInstance.toString().substring(0,textInstance.toString().lastIndexOf(","));
//				arffString      = arffString + "\n" + data;
//			}
//			
//			//Converting String to InputStream.
//			InputStream inputStream = new ByteArrayInputStream(arffString.getBytes(StandardCharsets.UTF_8));
//			
//			//Converting InputStream to ARFF format (DataInstances)
//			ArffLoader loader = new ArffLoader();
//			loader.setSource(inputStream);
//			Instances dataInstances = loader.getDataSet();
//			
//			//This is done to set the index on a particular attribute which contains not NaN values
//			//Manually you could select any attribute you want as you index. For the program to find put -1.
//			dataInstances.setClassIndex(0);
//			
//			//Build Hierarchical Cluster - Parameters("DistanceFunction","NumberOfClusters")
//			String newick = "";
//			HierarchicalClusterer HC = new HierarchicalClusterer("Euclidean", 1);
//			try{
//				HC.buildClusterer(dataInstances);
//				newick = HC.graph();
//			}catch(Exception e){
//				System.err.println(e);
//			}
//			context.write(mapOutKey,new Text(newick));
//		}
//		else{
//			for(Text t : mapOutVal){
//				context.write(mapOutKey, new Text("Payload: " + t.toString()));
//			}
//		}
		
//		
		//Write newick to output file
		
		Configuration config = context.getConfiguration();
		
		float numberOfInstances    = Float.parseFloat(config.get("numOfInstances"));
		float numOfPartitions      = Float.parseFloat(config.get("numOfPartitions"));
		float samplingPercentage   = Float.parseFloat(config.get("samplingPercentage"));
		String attributes = config.get("attributes");
		
		TreeMap<Double, ArrayList<Integer>> tmap = new TreeMap<Double,  ArrayList<Integer>>();
		HashMap<Integer,Integer> indexMap = new HashMap<Integer, Integer>();
		indexMap.put(-1, 0);
		
		StringBuilder sb = new StringBuilder();
		
		int instanceNumberAsValue = -1;
		String arffString = "";//No attributes attached!
		
		for(Text textInstance : mapOutVal){
			
			ArrayList<Integer> listOfIndex = new ArrayList<Integer>();
			instanceNumberAsValue = instanceNumberAsValue + 1;
			
			// If the key is 1. Only fetch the payload from mapper: Which in our case is sum.
			if(mapOutKey.equals(new LongWritable(1))){
				totalDistance = Double.parseDouble(textInstance.toString());
				context.write(mapOutKey, new Text("Payload from Mapper: TotalDistance: " + totalDistance));
			}else{
				
				//Find the distance of each instance from mean.
				String[] temp   = textInstance.toString().split(",");
				String strDist  = temp[temp.length - 1];
				Double instanceDistance = Double.parseDouble(strDist);
				
				String data     = textInstance.toString().substring(0,textInstance.toString().lastIndexOf(","));
				arffString      = arffString + "\n" + data;
				
				sb.append("\n" + data);
				
				//Finding the indexoflinebreak
				int indexOfLineBreak = arffString.length();
				indexMap.put(instanceNumberAsValue, indexOfLineBreak);
				
				//Find the probability of instance.
				Double term1 = 0.5 * (1/numberOfInstances);
				Double term2 = 0.5 * (instanceDistance)/(totalDistance);
				Double instanceProbability = term1 + term2;
				
				if(tmap.containsKey(instanceProbability)){
					ArrayList<Integer> tempArrayList = tmap.get(instanceProbability);
					tempArrayList.add(instanceNumberAsValue);
					tmap.put(instanceProbability, tempArrayList);
				}else{
					listOfIndex.add(instanceNumberAsValue);
					tmap.put(instanceProbability, listOfIndex);
				}
			}
		}
		
		// If the key is 1. Only fetch the payload from mapper: Which in our case is sum.
		if(!mapOutKey.equals(new LongWritable(1))){
			int breakCounter = 0;
			int instancesInPartition = (int) (numberOfInstances/numOfPartitions);
			float remainingPercentage = (float) (1.0 - (samplingPercentage/100.0));
			int breakHere = (int) (remainingPercentage * instancesInPartition); 
			
			int sumOfDifference = 0;
			outerLoop:
			for(Double d : tmap.keySet()){
				for(Integer i : tmap.get(d)){
					
					sumOfDifference += (indexMap.get(i) - indexMap.get(i-1));
					
					int start = indexMap.get(i - 1);
					int end   = indexMap.get(i);
					
					String dummyRepeat = new String(new char[end - start]).replace("\0", "~");
					
					sb.replace(start, end, dummyRepeat);
					
					context.write(mapOutKey, new Text("Start: " + start + "\n End: " + end + "\n"));
					breakCounter = breakCounter + 1;
					
					//context.write(mapOutKey, new Text("Key: " + i.toString() + " , Value: " + d + "BreakCounter: " + breakCounter + " , " + "BreakHere: " + breakHere));
					if(breakCounter == breakHere){
						break outerLoop; 
					}
				}
			}
			String approxArff = sb.toString().replace("~", "");
			context.write(mapOutKey, new Text("\nArffString: \n" + arffString + "\n\n\n\n ApproxArffString: \n" + approxArff));
		}
	}	
}