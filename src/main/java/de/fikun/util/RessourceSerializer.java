package de.fikun.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import de.fikun.dto.SerializeableRessourceDir;

/**
 * 
 * @author FiKun
 *
 */
public class RessourceSerializer {
	
	public static File testFile = new File("/Users/FiKun/Documents/workspace_FXMLrest/file_rest_transmitter/source");
	//public static File sourceDir;
	private static String sourcePath;
	private static String prepFile = "/Users/FiKun/Documents/workspace_FXMLrest/file_rest_transmitter/prep/b64.txt";
	private static ArrayList<String> filepathArray = new ArrayList<String>();
	private static ArrayList<String> filenameArray = new ArrayList<String>();
	private static ArrayList<byte[]> byteArrayList = new ArrayList<byte[]>();

	public static void main(String[] args) {
		serializeFileToString(testFile);
		for (String s : filepathArray){
			System.out.println(s);
		}
		for (String s : filenameArray){
			System.out.println(s);
		}
	}
	
	private static String serializeFileToString(File source){
		String b64 = "";
		sourcePath = source.getAbsolutePath() + "/";

		fetchSourceContent(source);
		readFilesToByte(filepathArray);
		b64 = serializeObjectToString(new SerializeableRessourceDir(filepathArray, filenameArray, byteArrayList));
		
		writeStringToFile(b64, prepFile);
		
		
		return b64;
	}
	
	private static void fetchSourceContent(File sourceDir) {
		for (File curFile : sourceDir.listFiles()) {
			
			if (curFile.isDirectory()) {
				fetchSourceContent(curFile);
			}
			if (curFile.isFile()) {
				filepathArray.add(curFile.getAbsolutePath());
				filenameArray.add(curFile.getAbsolutePath().replace(sourcePath, ""));
			}
		}

	}
	
	public static void readFilesToByte(ArrayList<String> filePathArray){
		for (String s : filePathArray){
			try {
			Path path = Paths.get(s);
				byte[] data = Files.readAllBytes(path);
				byteArrayList.add(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private static String serializeObjectToString(SerializeableRessourceDir resDir) {
		try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
		oos = new ObjectOutputStream( baos );
        oos.writeObject(resDir);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "WRONG";
		}
		
	}
	
	
	private static void writeStringToFile(String b64, String prepFile) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(prepFile))) {
			bw.write(b64);
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		}
		
	}

}
