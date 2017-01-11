package directory.main;

import directory.implementation.DirectoryStructureImplementation;

public class Run {
	
	public static void main(String args[]){
		DirectoryStructureImplementation dsi = new DirectoryStructureImplementation();
		//to print tree
		System.out.println("PRINT TREE");
		System.out.println("-------------------------------");
		dsi.printTree();
		System.out.println("-------------------------------");
		//to print node of classification as top secret
		/*System.out.println("\n\nPRINT TOP SECRET");
		System.out.println("-------------------------------");
		dsi.printTopSecret();
		System.out.println("-------------------------------");
		//to print node of classification as secret
		System.out.println("\n\nPRINT SECRET");
		System.out.println("-------------------------------");
		dsi.printSecret();
		System.out.println("-------------------------------");
		//to print node of classification as secret or top secret
		System.out.println("\n\nPRINT SECRET OR TOP SECRET");
		System.out.println("-------------------------------");
		dsi.printSecretOrTopSecret();
		System.out.println("-------------------------------");
		//to print size of all node whose classification is public
		System.out.println("\n\nPRINT SIZE OF PUBLIC");
		System.out.println("-------------------------------");
		dsi.calculateSizeOfPublicClassification();
		System.out.println("-------------------------------");
		//to print node of classification as non public
		System.out.println("\n\nPRINT NON PUBLIC");
		System.out.println("-------------------------------");
		dsi.printNonPublic();
		System.out.println("-------------------------------");*/
	}

}
