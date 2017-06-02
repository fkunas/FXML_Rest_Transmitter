package de.fikun.dto;

import java.io.File;

public class SerializedRessource {
	static File currentDir = new File(".");
	public static void main(String[] args){
		System.out.println(currentDir.getAbsolutePath());
	}

}
