package directory.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import directory.model.DirectoryModel;
import directory.tree.Tree;

public class DirectoryStructureImplementation {
	
	ArrayList<DirectoryModel> al = new ArrayList<DirectoryModel>();
	ArrayList<DirectoryModel> objs = new ArrayList<DirectoryModel>();
	
	public void printTree(){
		DirectoryStructureReader dsr = new DirectoryStructureReader();
		Tree<DirectoryModel> directoryTree = null;
		DirectoryModel dm0 = null;
		
			String[] line;
			while((line = dsr.readCSVFile())!= null){
				objs.add(dsr.readCSVLine(line));
				if(dsr.parentId==0){
					dm0 = new DirectoryModel(dsr.id, dsr.parentId, dsr.name, dsr.type,dsr.size,dsr.classification,dsr.checksum);
					directoryTree = new Tree<DirectoryModel>(dm0);
					al.add(dm0);
				}
			}
			int lenght = objs.size();
			Iterator<DirectoryModel> itr = objs.iterator();
			while(itr.hasNext()){
				DirectoryModel dm = (DirectoryModel)itr.next();
				Iterator<DirectoryModel> itr1 = objs.iterator();
				if(dm.parentId!=0 && !al.contains(dm) ){
					while(itr1.hasNext()){
						DirectoryModel dm1 = (DirectoryModel)itr1.next();
						if(dm.parentId==dm1.id){				
							directoryTree.addLeaf(dm1, dm);
							al.add(dm1);
						}
					}
				}
			}
			System.out.println(directoryTree.toString());			
	}
	
	
	public void calculateSizeOfPublicClassification(){
		int calculatedSize = 0;
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			 DirectoryModel dm = (DirectoryModel)itr.next();
			 if(dm.classification != null && dm.classification.equalsIgnoreCase("public")){
				calculatedSize = calculatedSize+dm.size;
			 }
		}
		System.out.println("Size : "+ calculatedSize);
	}
	
	public void printTopSecret(){
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			DirectoryModel dm = (DirectoryModel)itr.next();
			 if(dm.getClassification()!=null && dm.getClassification().equalsIgnoreCase("top secret")){
				 dm.printTreeNode();
			 }
		}
	}
	
	
	/*public int calculateSizeOfFolder(int id){
		int size = 0;
		int calculateID = 0;
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			DirectoryModel dm = (DirectoryModel)itr.next();
			 if(dm.parentId==id){
				 if(dm.type.equalsIgnoreCase("file")){
					 size = size + dm.size;
				 }
				 if(dm.type.equalsIgnoreCase("directory")){
					 calculateID = dm.id;
					 Iterator<DirectoryModel> itr1 = objs.iterator();
					 while(itr1.hasNext()){
						 DirectoryModel dm1 = (DirectoryModel)itr1.next();
						 if(dm1.parentId==calculateID){
							 if(dm1.type.equalsIgnoreCase("file")){
								 size = size + dm1.size;
							 }else{
								 calculateID = dm1.id;
								 Iterator<DirectoryModel> itr2 = objs.iterator();
								 while(itr2.hasNext()){
									 DirectoryModel dm2 = (DirectoryModel)itr2.next();
									 if(dm2.parentId==calculateID){
										 if(dm2.type.equalsIgnoreCase("file")){
											 size = size + dm2.size;
										 }else{
											 calculateID = dm2.id;
											 Iterator<DirectoryModel> itr3 = objs.iterator();
											 while(itr3.hasNext()){
												 DirectoryModel dm3 = (DirectoryModel)itr2.next();
												 if(dm3.parentId==calculateID){
													 if(dm3.type.equalsIgnoreCase("file") ){
														 size = size + dm3.size;
													 }
												 }
											 }
										 }
									 }
								 }
							 }
						 }
					 }
				 }
				 
			 }
		}
		return size;
	}*/
	
	public void printSecret(){
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			 DirectoryModel dm = (DirectoryModel)itr.next();
			 if(dm.getClassification()!=null && dm.getClassification().equalsIgnoreCase("secret")){
				 dm.printTreeNode();
			 }
		}
	}
	
	public void printSecretOrTopSecret(){
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			 DirectoryModel dm = (DirectoryModel)itr.next();
			 if(dm.getClassification()!= null){
				 if(dm.getClassification().equalsIgnoreCase("top secret") || dm.getClassification().equalsIgnoreCase("secret")){
					 dm.printTreeNode();
				 }
			 }
		}
	}
	
	public void printSizeOfPlublicFileNodes(){
		int sum = 0;
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			 DirectoryModel dm = (DirectoryModel)itr.next();
			 if(dm.getClassification()!=null && dm.getClassification().equalsIgnoreCase("public")){
				 sum = sum + dm.getSize();
			 }
		}
		System.out.println("Size of all Public nodes="+sum);
	}
	
	public void printNonPublic(){
		Iterator<DirectoryModel> itr = objs.iterator();
		while(itr.hasNext()){
			 DirectoryModel dm = (DirectoryModel)itr.next();
			 if(dm.getClassification()!=null && !dm.getClassification().equalsIgnoreCase("public")){
				 dm.printTreeNode();
			 }
		}
	//	line = null;
	}

}
