import java.util.Random;

import java.util.ArrayList;

public class ElasticERL {
	
	/*
	 * Variables and Constants of ElasticERL
	 */
	ElasticAVL avltree = null;
	int sizeOfERL;
	myHashTable hashtable = null;
	
	/*
	 * Constructor
	 */
	public ElasticERL(int size) {
		if (size < 1000) {
			avltree = new ElasticAVL();	
		}
		else {
			hashtable = new myHashTable(size);	//passed size as argument for myHashTable
		}
		sizeOfERL = size;
	}
	
	/*
	 * Getters and Setters
	 */
	//AVL
	public ElasticAVL getAvltree() { return avltree; }
	public void setAvltree(ElasticAVL avltree) { this.avltree = avltree; }
	public int getSizeOfERL() { return sizeOfERL; }
	public void setSizeOfERL(int sizeOfERL) { this.sizeOfERL = sizeOfERL; }
	//HashMap
	public myHashTable getHashTable() { return hashtable;}
	public void setHashTable(myHashTable hashtable) { this.hashtable = hashtable; }
	
	/*
	 * class methods
	 */
	/**
	 * Create the appropriate ADT based on the size
	 * 
	 * @param size
	 * @return Object
	 */
	public static Object setEINThreshold(int size) {	//MAYBE WE CAN MODIFY THIS!
			return new ElasticERL(size);
	}
	
	/**
	 * Returns randomly generated key that should not exist in the ADT
	 * 
	 * @param elasticERLObj
	 * @return int
	 */
	public int generate(ElasticERL elasticERLObj) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			//Create sorted array of key values
			ElasticAVL tree = elasticERLObj.getAvltree();
			tree.setCount(0);
			tree.inorderTraversalAVL(tree.getRoot(), elasticERLObj.getSizeOfERL());
			ElasticAVL.Node[] tempArray = elasticERLObj.getAvltree().getSortedArray();
			//Create random index
			Random rand = new Random();
			int randomIndex = rand.nextInt(elasticERLObj.getSizeOfERL()-1); //goes from 0 to size-1, used to access array at random index
			//Determined lower and upper bounds for new generated key		
			int keyLower = Integer.parseInt(tempArray[randomIndex].getKey());
			int keyUpper = Integer.parseInt(tempArray[randomIndex+1].getKey());
			//Make sure the lower bound is actually lower
			if(keyLower > keyUpper) {
				int temp = keyLower; //keyLower is larger than keyUpper
				keyLower = keyUpper;
				keyUpper = temp;
			}
			//create new generated key based on lower and upper bounds
			Random randNb = new Random();
			int generatedKey = randNb.nextInt(keyUpper-(keyLower+1)) + (keyLower+1);
			return generatedKey;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Return a sorted sequence of all the keys in the ADT
	 * 
	 * @param elasticERLObj
	 * @return Object
	 */
	public Object allKeys(ElasticERL elasticERLObj) {
		if(elasticERLObj.getSizeOfERL() < 1000) { //AVL Case
			//Set counter to 0
			elasticERLObj.getAvltree().setCount(0);
			//First sort the tree into an array by inorder traversal
			elasticERLObj.getAvltree().inorderTraversalAVL(avltree.getRoot(), elasticERLObj.getSizeOfERL());
			//Get the created array in the ElasticAVL class and upcast to Object array
			Object[] sortedArray = elasticERLObj.getAvltree().getSortedArray();
			return sortedArray;
		} 
		else {
			allKeysinHash(elasticERLObj);	//see method below!
			return null;
		}
	}
	//ADDED THIS METHOD FOR HASH FUNCTION!!!!!!!!!!!!!!!!!
	public ArrayList<Integer> allKeysinHash(ElasticERL elasticERLObj) {
		return elasticERLObj.getHashTable().getAllKeys();
	}
	
