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
import java.util.HashMap;
import java.util.TreeMap;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.clusterers.HierarchicalClusterer;

public class HCReducer 
	extends Reducer<LongWritable, Text, LongWritable, Text>{
	Double totalDistance = new Double(0.0);
	public void reduce(LongWritable mapOutKey, Iterable<Text> mapOutVal, Context context)
		throws IOException, InterruptedException{
		
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
		
		String exactArffData = "";//No attributes attached!
		
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
				exactArffData   = exactArffData + "\n" + data;
				
				sb.append("\n" + data);
				
				//Finding the indexoflinebreak
				int indexOfLineBreak = exactArffData.length();
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
			
			outerLoop:
			for(Double d : tmap.keySet()){
				for(Integer i : tmap.get(d)){
					
					int start = indexMap.get(i - 1);
					int end   = indexMap.get(i);
					
					String dummyRepeat = new String(new char[end - start]).replace("\0", "~");
					
					sb.replace(start, end, dummyRepeat);
					
					breakCounter = breakCounter + 1;
					
					if(breakCounter == breakHere){
						break outerLoop; 
					}
				}
			}
			
			String approxArffData = sb.toString().replace("~", "");
			String approxArff = attributes + approxArffData;
			
			String approxNewick = hierarchicalCLustering(approxArff);
			
			String exactArff = attributes + exactArffData;
			String exactNewick = hierarchicalCLustering(exactArff);
			
			context.write(mapOutKey, new Text("\nExact-Arff-String: " + exactArffData + 
											  "\n\nExact Newick: " + exactNewick + 
											  "\n\nApprox-Arff-String:" + approxArffData + 
											  "\n\nApprox Newick: " + approxNewick));
		}
	}
	
	public static String hierarchicalCLustering(String arffString) throws IOException{
		
		//Converting String to InputStream.
		InputStream inputStream = new ByteArrayInputStream(arffString.getBytes(StandardCharsets.UTF_8));
		
		//Converting InputStream to ARFF format (DataInstances)
		ArffLoader loader = new ArffLoader();
		loader.setSource(inputStream);
		Instances dataInstances = loader.getDataSet();
		
		//This is done to set the index on a particular attribute which contains not NaN values
		//Manually you could select any attribute you want as you index. For the program to find put -1.
		dataInstances.setClassIndex(0);
		
		//Build Hierarchical Cluster - Parameters("DistanceFunction","NumberOfClusters")
		String newick = "";
		HierarchicalClusterer HC = new HierarchicalClusterer("Euclidean", 1);
		try{
			HC.buildClusterer(dataInstances);
			newick = HC.graph();
		}catch(Exception e){
			System.err.println(e);
		}
		
		return newick;
	}
}