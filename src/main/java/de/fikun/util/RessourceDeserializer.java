package de.fikun.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import de.fikun.dto.SerializeableRessourceDir;

public class RessourceDeserializer {
	static String prepFile = "/Users/FiKun/Documents/workspace_FXMLrest/fxml_rest_transmitter/prep/b64.txt";
	static String targetFilepath = "/Users/FiKun/Documents/workspace_FXMLrest/fxml_rest_transmitter/dest/";
	static SerializeableRessourceDir resDir;


	public static void main(String args[]) {
		String b64;
		try {
			b64 = new String(Files.readAllBytes(Paths.get(prepFile)));
			resDir = (SerializeableRessourceDir) fromString(b64);
			regenerateFiles(resDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Object fromString(String s) throws IOException, ClassNotFoundException {
		byte[] data = Base64.getDecoder().decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;

	}
	
	private static void regenerateFiles(SerializeableRessourceDir resDir){
		int i = 0;
		for (String s : resDir.getFilenameList()){
			try {
			FileOutputStream fos;
				fos = new FileOutputStream(targetFilepath + s);
			fos.write(resDir.getByteArrayList().get(i));
			fos.close();
			i++;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
