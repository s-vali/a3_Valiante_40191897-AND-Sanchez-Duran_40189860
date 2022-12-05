
public class ElasticAVL {

	public class Node {
		/*
		 * INNER CLASS
		 */
		
		/*
		 * Variables and Constants
		 */
		String key;
		int height;
		String value;
		Node left;
		Node right;
		
		/*
		 * Constructor
		 */
		public Node(String key, String value) {
			this.left = null; //creating a new node always sets its left child to null
			this.right = null; //creating a new node always sets its right child to null
			this.key = key; //key passed is the key of the Node
			this.value = value;
		}
		//This default constructor will act as the constructor for linked list in inorder traversal
		public Node() {
			this.right = null;
			this.key = "";
			this.value = "";
		}
		
		/*
		 *Getters and Setters
		 */
		public String getKey() { return key; }
		public void setKey(String key) { this.key = key; }
		public int getHeight() { return height; }
		public void setHeight(int height) { this.height = height; }
		public String getValue() { return value; }
		public void setValue(String value) { this.value = value; }
		public Node getLeft() { return left; }
		public void setLeft(Node left) { this.left = left; }
		public Node getRight() { return right; }
		public void setRight(Node right) { this.right = right; }
	} //end of inner Node class
	
	/*
	 * OUTER CLASS
	 */
	
	/*
	 * Variables
	 */
	Node root;
	Node[] sortedArray;
	int count = 0; //needs to be set to zero (outside this file) after function inorderTraversal() is called
	
	/*
	 * Getters and Setters
	 */
	public Node getRoot() { return root; }
	public void setRoot(Node root) { this.root = root; }
	public Node[] getSortedArray() { return sortedArray; }
	public int getCount() { return count; }
	public void setCount(int count) { this.count = 0; }
	
	/**
	 * Function that returns height at the node
	 * @return int
	 */
	public int height(Node node) {
		if(node == null)
			return -1;
		return node.getHeight();
	}
	
	/**
	 * Restructures AVL tree by rotating branch either right or left 
	 * @param y
	 * @return Node
	 */
	public Node rotateNodeRight(Node y) {
		Node x = y.getLeft();
		Node z = x.getRight();
		// Perform the rotation
		x.setRight(y);
		y.setLeft(z);
		// Update heights
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		return x;
	}
	
	public Node rotateNodeLeft(Node y) {
		Node x = y.getRight();
		Node z = x.getLeft();
		//perform the rotation
		x.setLeft(y);
		y.setRight(z);
		//update height
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		return x;
	}
	
	/**
	 * Calculates the balance factor at the node
	 * @param node
	 * @return int
	 */
	public int balanceFactor(Node node) {
		if(node == null) //a tree with no nodes has a balance factor of 0
			return 0;
		return height(node.getRight()) - height(node.getLeft()); //height of the left side of the tree - height of the right side of the tree
	}
	
	/**
	 * Inserts a node into an AVL tree
	 * @param nodeKey
	 * @param node
	 * @param value
	 * @return Node
	 */
	public Node insert(String nodeKey, Node node, String value) { //pass ERL object, thus root of tree
		long key = Long.parseLong(nodeKey);
		//Insert node into tree regularly
		if(node == null) { //if node is null, like root or external node, stopping case
			Node nodeToAdd = new Node(nodeKey, value);
			return nodeToAdd;
		}
		else if(key < Long.parseLong(node.getKey())) //binary insert
			node.setLeft(insert(nodeKey, node.getLeft(), value));
		else if(key > Long.parseLong(node.getKey()))
			node.setRight(insert(nodeKey, node.getRight(), value)); //run down the tree
		else 
			return node; //key is not less or greater than current key at the node, thus do not allow equal key
		
		//update the height of added node
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
		
		//check whether new added node has made AVL tree unbalanced
		int bf = balanceFactor(node);
			
		//if node has become unbalanced, balance it
		if(bf > 1) {
			if(height(node.getRight().getRight()) > height(node.getRight().getLeft())) {
				return rotateNodeLeft(node);
			} else {
				node.setRight(rotateNodeRight(node.getRight()));
				return rotateNodeLeft(node);
			}
		} else if(bf < -1) {
			if(height(node.getLeft().getLeft()) > height(node.getLeft().getRight())) {
				return rotateNodeRight(node);
			} else {
				node.setLeft(rotateNodeLeft(node.getLeft()));
				return rotateNodeRight(node);
			}
		}
		return node;	
	}
	
