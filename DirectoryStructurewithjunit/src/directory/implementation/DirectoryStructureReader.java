package directory.implementation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DirectoryStructureReader {
	String csvFilepath = "./directory-structure.csv";
	BufferedReader br = null;
	String line = "";
	String cvsDelimter = ";";
	String name, type, classification; 
	int id, parentId, size, checksum;
	
	public void readCSVLine(String[] line){
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
	
	public int readCSVFileLineCount() throws IOException{
		int lineCount = 0;
		BufferedReader br2 = null;
		String line2;
		//String[] lineArrays = null;
		try {
			if(br2!=null){}else{
				br2 = new BufferedReader(new FileReader(csvFilepath));
			}
			while((line2 = br2.readLine()) != null) {
				while(line2.startsWith("#")){
					line2 = br2.readLine();
				}
				//lineArrays = line.split(cvsDelimter);
				lineCount = lineCount+1;
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			br2.close();
		}
		return lineCount;
		
	}
	
	public int readCSVFileDirectoryCount() throws IOException{
		int lineCount = 0;
		BufferedReader br2 = null;
		String line2;
		String[] lineArrays = null;
		try {
			if(br2!=null){}else{
				br2 = new BufferedReader(new FileReader(csvFilepath));
			}
			while((line2 = br2.readLine()) != null) {
				while(line2.startsWith("#")){
					line2 = br2.readLine();
				}
				try{
					lineArrays = line2.split("directory");
					if(lineArrays[1].length()>=1){
						lineCount = lineCount+1;
					}
				}catch(Exception e){
					lineCount = lineCount;
				}
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			br2.close();
		}
		return lineCount;
		
	}
}
