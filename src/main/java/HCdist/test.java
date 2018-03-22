package HCdist;

import weka.core.converters.CSVLoader;
import java.io.File;
import weka.core.Instances;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.ByteArrayInputStream;
import weka.clusterers.HierarchicalClusterer;;

public class test {
	public static void main(String args[])
		throws Exception{
		
		String someString = new String(Files.readAllBytes(Paths.get("TrainingData\\smallDiabetic.csv")), StandardCharsets.UTF_8);
		
		CSVLoader loader = new CSVLoader();
		InputStream inputStream = new ByteArrayInputStream(someString.getBytes());
		
		loader.setSource(inputStream);
		
		Instances data = loader.getDataSet();
		
		//This is done to set the index on a particular attribute which contains not NaN values
		data.setClassIndex(0);
		
		HierarchicalClusterer hc = new HierarchicalClusterer("Euclidean",1);
		hc.buildClusterer(data);
		System.out.println(hc.graph());
		
	}
}
