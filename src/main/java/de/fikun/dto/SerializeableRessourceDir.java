package de.fikun.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class SerializeableRessourceDir implements Serializable {
	private static final long serialVersionUID = 5572659389522556159L;
	private ArrayList<String> filepathList;
	private ArrayList<String> filenameList;
	private ArrayList<byte[]> byteArrayList;	
	
	public SerializeableRessourceDir(ArrayList<String> filepathList, ArrayList<String> filenameList, ArrayList<byte[]> byteArrayList){
		this.filepathList = filepathList;
		this.filenameList = filenameList;
		this.byteArrayList = byteArrayList;
		
	}

	public ArrayList<String> getFilepathList() {
		return filepathList;
	}
	
	public ArrayList<String> getFilenameList() {
		return filenameList;
	}
	
	public ArrayList<byte[]> getByteArrayList() {
		return byteArrayList;
	}

	public void setFilepathList(ArrayList<String> filepathList) {
		this.filepathList = filepathList;
	}
	
	public void setFilenameList(ArrayList<String> filenameList) {
		this.filenameList = filenameList;
	}

	public void setByteArrayList(ArrayList<byte[]> byteArrayList) {
		this.byteArrayList = byteArrayList;
	}
	
}
