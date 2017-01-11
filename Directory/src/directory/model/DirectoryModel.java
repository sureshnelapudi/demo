package directory.model;
public class DirectoryModel {
public String name, classification, type;
public int id;
public int parentId;
public int size;
public int checksum;
public DirectoryModel(int id,int parentId, String name, String type, int size, String classification, int checksum){
	this.id = id;
	this.parentId = parentId;
	this.name = name;
	this.type = type;
	this.size = size;
	this.classification = classification;
	this.checksum = checksum;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getClassification() {
	return classification;
}
public void setClassification(String classification) {
	this.classification = classification;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getParentId() {
	return parentId;
}
public void setParentId(int parentId) {
	this.parentId = parentId;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
public int getChecksum() {
	return checksum;
}
public void setChecksum(int checksum) {
	this.checksum = checksum;
}
public void printTreeNode(){
	if(type.equalsIgnoreCase("directory")){
		System.out.println("name = "+name+", type = "+type+", size = "+size);
	}else{
		System.out.println("name = "+name+", type = "+type+", size = "+size+", classification = "+classification+", checksum = "+checksum);
	}
}
public String printTreeNodeSort(){
	if(type.equalsIgnoreCase("directory")){
		return "name = "+name+", type = "+type+", size = "+size;
	}else{
		return "name = "+name+", type = "+type+", size = "+size+", classification = "+classification+", checksum = "+checksum;
	}
}
}
