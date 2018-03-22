package HCdist;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;

import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.CSVLoader;

public class coreset {
	public static void main(String[] args) throws IOException{
		String originalCsvData = new String(Files.readAllBytes(Paths.get("TrainingData\\diabetic_data.csv")), StandardCharsets.UTF_8);
		InputStream inputStream = new ByteArrayInputStream(originalCsvData.getBytes(StandardCharsets.UTF_8));
		
		CSVLoader loader = new CSVLoader();
		loader.setSource(inputStream);
		
		Instances originalInstancesData = loader.getDataSet();
		BufferedWriter writer = new BufferedWriter(new FileWriter("diabetic_data.arff"));
		writer.write(originalInstancesData.toString());
		
//		importanceSampler(originalInstancesData);
	}
	
	public static Instances importanceSampler(Instances originalInstancesData){
		Instances approximateInstancesData = null;
		
		//Finding Mean of each attribute
//		for(Instance i : originalInstancesData){
//			
//		}
		for(int i = 0; i < originalInstancesData.numAttributes(); i++){
			
			// Attribute Type
			// 0 -> Numeric: This type of attribute represents a floating-point number.
			// 1 -> Nominal: This type of attribute represents a fixed set of nominal values.
			// 2 -> String : This type of attribute represents a dynamically expanding set of nominal values.
			
			if(!originalInstancesData.attribute(i).isNumeric()){
				System.out.println(originalInstancesData.attribute(i));
				System.exit(0);
			}
		}
		return approximateInstancesData;
	}
}
