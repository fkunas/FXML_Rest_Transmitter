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

public class RessourceSerializer {
	static File sourceDir = new File("/Users/FiKun/Documents/workspace_FXMLrest/fxml_rest_transmitter/source");
	static String prepFile = "/Users/FiKun/Documents/workspace_FXMLrest/fxml_rest_transmitter/prep/b64.txt";
	static ArrayList<String> filepathArray = new ArrayList<String>();
	static ArrayList<String> filenameArray = new ArrayList<String>();
	static ArrayList<byte[]> byteArrayList = new ArrayList<byte[]>();

	public static void main(String[] args) {
		listFolderContent(sourceDir);
		readFilesToByte(filepathArray);
		String b64 = serializeObjectToString(new SerializeableRessourceDir(filepathArray, filenameArray, byteArrayList));
		writeStringToFile(b64, prepFile);
		
		
	}

	private static void listFolderContent(File sourceFolder) {
		for (String s : sourceFolder.list()) {
			File curFile = new File(s);
			File curFile2 = new File(sourceFolder.getAbsolutePath() + "/" + curFile.getName());
			
			if (curFile2.isDirectory()) {
				listFolderContent(curFile2);
			}
			if (curFile2.isFile()) {
				filepathArray.add(curFile2.getAbsolutePath());
				filenameArray.add(curFile2.getName());
			}
		}

	}
	
	private static void readFilesToByte(ArrayList<String> filePathArray){
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
			e.printStackTrace();
		}
		
	}

}
