package directory.implementation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import directory.model.DirectoryModel;

public class DirectoryStructureReader {
	String csvFilepath = "./directory-structure.csv";
	BufferedReader br = null;
	String line = "";
	String cvsDelimter = ";";
	String name, type, classification; 
	int id, parentId, size, checksum;
	DirectoryModel dm ;
	
	public DirectoryModel readCSVLine(String[] line){
		try{
			if(line[0]!=null || line[0]!= ""){
				id = Integer.parseInt(line[0]);
			}else{
				id = 000;
			}
		}catch(Exception e){
			id = 000;
		}
		try{
			if(line[1]!=null && line[1].length()>0){
				parentId = Integer.parseInt(line[1]);
			}else{
				parentId = 000;
			}
		}catch(Exception e){
			parentId = 000;
		}
		try{
			if(line[2]!=null && line[2].length()>0){
				name =  line[2];
			}else{
				name = null;
			}
		}catch(Exception e){
			name = null;
		}
		try{
			if(line[3]!=null && line[3].length()>0){
				type = line[3];
			}else{
				type = null;
			}
		}catch(Exception e){
			type = null;
		}
		try{
			if(line[4]!=null && line[4].length()>0){
				size =  Integer.parseInt(line[4]);
			}else{
				size = 000;
			}
		}catch(Exception e){
			size = 000;
		}
		try{
			if(line[5]!=null && line[5].length()>0){
				classification = line[5];
			}else{
				classification = null;
			}
		}catch(Exception e){
			classification = null;
		}
		try{
			if(line[6]!=null && line[6].length()>0){
				checksum = Integer.parseInt(line[6]);
			}else{
				checksum = 000;
			}
		}catch(Exception e){
			checksum = 000;
		}
		dm = new DirectoryModel(id,parentId, name,type,size,classification,checksum);
		return dm;
	}
	public void closeResource(){
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public String[] readCSVFile(){
		String[] lineArrays = null;
		try {
			if(br!=null){}else{
				br = new BufferedReader(new FileReader(csvFilepath));
			}
			if((line = br.readLine()) != null) {
				while(line.startsWith("#")){
					line = br.readLine();
				}
				lineArrays = line.split(cvsDelimter);				
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lineArrays;
		
	}
	
	
	public int readCSVFileDirectoryCount(ArrayList<DirectoryModel> objs) throws IOException{
		int lineCount = 0;
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			DirectoryModel dm = (DirectoryModel)itr.next();
			if(dm.type.equalsIgnoreCase("directory")){
				lineCount = lineCount + 1;
			}
		}
		return lineCount;
		
	}
}
