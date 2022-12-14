//package Assi3;

import java.util.Random;

import java.util.ArrayList;

public class ElasticERL {
	
	/*
	 * Variables and Constants of ElasticERL
	 */
	ElasticAVL avltree = null;
	myHashTable hashtable = null;
	int sizeOfERL;
	
	/*
	 * Constructor
	 */
	public ElasticERL(int size) {
		if (size < 1000) 
			avltree = new ElasticAVL();	
		else 
			hashtable = new myHashTable(size);	//passed size as argument for myHashTable
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
	 * Class Methods
	 */
	/**
	 * Create the appropriate ADT based on the size
	 * 
	 * @param size
	 * @return Object
	 */
	public static Object setEINThreshold(int size) {
			return new ElasticERL(size);
	}
	
	/**
	 * Returns a randomly generated key that should not exist in the ADT. 
	 * If the size of the ERL is less than 1000, the function will create 
	 * a sorted array through inorder traversal of the AVL ADT 
	 * and randomly index, take the keys at both indices and generate a 
	 * random 8 digit number between the two. If greater than 1000, a random 8 
	 * digit number will be generated and searched for its existence in
	 * the hash map. If it does not exist, it will be returned. 
	 * 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Thus, a random number will be created and the function keyExists(k) will be called from the myHashTable class in order to ensure 
	 * that the key is not already in the hash table. If the key is not in the hash table, it will return the newly generated number. 
	 * Otherwise, it will continue to generate random numbers until it does one that is not already in the hash table. 
	 * The keyExists(k) function has a time complexity of O(1) at best and O(n) at worst.
	 * Best case: O(1)
	 * Worst case: O(n)
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
			//Generating random 8-digit number
			Random rand = new Random();
			int num = rand. nextInt(90000000) + 1000000;
			
			//Checking if number is already a key in hash table
			while (hashtable.keyExists(num)) {
				rand = new Random();
				num = rand. nextInt(90000000) + 1000000;
			}
			return num;
		}
	}
	
	/**
	 * Returns a sorted sequence of all the keys in the ADT.
	 * If the size of the ERL is less than 1000, it does so 
	 * by an inorder traversal of the AVL ADT. If greater than
	 * 1000, the hash table will be looped through and a collections 
	 * sort performed. 
	 * 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Therefore, the allKeys(ElasticERL) function will call the allKeysInHash(ElasticERL) which, in turn, will call the getAllKeys() function from the myHashTable class. 
	 * The getAllKeys() function has a time complexity of O(n) and iterates through the entire hash table in order to obtain all the keys and sort them.
	 * Best case: O(n)
	 * Worst case: O(n)
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
			allKeysinHash(elasticERLObj); //see method below!
			return null;
		}
	}
	public ArrayList<String> allKeysinHash(ElasticERL elasticERLObj){
		return elasticERLObj.getHashTable().getAllKeys();
	}
	
	/**
	 * Add an entry to the appropriate ADT. If the ERL is less than 
	 * 1000, the key will be inserted following the property of the AVL
	 * tree, and restructured accordingly if the insertion caused an
	 * imbalance. If greater than 1000, the key will be hashed to
	 * its correct location, and collisions will be handled through
	 * separate chaining. 
	 * 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Thus, the put(key, value) function is called from the myHashTable class. 
	 * The put(key, value) function checks if the key is already in the hash table and if it is not, it will add the new key-value pair. 
	 * If the key already exists in the hash table, the value will be overwritten with the new value. At best, the time complexity is O(1). At worst, the time complexity is O(n).
	 * Best case: O(1)
	 * Worst case: O(n)
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @param value
	 */
	public void add(ElasticERL elasticERLObj, String key, String value) {
		if(elasticERLObj.getSizeOfERL() < 1000) { //if the size set for the ADT is <1000, insert into AVL tree
			avltree.setRoot(avltree.insert(key, avltree.getRoot(), value)); //inserting into AVL tree
		} 
		else { //size set is greater than or equal to 1000, insert into hash table
			elasticERLObj.getHashTable().put(key,value); //inserting into hash table
		}
	}
	
	/**
	 * Remove an entry from the appropriate ADT. If the ERL is less than 
	 * 1000, the removal will occur to the AVL ADT, in which any imbalance
	 * caused by the deletion of the node in the tree will trigger a restructuring 
	 * of the tree. 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Thus, the remove(key) function is called from the myHashTable class. 
	 * The remove(key) function searches for the key that the user wants to remove and if it exists, it will remove the key-value pair from the hash table and return the value of the key that was removed. 
	 * Otherwise, it will return null. At best, the time complexity is O(1). At worst, the time complexity is O(n).
	 * Best case: O(1)
	 * Worst case: O(n)
	 * 
	 * @param elasticERLObj
	 * @param key
	 */
	public void remove(ElasticERL elasticERLObj, String key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			ElasticAVL tree = elasticERLObj.getAvltree();
			tree.setRoot(tree.deleteNode(tree.getRoot(), key));
			setSizeOfERL(getSizeOfERL()-1);
		}
		else {
			//remove from hash table
			elasticERLObj.getHashTable().remove(key); 
		}
	}
	