	/**
	 * Add an entry to the appropriate ADT
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @param value
	 */
	public void add(ElasticERL elasticERLObj, String key, String value) {
		if(elasticERLObj.getSizeOfERL() < 1000) { //if the size set for the ADT is <1000, insert into AVL tree
			avltree.setRoot(avltree.insert(key, avltree.getRoot(), value));
		} 
		else { //size set is greater than or equal to 1000, insert into hash table
			int pkey = Integer.parseInt(key);
			hashtable.put(pkey,value);	//inserting into hash table
		}
	}
	
	/**
	 * Remove an entry from the appropriate ADT
	 * 
	 * @param elasticERLObj
	 * @param key
	 */
	public void remove(ElasticERL elasticERLObj, String key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			ElasticAVL tree = elasticERLObj.getAvltree();
			tree.setRoot(tree.deleteNode(tree.getRoot(), key));
		}
		else {
			int pkey = Integer.parseInt(key);
			hashtable.remove(pkey); //remove from hash table
		}
	}
	
	/**
	 * Return the value at the indicated key in the ADT
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @return String
	 */
	public String getValues(ElasticERL elasticERLObj, String key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			//If key does not exist in the Tree
			if((elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot())) == null){
				return "The key does not exist. ";
			}
			//Key does exist in the Tree
			return (elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot())).getValue();
		} 
		else {
			int pkey = Integer.parseInt(key);
			return hashtable.get(pkey);
		}
	}
	
	/**
	 * Returns the key of the successor of the key passed
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @return long
	 */
	public String nextKey(ElasticERL elasticERLObj, String key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			ElasticAVL.Node node = elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot());	
			if(node == null)
				return "Key does not exist. ";
			else if(node.getRight() == null) //reached an external node
				return "There is no next key because node " + key + " is external";
			else 
				return node.getRight().getKey();
		} 
		else {
			return "";
		}
	}
	
	/**
	 * Returns the predecessor of the key passed
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @return long 
	 */
	public String prevKey(ElasticERL elasticERLObj, String key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			ElasticAVL tree = elasticERLObj.getAvltree();
			ElasticAVL.Node temp = elasticERLObj.getAvltree().getParent(key, elasticERLObj.getAvltree().getRoot());
			if(tree == null) {
				return "Tree does not exists.";
			}
			if(temp == null) { //returned node is either root or external
				return "Key does not exist for prevKey(key) or key has no parent (root). "; //key not found in the Tree
			}
			else 		
				return temp.getKey();
		} 
		else {
			return "";
		}
	}
	
	public int rangeKey(ElasticERL elasticERLObj, String nodeKey1, String nodeKey2) {
		//convert form String to Long
		long key1 = Long.parseLong(nodeKey1);
		long key2 = Long.parseLong(nodeKey2);
		int count = 0;
		elasticERLObj.getAvltree().count = 0;
		if(elasticERLObj.getSizeOfERL() < 1000) {
			//run through array of inorder keys and increment counter
			ElasticAVL tree = elasticERLObj.getAvltree();
			//Perform inorder traversal of AVL tree
			tree.inorderTraversalAVL(tree.getRoot(), elasticERLObj.getSizeOfERL());
			Object[] sortedArray = tree.getSortedArray();
			if(sortedArray == null || elasticERLObj.getAvltree().getSortedArray() == null)
				return 0;
			else
				if(key1 > key2) { //make sure passed key1 is smaller than key2 so that range is [key1, key2]
					long temp = 0;
					temp = key1;
					key1 = key2;
					key2 = temp;
				}
			//SEARCH IF KEYS EXIST
				for(int i = 0; i < sortedArray.length; i++) {
					ElasticAVL.Node node = (ElasticAVL.Node)sortedArray[i];//downcast to Node in ElasticAVL
					if(Long.parseLong(node.getKey()) >= key1) //if key of node is >= key1
						count++;
					if(Long.parseLong(node.getKey()) == key2)
						break;
				}
			return count;
		} 
		else {
			return 1000;
		}
	}
} //end of elasticERL class
