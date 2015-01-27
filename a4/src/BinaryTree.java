
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class for a binary tree that stores type E objects.
 * @author Koffman and Wolfgang
 **/
public class BinaryTree<E> implements Serializable {

    /*<listing chapter="6" number="1">*/
    /** Class to encapsulate a tree node. */
    protected static class Node<E> implements Serializable {
        // Data Fields

        /** The information stored in this node. */
        public E data;
        /** Reference to the left child. */
        public Node<E> left;
        /** Reference to the right child. */
        public Node<E> right;

        // Constructors
        /**
         * Construct a node with given data and no children.
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods
        /**
         * Returns a string representation of the node.
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
    /*</listing>*/
    // Data Field
    /** The root of the binary tree */
    protected Node<E> root;

    /** Construct an empty BinaryTree */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a BinaryTree with a specified root.
     * Should only be used by subclasses.
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree
     * as its left subtree and rightTree as its right subtree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
            BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     * @return The left subtree or null if either the root or
     * the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     * @return the right sub-tree or
     *         null if either the root or the
     *         right subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     * @return the data field of the root
     *         or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    
  

    /**
     * Perform a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth,
            StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }
    /**
     * Method to traverse the tree in postorder 
     * @param node
     * @param sb
     */
    private void postOrderTraverse(Node<E> node,StringBuilder sb) {
    	if (node != null)
    	  {
    	   postOrderTraverse(node.left,sb);
    	   postOrderTraverse(node.right,sb);
    	   sb.append(node.data + " ");
    	  }
    }
    /**
     * Method to return a post order travresed tree with a evaluated result
     * @return
     */
    public String postorderResult(){
    	 StringBuilder sb = new StringBuilder();
         postOrderTraverse(root,sb);
         sb.append("="+" "+eval(sb.toString()));
         return sb.toString();
    }
    /**
     * Method to evaluate the post order traversal
     */
    private int eval(String exp) {
       //store a result
    	int res = 0;
    	//create new stack
        Stack<Integer> list = new Stack<Integer>();
        int first;     
        int second;     
        try{
        //iterate through an expression
        for (int i = 0; i < exp.length(); i++) {
        	
        	//get every char of the expression 
            char ch = exp.charAt(i);
            //get all of the numbers from expression 
            	if(isOperand(String.valueOf(ch))){
                        list.push(Character.getNumericValue(ch));//push numbers
                }else if(ch == '+' || ch== '-' || ch == '*' || ch == '/' ){
                        first = list.pop();
                        second = list.pop();
                        //do operations
                        switch (ch) {
                            case '+':
                                list.push(first + second);
                                break;
                            case '-':
                                list.push(second - first);
                                break;
                            case '*':
                                list.push(first * second);
                                break;
                            case '/':
                                list.push(second / first);
                                break;
                        }
                }//end of else
            }//end of for loop

        res = list.pop();
        
        }catch(EmptyStackException e){
        	System.out.println("Invalid input. Can only perform evaluation of an arithmetic binary tree with numbers");
        }
        return res;
    }
    /*<listing chapter="6" number="2">*/
    /**
     * Method to read a binary tree.
     * @pre The input consists of a preorder traversal
     *      of the binary tree. The line "null" indicates a null tree.
     * @param bR The input file
     * @return The binary tree
     * @throws IOException If there is an input error
     */
    public static BinaryTree<String> readPreBinaryTree(BufferedReader bR)
            throws IOException {
        // Read a line and trim leading and trailing spaces.
        String data = bR.readLine().trim();
       // System.out.println("data is "+data);
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readPreBinaryTree(bR);
            BinaryTree<String> rightTree = readPreBinaryTree(bR);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }
    /**
     * The method constructs a tree based on the postfix notation stored in a file.
     * The evaluation of the tree takes place by reading the expression one symbol at a time. 
     * @pre The input consists of a preorder traversal
     *      of the binary tree. The line "null" indicates a null tree.
     * @param scanner
     * @return
     */
    public static BinaryTree<String> readPostBinaryTree(Scanner scanner){
    	String symbol = null;
    	Stack<Node<String>> stack=new Stack<Node<String>>();
    	BinaryTree<String> newTree = null;
    	while(scanner.hasNext()){
        symbol= scanner.next();
        //if the symbol is an operand
        if(isOperand3(symbol)){
        	//create a node for the operand
        	Node<String> operand=new Node<String>(symbol);
        	// push the reference to the created node onto the stack ;
        	stack.push(operand);
        }
        //if symbol in the expression is a binary operator
        else if(!isOperand3(symbol)){
        	//create a node for the operator 
        	String operator=symbol;
        	//pop from the stack a reference to an operand
        	BinaryTree<String> poppedRight=new BinaryTree<String>(stack.pop());
        	BinaryTree<String> poppedLeft=new BinaryTree<String>(stack.pop());
        	//make the operand the right and left subtree of the operator node
        	newTree= new BinaryTree<String>(operator,poppedLeft, poppedRight);
        	//push the reference to the operator node onto the stack 
        	stack.push(newTree.root);
        }      	
    	}
            return newTree;
    }
    /**
     * Method to check for operand Part 3
     */
    public static boolean isOperand3(String symbol){
    	if( symbol.equals("+") || symbol.equals("-") || symbol.equals("*")|| symbol.equals("/")){
    		return false;
    	}
    	System.out.println();
    	return true;
    }
    /**
     * Method to check whether the string is the operand
     * @param str
     * @return
     */
    public static boolean isOperand(String str) {
        try {
        	//true only if number
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Method to print tree in 2D CALL THIS TO PRINT
     */
    public void printTree(BinaryTree<String> tree){
    	List<Node<String>> rootList= new ArrayList<Node<String>>();
    	rootList.add(tree.root);
    	drawTree(rootList, getHeight(tree.root));
    }
    
    /**
     * RECURSIVE!!!
     * method to output a 2D layout of the binary expression tree
     */
    public void drawTree(List<Node<String>> printNodes, int depth){
    	//create a queue(arraylist) for nodes to print
    	List<Node<String>> nodes=new ArrayList<Node<String>>();
    	//first print the indents from left
    	printIndent(depth);
    	for(Node<String> curNode: printNodes){
    		//print the node
        	if(curNode == null){
        		System.out.print("o");
        	}else{
        		System.out.print(curNode.data);
        	}
        	//print spacing betweeen nodes
        	printSpacing(depth);
        	//check whether it is a leaf node
        	if(depth>1){
        		if(curNode!=null){
        			nodes.add(curNode.left);
        			nodes.add(curNode.right);
        		}
        		
        	}
    	}
    	//print spaces between lines
    	System.out.println();
    	System.out.println();
    	//recursivly call
    	if(depth>1){        
            drawTree(nodes, depth-1);
        }
    
    }
    /**
     * Method to print indents
     * @param level
     */
    
    private void printIndent(int level){
        for (int i = (int) (Math.pow(2,level-1)); i >0; i--) {
            System.out.print(" ");
        }
    }
    /**
     * Method to print out spaces inbetweens
     * @param level
     */
    private void printSpacing(int level){
        //spacing between nodes
        for (int i = (int) ((Math.pow(2,level-1))*2)-1; i >0; i--) {
            System.out.print(" ");
        }
    }
    /**
     * Method to get a current height of a tree
     */
    public int getHeight(Node root) {
    	//if tree is empty height is 0
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }
}
