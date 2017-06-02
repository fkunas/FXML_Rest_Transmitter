package de.fikun.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class SerializeableRessourceDir implements Serializable {
	private ArrayList<String> filepathList;
	private ArrayList<byte[]> byteArrayList;	
	
	public SerializeableRessourceDir(ArrayList<String> filepathList, ArrayList<byte[]> byteArrayList){
		this.filepathList = filepathList;
		this.byteArrayList = byteArrayList;
		
	}
	public ArrayList<String> getFilepathList() {
		return filepathList;
	}

	public void setFilepathList(ArrayList<String> filepathList) {
		this.filepathList = filepathList;
	}

	public ArrayList<byte[]> getByteArrayList() {
		return byteArrayList;
	}

	public void setByteArrayList(ArrayList<byte[]> byteArrayList) {
		this.byteArrayList = byteArrayList;
	}
	
}
