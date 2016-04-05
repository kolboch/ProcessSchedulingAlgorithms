package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class OutputToFile {
	
	public void dataToFile(String pathname , String output){
		
		File f = new File(pathname);
		try{
		FileWriter writer = new FileWriter(f , true);
		
		writer.write(output);
		writer.write(String.format("%n", ""));
		writer.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[]args){
		String pathname = "D:\\javaeclipseprog\\Tutoriale\\TestDocumentOutput.txt";
		
		OutputToFile of = new OutputToFile();
		of.dataToFile(pathname, "Dzien dobry");
	}
}
