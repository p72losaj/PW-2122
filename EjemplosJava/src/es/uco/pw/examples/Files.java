package es.uco.pw.examples;

import java.io.*;

/**
 * A program to manage files in Java.
 * @author Aurora Ramirez
 * @author Jose Raul Romero
 * */

public class Files {

	public static void main(String[] args) {
		
		String inputFileName = args[0];
		String outputFileName = args[1];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(inputFileName)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFileName)));
			
			int counter = 0;
			String line;
			while((line = reader.readLine()) != null) {
				if(counter % 2 == 0) {
					writer.write(line + "\n");
				}
				counter++;
			}
			reader.close();
			writer.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