	/**
	 * Deletes a node of the AVL tree
	 * @param nodeKey
	 * @param node
	 * @param value
	 * @return Node
	 */
	public Node deleteNode(Node node, String nodeKey) {
		long key = Long.parseLong(nodeKey);
		if (node == null) {
            return node;
        } else if (Long.parseLong(node.getKey()) > key) {
            node.setLeft(deleteNode(node.getLeft(), nodeKey));
        } else if (Long.parseLong(node.getKey()) < key) {
            node.setRight(deleteNode(node.getRight(), nodeKey));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                node = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            } else {
            	Node temp = node.getRight();
				while(temp.getLeft() != null) {
					temp = temp.getLeft();
				}	
                node.setKey(temp.getKey());
                node.setRight(deleteNode(node.getRight(), node.getKey()));
            }
        }
        if (node != null) {
        	//update the height of added node
    		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    		
    		//check whether new added node has made AVL tree unbalanced
    		int bf = balanceFactor(node);
    			
    		//if node has become unbalanced, balance it
    		if(bf > 1) {
    			if(height(node.getRight().getRight()) > height(node.getRight().getLeft())) {
    				node =  rotateNodeLeft(node);
    			} else {
    				node.setRight(rotateNodeRight(node.getRight()));
    				node = rotateNodeLeft(node);
    			}
    		} else if(bf < -1) {
    			if(height(node.getLeft().getLeft()) > height(node.getLeft().getRight())) {
    				node = rotateNodeRight(node);
    			} else {
    				node.setLeft(rotateNodeLeft(node.getLeft()));
    				node = rotateNodeRight(node);
    			}
    		}
        }
		return node;
	}
	
	/**
	 * Searches the AVL tree using binary search to search for
	 * the given key
	 * @param nodeKey
	 * @param node
	 * @return Node
	 */
	public Node searchAVL(String nodeKey, Node node) { //pass root in ElasticERL file as Node node
		long key = Long.parseLong(nodeKey);
		if(node == null || nodeKey.compareTo(node.getKey()) == 0) //key.compareTo(node.getKey()) == 0
			return node; //will return null if the key could not be found in the tree
		if(key < Long.parseLong(node.getKey()))
			return searchAVL(nodeKey, node.getLeft());
		else 
			return searchAVL(nodeKey, node.getRight()); //key > node.key
	}
	
	/**
	 * Returns the parent of the key as the node
	 * by checking the contents of the current nodes children.
	 * @param key
	 * @param node
	 * @return Node
	 */
	public Node getParent(String key, Node node) { //pass root
        Node parent = node;
        while (parent != null) {
            if (parent.getLeft() == null || parent.getRight() == null) { //external node, so key was not found
               return null;
            }
            else if(parent.getLeft().getKey().compareTo(key) == 0 || parent.getRight().getKey().compareTo(key) == 0) {
            	break;
            }
            else {
            	if(Long.parseLong(parent.getKey()) < Long.parseLong(key))
            		parent = parent.getRight();
                else
                	parent = parent.getLeft();
            }   
        }
        return parent;
    }
	
	/**
	 * Returns a sorted array created by an inorder traversal of the 
	 * AVL tree.
	 * @param node
	 * @param size
	 */
	public void inorderTraversalAVL(Node node, int size) { //root of AVL tree will be passed
		if(count == 0) { //count must be set to zero outside of the function, in ElasticERL file
			sortedArray = new Node[size];
		}
		if(node != null) {
			inorderTraversalAVL(node.getLeft(), size);
			Node listNode = new Node(); //separate linked list form AVL tree will be created
			listNode.setKey(node.getKey());
			listNode.setValue(node.getValue());
			sortedArray[count] = listNode;
			count++;
			inorderTraversalAVL(node.getRight(), size);	
		}
	}
	
	/**
	 * Displays the inorder traversal of the AVL tree. 
	 * @param node
	 * @param size
	 */
	public void displayAVL(Node node, int size) {
		if(node != null) {
			displayAVL(node.getLeft(), size);
			System.out.print(node.getKey() + "  ");
			displayAVL(node.getRight(), size);	
		}
	}	
} //end of class