	/**
	 * Return the value at the indicated key in the ADT. If the ERL
	 * is less 1000, the method will utilize the AVL ADT binary search to
	 * search for the node holding the given key and return its value. 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Thus, the get(key) function is called from the myHashTable class which returns the value(s) of the given key if the key exists.
	 * Otherwise, get(key) returns null. At best, the time complexity is O(1). At worst, the time complexity is O(n).
	 * Best case: O(1)
	 * Worst case: O(n)
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @return String
	 */
	public String getValues(ElasticERL elasticERLObj, String key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			//If key does not exist in the Tree
			if((elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot())) == null){
				return "The key " + key + "does not exist. ";
			}
			//Key does exist in the Tree
			return (elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot())).getValue();
		} 
		else {
			return elasticERLObj.getHashTable().get(key);	//returning value of key from hash table
		}
	}
	
	/**
	 * Returns the key of the successor of the key passed. If the 
	 * size of the ERL is less than 1000, the right child of the 
	 * current node matching the given key will be returned. 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Thus, the getNextKey(key) function is called from the myHashTable class.
	 * The function gets all the keys, sorts them and returns the successor key of the key passed to the function getNextKey(key). 
	 * The time complexity of this is O(n).
	 * Best case: O(logn)
	 * Worst case: O(n)
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
			return elasticERLObj.getHashTable().getNextKey(key);
		}
	}
	
	/**
	 * Returns the predecessor of the key passed. If the size of 
	 * the ERL if less than 1000, the parent of the current node
	 * matching the given key will be returned. 
	 * 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Thus, the getPrevKey(key) function is called from the myHashTable class. 
	 * The function gets all the keys, sorts them and returns the predecessor key of the key passed to the function getPrevKey(key). 
	 * The time complexity of this is O(n).
	 * 
	 * Best case: O(logn)
	 * Worst case: O(n)
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
				return "Key " + key + " does not exist for prevKey(key) or key has no parent (root). "; //key not found in the Tree
			}
			else 		
				return temp.getKey();
		} 
		else {
			return elasticERLObj.getHashTable().getPrevKey(key);
		}
	}
	
	/**
	 * If the size of the ERL if less than 1000, an inorder traversal
	 * will be performed to construct a sorted sequenced array,
	 * which will be accessed at the locations of the smaller value of the keys
	 * and a counter incremented. The loop will stop when the larger key is reached
	 * and the counter will be returned. The counter is inclusive. 
	 * If the size is greater than or equal to 1000, then the method will only consider the hash table object. 
	 * Thus, the getRangeOfKeys(k1, k2) function is called from the myHashTable class. 
	 * The function gets all the keys, sorts them and returns the number of keys (not inclusive) between the two keys passed to the function getRangeOfKeys(k1, k2). 
	 * The time complexity of this is O(n).
	 * Best case: O(n)
	 * Worst case: O(n)
	 * 
	 * @param elasticERLObj
	 * @param nodeKey1
	 * @param nodeKey2
	 * @return int
	 */
	public int rangeKey(ElasticERL elasticERLObj, String nodeKey1, String nodeKey2) {
		//convert form String to Long
		long key1 = Long.parseLong(nodeKey1);
		long key2 = Long.parseLong(nodeKey2);
		int count = 0;
		if (elasticERLObj.getAvltree() != null) {
			elasticERLObj.getAvltree().count = 0;
		}
		if(elasticERLObj.getSizeOfERL() < 1000) {
			//Run through array of inorder keys and increment counter
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
			return elasticERLObj.getHashTable().getRangeOfKeys(nodeKey1, nodeKey2);
		}
	}
} //end of elasticERL class