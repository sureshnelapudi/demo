package directory.implementation;

import java.util.ArrayList;
import java.util.Iterator;

import directory.model.DirectoryModel;

public class DirectoryStructureImplementation {
	DirectoryModel dm = new DirectoryModel();
	DirectoryStructureReader dsr ;
	String[] line = null;
	String printedLine = "";
	int compareID=0;
	ArrayList<Integer> leftOverCompareIds = new ArrayList<Integer>();
			
	public void printTree(){
		dsr = new DirectoryStructureReader();
		int totalLines = 0;
		try{
			totalLines = dsr.readCSVFileLineCount();
		}catch(Exception e){
			totalLines = 0;
		}
		
			while((line = dsr.readCSVFile())!= null){
				dsr.readCSVLine(line);
				//if it is directory calculating size
				int caluculatedSize = 0;
				if(dsr.type != null && dsr.type.equalsIgnoreCase("directory")){
					DirectoryStructureReader dsr1 = new DirectoryStructureReader();
					String[] line1;
					while((line1 = dsr1.readCSVFile())!=null){
						dsr1.readCSVLine(line1);
						if(dsr1.parentId!=000 && dsr1.parentId==dsr.id){
							caluculatedSize = caluculatedSize + dsr1.size;
							//System.out.println("!!!!!!"+calculateSizeOfFolder(dsr.id));
						}
					}
					dsr1.closeResource();
					dsr.size = calculateSizeOfFolder(dsr.id)-10;
				}
				
				dm.setDirectoryModel(dsr.id, dsr.parentId, dsr.name, dsr.type, dsr.size, dsr.classification, dsr.checksum);
				if(dsr.parentId==0 ){
					dm.printTreeNode();
					System.out.print("");
					compareID = dsr.id;
				}
			}
		//}
			int calNumber = 0;
			try{
				calNumber = dsr.readCSVFileDirectoryCount()-1;
			}catch(Exception e){
				calNumber = 0;
			}
			
			while(calNumber > 0){
				sortTree(compareID,totalLines);
				calNumber--;
			}
			Iterator<Integer> itr = leftOverCompareIds.iterator();
			while(itr.hasNext()){
				int compareNewId = (Integer)itr.next();
				printDirectory(compareNewId,totalLines);
			}
			line = null;
			dsr.closeResource();
	}
	
	public void printDirectory(int ID, int totalLines){
		DirectoryStructureReader dsr2 = new DirectoryStructureReader() ;
		int icomp = 0,caluculatedSize=0;
		while(icomp <= totalLines){
			String[] line3=null;
			while((line3 = dsr2.readCSVFile())!= null){
				dsr2.readCSVLine(line3);
				if(dsr2.parentId!=000 && dsr2.id==ID){
					DirectoryStructureReader dsr3 = new DirectoryStructureReader();
					String[] line1;
					while((line1 = dsr3.readCSVFile())!=null){
						dsr3.readCSVLine(line1);
						if(dsr3.parentId!=000 && dsr3.parentId==dsr2.id){
							caluculatedSize = caluculatedSize + dsr3.size;
						}
					}
					dsr3.closeResource();
					dsr2.size = calculateSizeOfFolder(dsr2.id);
					dm.setDirectoryModel(dsr2.id, dsr2.parentId, dsr2.name, dsr2.type, dsr2.size, dsr2.classification, dsr2.checksum);
					System.out.print(" ");
					dm.printTreeNode();
					System.out.print("  ");
					
				}
			}
			icomp++;
		}
		dsr2.closeResource();
		sortTree(ID,totalLines);
	}
	
	public void sortTree(int compareIDValue, int totalLines){
		int folderVisit = 0;
		int icomp = 0,caluculatedSize = 0;
		DirectoryStructureReader dsr2 = new DirectoryStructureReader() ;
		while(icomp <= totalLines){
			String[] line3 =null;
			while((line3 = dsr2.readCSVFile())!= null){
				dsr2.readCSVLine(line3);
				if(dsr2.parentId!=000 && dsr2.parentId==compareIDValue){
					//caluculatedSize = caluculatedSize+dsr1.size;
					if(dsr2.type.equalsIgnoreCase("file")){
						dm.setDirectoryModel(dsr2.id, dsr2.parentId, dsr2.name, dsr2.type, dsr2.size, dsr2.classification, dsr2.checksum);
						if(!printedLine.equals(dm.printTreeNodeSort())){
							dm.printTreeNode();
							System.out.print(" ");//10,11
							printedLine = dm.printTreeNodeSort();
							System.out.print("   ");
						}
					}else if(dsr2.type.equalsIgnoreCase("directory")){
						folderVisit = folderVisit + 1;
						if(folderVisit == 1){
							this.compareID = dsr2.id;
							DirectoryStructureReader dsr3 = new DirectoryStructureReader();
							String[] line1;
							while((line1 = dsr3.readCSVFile())!=null){
								dsr3.readCSVLine(line1);
								if(dsr3.parentId!=000 && dsr3.parentId==dsr2.id){
									caluculatedSize = caluculatedSize + dsr3.size;
								}
							}
							dsr3.closeResource();
							dsr2.size = calculateSizeOfFolder(dsr2.id);
							dm.setDirectoryModel(dsr2.id, dsr2.parentId, dsr2.name, dsr2.type, dsr2.size, dsr2.classification, dsr2.checksum);
							if(!printedLine.equals(dm.printTreeNodeSort())){
								dm.printTreeNode();
								//System.out.print("\t  ");
								printedLine = dm.printTreeNodeSort();
								System.out.print("\t  ");
							}
						}else{
							leftOverCompareIds.add(dsr2.id);
						}
					}
				}
			}
			icomp ++;
		}
		dsr2.closeResource();
	}
	
