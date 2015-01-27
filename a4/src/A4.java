import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class A4 {

	public static void main(String[] args) throws Exception {
		if (args.length<2) {
			System.out.println("Usage: two arguments expected\n" + "-[pre/post] filename");

		} else if (args.length == 2) {
			BinaryTree<String> tree = null;
			//BufferedReader br = new BufferedReader(new FileReader(args[1]));

			if (args[0].equals("-pre")) {
				tree = BinaryTree.readPreBinaryTree(new BufferedReader(new FileReader(args[1])));
			} else if (args[0].equals("-post")) {	
				//TO DO 
				tree = BinaryTree.readPostBinaryTree(new Scanner(new FileReader(args[1])));
			}

			if (tree!=null) {
				//System.out.println(tree);// Part2
				 // tree.printTree(tree); //Part3
				 //System.out.println(tree.postorderResult()); //Part 1
			}
		} else if(args.length == 5){
			BinaryTree<String> tree1 = null;
			BinaryTree<String> tree2 = null;
			
			if (args[0].equals("-pre")) {
				tree1 = BinaryTree.readPreBinaryTree(new BufferedReader(new FileReader(args[1])));
			} else if (args[0].equals("-post")) {	
				//TO DO 
				tree1 = BinaryTree.readPostBinaryTree(new Scanner(new FileReader(args[1])));
			} 
			if (args[2].equals("-pre")) {
				tree2 = BinaryTree.readPreBinaryTree(new BufferedReader(new FileReader(args[3])));
			} else if (args[2].equals("-post")) {	
				//TO DO 
				tree2 = BinaryTree.readPostBinaryTree(new Scanner(new FileReader(args[3])));
			} 

			if (tree1!=null && tree2!=null) {
				BinaryTree<String> combinedTree= new BinaryTree<String>(args[4], tree1, tree2);
				//System.out.println(combinedTree);
				//combinedTree.printTree(combinedTree);
				System.out.println(combinedTree.postorderResult());
			}
			
		}
	}
}
