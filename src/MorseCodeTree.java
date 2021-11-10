import java.util.ArrayList;

/**
 * This is a MorseCodeTree which is specifically used for the conversion of morse code to english It relies on a root (reference to root of the tree) The root is set to null when the tree is empty. 
 * The class uses an external generic TreeNode class which consists of a reference to the data and a reference to the left and right child.
 * The TreeNode is parameterized as a String, TreeNode This class uses a private member root (reference to a TreeNode) The constructor will call the buildTree method
 * @author pjose
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> root;
	
	/**
	 * Constructor
	 * Calls the BuildTree method
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	
	/**
	 *  Get the root of the Tree Node
	 *  @return root
	 */
	@Override
	public TreeNode<String> getRoot() {
		
		return this.root;
	}

	
	
	/**
	 * set the root of the Tree Node
	 * @param newNode of class TreeNode of type String
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;	
	}

	
	/**
	 * Insert a letter based on a code morse in this TreeNode
	 * @param code the code of the letter in Morse
	 * @param letter the letter to insert
	 * @return this class
	 */
	@Override
	public MorseCodeTree insert(String code, String letter) {
		addNode(this.root, code, letter);
		return this;
	}

	
	/**
	 * Add a letter based on a code morse in a TreeNode
	 * @param root a TreeNode 
	 * @param code the code of the letter in morse
	 * @param letter the letter to insert
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		// 1. if there is only one character
		if (code.length() == 1) {
			
			// a. if the character is '.' (dot) store to the left of the current root
			if (code.charAt(0) == '.') {
				root.left = new TreeNode<String>(letter);
			}
			
			// b. if the character is "-" (dash) store to the right of the current root
			else if (code.charAt(0) == '-') {
				root.right = new TreeNode<String>(letter);
			}
			// c. return
		}
		
		// 2. if there is more than one character
		else {
			
			// a. if the first character is "." (dot) new root becomes the left child
			if (code.charAt(0) == '.') {
				
				// c. new code becomes all the remaining charcters in the code (beyond the first character)
				// d. call addNode(new root, new code, letter)
				addNode(root.left, code.substring(1),letter);
			}
			
			// b. if the first character is "-" (dash) new root becomes the right child
			else if(code.charAt(0) == '-') {
				
				// c. new code becomes all the remaining charcters in the code (beyond the first character)
				// d. call addNode(new root, new code, letter)
				addNode(root.right, code.substring(1),letter);
			}
		}
		
		
	}

	
	
	
	/**
	 * Fetch the data in the tree based on the code.  
	 * This method will call the recursive method fetchNode which passes the root of this class and code
	 * @param code
	 * @return a String letter according to the code
	 */
	@Override
	public String fetch(String code) {
		
		String letter = fetchNode(this.root, code);
		
		return letter;
	}

	
	
	
	/**
	 * This is the recursive method that fetches the data of the TreeNode that corresponds with the code 
	 * A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right. 
	 * The code ".-" would fetch the data of the TreeNode stored as the right child of the left child of the root
	 * @param root
	 * @param code
	 * @return a String letter according to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {

		String letter="";
		
		if (code.length() == 1) {
			
			if (code.equals(".")) 
				return root.left.data;
			
			else 
				return root.right.data;
				
		} 
		
		else {
			if (code.charAt(0) == '.')
				letter = fetchNode(root.left, code.substring(1));
				
			else 
				letter = fetchNode(root.right, code.substring(1));
				
		}
		return letter;
	}
	
	
	
	/**
	 * This operation is not supported in the MorseCodeTree
	 * @param data
	 * @return reference to this class
	 * @throws UnsupportedOperationException
	 */
	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	
	/**
	 * This operation is not supported in the MorseCodeTree
	 * @return reference to this class
	 * @throws UnsupportedOperationException
	 */
	@Override
	public MorseCodeTree update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	
	
	/**
	 * This method builds the MorseCodeTree by inserting the nodes of the tree level by level based on the code.
	 */
	@Override
	public void buildTree() {
		
		// Level 0
		root = new TreeNode<String>(""); 
		
		// Level 1
		insert(".", "e");
		insert("-", "t");
		
		// Level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		// Level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		// Level 4
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}

	
	
	/**
	 * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order Used for testing to make sure tree is built correctly
	 * @return an ArrayList
	 */
	@Override
	public ArrayList<String> toArrayList() {
		
		ArrayList<String> myArray = new ArrayList<>();
		
		// Traverse thro the tree, store each element into the passed Array
		LNRoutputTraversal(root, myArray);
		
		return myArray;
	}

	
	
	/**
	 * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
	 * @param root - the root of the tree for this particular recursive instance
	 * @param list - the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		if ( root.left != null ) 
			LNRoutputTraversal(root.left, list);
		
		list.add(root.data);
		
		if (root.right != null) 
			LNRoutputTraversal(root.right, list);
		
	}

}