	public void calculateSizeOfPublicClassification(){
		dsr = new DirectoryStructureReader();
		int calculatedSize = 0;
		while((line = dsr.readCSVFile())!= null){
			 dsr.readCSVLine(line);
			 if(dsr.classification != null && dsr.classification.equalsIgnoreCase("public")){
				calculatedSize = calculatedSize+dsr.size;
			 }
		}
		System.out.println("Size : "+ calculatedSize);
		line = null;
		dsr.closeResource();
	}
	
	public void printTopSecret(){
		dsr = new DirectoryStructureReader();
		while((line = dsr.readCSVFile())!= null){
			 dsr.readCSVLine(line);
			 dm.setDirectoryModel(dsr.id, dsr.parentId, dsr.name, dsr.type, dsr.size, dsr.classification, dsr.checksum);
			 if(dm.getClassification()!=null && dm.getClassification().equalsIgnoreCase("top secret")){
				 dm.printTreeNode();
			 }
		}
		line = null;
		dsr.closeResource();
	}
	
	
	public int calculateSizeOfFolder(int id){
		int size = 0;
		int calculateID = 0;
		DirectoryStructureReader dsr0 = new DirectoryStructureReader();
		
		while((line = dsr0.readCSVFile())!= null){
			dsr0.readCSVLine(line);
			 if(dsr0.parentId==id){
				 if(dsr0.type.equalsIgnoreCase("file")){
					 size = size + dsr0.size;
				 }
				 if(dsr0.type.equalsIgnoreCase("directory")){
					 calculateID = dsr0.id;
					 DirectoryStructureReader dsr2 = new DirectoryStructureReader();
					 String[] line2;
					 while((line2 = dsr2.readCSVFile())!= null){
						 dsr2.readCSVLine(line2);
						 if(dsr2.parentId==calculateID){
							 if(dsr2.type.equalsIgnoreCase("file")){
								 size = size + dsr2.size;
							 }else{
								 calculateID = dsr2.id;
								 DirectoryStructureReader dsr3 = new DirectoryStructureReader();
								 String[] line3;
								 while((line3 = dsr3.readCSVFile())!= null){
									 dsr3.readCSVLine(line3);
									 if(dsr3.parentId==calculateID){
										 if(dsr3.type.equalsIgnoreCase("file")){
											 size = size + dsr3.size;
										 }else{
											 calculateID = dsr3.id;
											 DirectoryStructureReader dsr4 = new DirectoryStructureReader();
											 String[] line4;
											 while((line4 = dsr4.readCSVFile())!= null){
												 dsr4.readCSVLine(line4);
												 if(dsr4.parentId==calculateID){
													 if(dsr4.type.equalsIgnoreCase("file")){
														 size = size + dsr4.size;
													 }
												 }
											 }
											 dsr4.closeResource();
										 }
									 }
								 }
								 dsr3.closeResource();
							 }
						 }
					 }
					dsr2.closeResource();
				 }
				 
			 }
		}
		line = null;
		dsr0.closeResource();
		return size;
	}
	
	public void printSecret(){
		dsr = new DirectoryStructureReader();
		while((line = dsr.readCSVFile())!= null){
			 dsr.readCSVLine(line);
			 dm.setDirectoryModel(dsr.id, dsr.parentId, dsr.name, dsr.type, dsr.size, dsr.classification, dsr.checksum);
			 if(dm.getClassification()!=null && dm.getClassification().equalsIgnoreCase("secret")){
				 dm.printTreeNode();
			 }
		}
		line = null;
		dsr.closeResource();
	}
	
	public void printSecretOrTopSecret(){
		dsr = new DirectoryStructureReader();
		while((line = dsr.readCSVFile())!= null){
			 dsr.readCSVLine(line);
			 dm.setDirectoryModel(dsr.id, dsr.parentId, dsr.name, dsr.type, dsr.size, dsr.classification, dsr.checksum);
			 if(dm.getClassification()!= null){
				 if(dm.getClassification().equalsIgnoreCase("top secret") || dm.getClassification().equalsIgnoreCase("secret")){
					 dm.printTreeNode();
				 }
			 }
		}
		line = null;
		dsr.closeResource();
	}
	
	public void printSizeOfPlublicFileNodes(){
		int sum = 0;
		dsr = new DirectoryStructureReader();
		while((line = dsr.readCSVFile())!= null){
			 dsr.readCSVLine(line);
			 dm.setDirectoryModel(dsr.id, dsr.parentId, dsr.name, dsr.type, dsr.size, dsr.classification, dsr.checksum);
			 if(dm.getClassification()!=null && dm.getClassification().equalsIgnoreCase("public")){
				 sum = sum + dm.getSize();
			 }
		}
		System.out.println("Size of all Public nodes="+sum);
		line = null;
		dsr.closeResource();
	}
	
	public void printNonPublic(){
		dsr = new DirectoryStructureReader();
		while((line = dsr.readCSVFile())!= null){
			 dsr.readCSVLine(line);
			 dm.setDirectoryModel(dsr.id, dsr.parentId, dsr.name, dsr.type, dsr.size, dsr.classification, dsr.checksum);
			 if(dm.getClassification()!=null && !dm.getClassification().equalsIgnoreCase("public")){
				 dm.printTreeNode();
			 }
		}
		line = null;
		dsr.closeResource();
	}

}
