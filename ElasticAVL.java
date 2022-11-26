
public class ElasticAVL {

	public class Node {
		
		/*
		 * variables and constants
		 */
		long key;
		int height;
		String value;
		Node left;
		Node right;
		Node parent;
		
		/*
		 * constructor
		 */
		public Node(long key, String value) {
			this.left = null; //creating a new node always sets its left child to null
			this.right = null; //creating a new node always sets its right child to null
			this.key = key; //key passed is the key of the Node
			this.value = value; //initialized to empty string because we do not know the values for each EIN
			this.parent = null;
			height = 1; //one tree node has a height of one
		}
		//This default constructor will act as the constructor for linked list in inorder traversal
		public Node() {
			this.right = null;
			this.key = 0;
			this.value = "";
		}
		
		/*
		 *getters and setters
		 */
		public long getKey() { return key; }
		public void setKey(long key) { this.key = key; }
		public int getHeight() { return height; }
		public void setHeight(int height) { this.height = height; }
		public String getValue() { return value; }
		public void setValue(String value) { this.value = value; }
		public Node getLeft() { return left; }
		public void setLeft(Node left) { this.left = left; }
		public Node getRight() { return right; }
		public void setRight(Node right) { this.right = right; }
		public Node getParent() { return parent; }
		public void setParent(Node parent) { this.parent = parent; }
	} //end of inner Node class
	
	/*
	 * variables
	 */
	Node root;
	Node[] sortedArray;
	int count = 0; //needs to be set to zero (outside this file) after function inorderTraversal() is called
	
	/*
	 * getters and setters
	 */
	public Node getRoot() { return root; }
	public void setRoot(Node root) { this.root = root; }
	public Node[] getSortedArray() { return sortedArray; }
	
	/**
	 * Function that returns height at the node
	 * @return int
	 */
	public int height(Node node) {
		if(node == null)
			return 0;
		return node.getHeight();
	}
	
	public Node rotateNodeRight(Node y) {
		Node x = y.getLeft();
		Node T2 = x.getRight();
		// Perform the rotation
		x.setRight(y);
		y.setLeft(T2);
		// Update heights
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		return x; // Return new root
	}
	
	public Node rotateNodeLeft(Node x) {
		Node y = x.getRight();
		Node T2 = y.getLeft();
		//perform the rotation
		y.setLeft(x);
		x.setRight(T2);
		//update height
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
		return y;
	}
	
	public int balanceFactor(Node node) {
		if(node == null) //a tree with no nodes has a balance factor of 0
			return 0;
		return height(node.getLeft()) - height(node.getRight()); //height of the left side of the tree - height of the right side of the tree
	}
	
	/**
	 * Insert method into AVL Tree
	 */
	public Node insert(long key, Node node, String value) { //pass ERL object, thus root of tree
		//Insert node into tree regularly
		if(node == null) { //if node is null, like root or external node, stopping case
			Node nodeToAdd = new Node(key, value);
			nodeToAdd.setParent(node); //set parent of new node to be previous node run through
			return nodeToAdd;
		}
		if(key < node.getKey()) //binary insert
			node.setLeft(insert(key, node.getLeft(), value));
		else if(key > node.getKey())
			node.setRight(insert(key, node.getRight(), value)); //run down the tree
		else 
			return node; //key is not less or greater than current key at the node, thus do not allow equal key
		
		//update the height of added node
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
		
		//check whether new added node has made AVL tree unbalanced
		int bf = balanceFactor(node);
			
		//if node has become unbalanced, balance it
		if(bf > 1 && key < node.getLeft().getKey()) //left-left
			return rotateNodeRight(node);
		if(bf < -1 && key < node.getRight().getKey()) //right-right
			return rotateNodeLeft(node);
		if(bf > 1 && key > node.getLeft().getKey()) { //left-right
			node.setLeft(rotateNodeLeft(node.getLeft()));
			return rotateNodeRight(node);
		}
		if(bf < -1 && key < node.getRight().getKey()) { //right-left
			node.setRight(rotateNodeRight(node.getRight()));
			return rotateNodeLeft(node);
		}
		return node;		
	} //end of insert()
	
	/**
	 * Delete Method into AVL Tree
	 */
	
	/**
	 * Searching AVL Tree: Binary Search
	 */
	public Node searchAVL(long key, Node node) { //pass root in ElasticERL file as Node node
		if(node == null || key == node.getKey())
			return node;
		if(key < node.getKey())
			return searchAVL(key, node.getLeft());
		else 
			return searchAVL(key, node.getRight()); //key > node.key
	}
	
	/**
	 * Inorder Traversal of AVL Tree
	 */
	public void inorderTraversalAVL(Node node, int size) { //root of AVL tree will be passed
		if(count == 0) { //count must be set to zero outside of the function, in ElasticERL file
			sortedArray = new Node[size];
		}
		if(node != null) {
			inorderTraversalAVL(node.getLeft(), size);
			System.out.print(node.getKey() + "  ");
			Node listNode = new Node(); //separate linked list form AVL tree will be created
			listNode.setKey(node.getKey());
			listNode.setValue(node.getValue());
			sortedArray[count] = listNode;
			count++;
			inorderTraversalAVL(node.getRight(), size);	
		}
	}
	public void printArray() {
		for(int i = 0; i > sortedArray.length; i++) {
			System.out.print(sortedArray[i].getKey());
		}
	}

	
	
}
