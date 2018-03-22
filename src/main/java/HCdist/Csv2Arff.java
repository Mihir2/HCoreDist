package HCdist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class Csv2Arff {
	public static void main(String[] args) throws IOException{
		convert("TrainingData\\Diabetic.csv");
	}
	
	public static void convert(String pathToCsv) throws IOException{
		String encoding = "UTF-8";
		BufferedReader reader = null;
		BufferedWriter writerAttr = null;
		BufferedWriter writerData = null;
		StringBuilder sb = new StringBuilder();
		
		// Read the CSV file
		System.out.println("Reading   : " + pathToCsv);
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathToCsv), encoding));
	    for (String line; (line = reader.readLine()) != null;) {
	    	sb.append(line+"\n");
	    }
		reader.close();
		
		//Convert the CSV file into ARFF
		System.out.println("Converting: " + pathToCsv);
		InputStream inputStream = new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
		CSVLoader loader = new CSVLoader();
		loader.setSource(inputStream);
		Instances data = loader.getDataSet();
		String strData = data.toString();
		
		// Write the ARFF file as .attr and .data
		System.out.println("Writing to:" + pathToCsv.substring(0,pathToCsv.length()-3)+"attr and data");
		writerAttr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToCsv.substring(0,pathToCsv.length()-4)+"_attributes.arff"), encoding));
		writerAttr.write(strData.substring(0,(strData.indexOf("@data") + 6)));
		writerAttr.close();
		
		writerData = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToCsv.substring(0,pathToCsv.length()-4)+"_instances.arff"), encoding));
		writerData.write(strData.substring((strData.indexOf("@data") + 6),strData.length()));
		writerData.close();
		
		System.out.println(pathToCsv.substring(0,pathToCsv.length()-3)+"attr.arff and instances.arff Created!");
		return;
	}
}
