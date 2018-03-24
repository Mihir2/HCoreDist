package HCdist;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class attributesMean {
	public static void main(String args[]) throws IOException{
	}
	
	public static String calculate(String attributes, String  dataPath,Configuration conf) throws IOException{
		
			
		FileSystem fs = FileSystem.get(conf);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(new Path(dataPath))));
		String data = "";
		String line = "";
		while((line = br.readLine()) != null){
			data = data + line + "\n";
		}
		
		String ArffData = attributes + data;
		InputStream inputStream = new ByteArrayInputStream(ArffData.getBytes(StandardCharsets.UTF_8));
		
		ArffLoader loader = new ArffLoader();
		loader.setSource(inputStream);
		
		Instances instances = loader.getDataSet();
		
		//This code would induce error for date type attribute! - Known Bug.
		//Run over each attribute column. Get the names of the attributes. Find the mean.
		
		double attrMean[] = new double[instances.numAttributes()];
		
		for(int attrIndex = 0; attrIndex < instances.numAttributes(); attrIndex++){
			
			double sum = 0;    // Sum up entire column of attribute
			int count = 0;     // Count for all except NaN
			
			//Load an entire feature col into a double array
			double entireFeatureCol[] = instances.attributeToDoubleArray(attrIndex);
			
			for(int i = 0; i < entireFeatureCol.length; i++){
				//Convert to derived datatype for using isNaN condition
				Double eachElement = new Double(entireFeatureCol[i]);
				if(!eachElement.isNaN()){
					count++;  //Count shows all instances of the particular feature contributing to mean
					sum += entireFeatureCol[i];    
				}else{
					//Do Nothing.
				}
			}
			
			if(count == 0){
				// This caters to the condition when entire attribute is NaN
				attrMean[attrIndex] = Double.NaN;
			}else if(instances.attribute(attrIndex).isNumeric()){
				// Simply find the mean of the numeric/real attribute column
				attrMean[attrIndex] = sum/count;
			}else{
				// Round up for nominal and string type
				attrMean[attrIndex] = Math.round(sum/count);
			}
		}
		
		instances.add(new DenseInstance(1.0,attrMean));
		String meanInstance = instances.get(instances.size() - 1).toString();
		
		return meanInstance;
	}
}
