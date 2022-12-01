package Assi3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * myHash Table Class
 */
public class myHashTable {
	private HashNode[] bucketArray;
	private int numOfBuckets;	//capacity (i.e: length of bucketArray)
	private int numOfPairs;	//number of key-value pairs in hash table (i.e: number of HashNodes)
	
	/**
	 * Default Constructor
	 */
	public myHashTable() {
		this.numOfBuckets = 1001;	//default capacity
		this.bucketArray = new HashNode[numOfBuckets];
		this.numOfPairs = 0;
	}
	
	/**
	 * Constructor
	 * @param capacity
	 */
	public myHashTable(int capacity) {
		this.numOfBuckets = capacity;
		this.bucketArray = new HashNode[numOfBuckets];
		this.numOfPairs = 0; 
	}
	
	
	/**
	 * HashNode Class
	 */
	private class HashNode {
		private String key;
		private String value;
		private HashNode next;	//reference to the next HashNode
		
		//Constructor
		public HashNode(String key, String value) {
			this.key = key;
			this.value = value;
		}
		//Setters and Getters
		public String getKey() {	
			return key;
		}
		
		public void setKey(String key) {
			this.key = key;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
		
	}	//end of HashNode Class
	
	/*
	 * Functions of myHashTable
	 */
	
	/**
	 * This function returns the number of key-value pairs (i.e: the number of HashNodes) 
	 * @return numOfPairs
	 */
	public int numOfPairs() {
		return numOfPairs;
	}
	
	/**
	 * Function returns true if the hash table is empty (has no key-value pairs). Otherwise, returns false.
	 * @return
	 */
	public boolean isEmpty() {
		return numOfPairs==0;
	}
	
	/**
	 * Inserts new key-value pair. If the key already exists, it will be updated with the new value.
	 * @param key
	 * @param value
	 */
	//Time Complexity of O(1)
	public void put(String key, String value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Key or Value is null!");
		}
		int intKey = Integer.parseInt(key);	//converting key string to integer
		int bucketIndex = getBucketIndex(intKey);
		HashNode head = bucketArray[bucketIndex];
		HashNode current = head;	
		while(current !=null) {
			//checking if key already exists and if it does, update it with new value
			if (current.key.equals(key)) {
				current.value = value;
				return;
			}
			current = current.next;
		}
		//adding key-value pair!
		numOfPairs++;		//incrementing numOfPairs
		HashNode node = new HashNode(key,value);	//creating new node with key-value pair you want to insert
		node.next = head;	//inserting at the beginning of the linked list
		bucketArray[bucketIndex] = node;
	}
	
	/**
	 * Hash function using modulo
	 * @param key
	 * @return
	 */
	private int getBucketIndex(Integer key) {
		return key % numOfBuckets;	//hash function using modulo
	}
	
	//Time Complexity of O(1)
	/**
	 * Given a key, it returns its value.
	 * @param key
	 * @return
	 */
	public String get(String key) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		int intKey = Integer.parseInt(key);	//converting key string to integer
		int bucketIndex = getBucketIndex(intKey);
		HashNode head = bucketArray[bucketIndex];
		HashNode current = head;
		while (current != null) {
			if (current.key.equals(key)) {
				return current.value;
			}
			current = current.next;
		}
		return null;
	}
	
	//Time Complexity of O(1)
	/**
	 * Removes key-value pair given a key.
	 * @param key
	 * @return
	 */
	public String remove(String key) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		int intKey = Integer.parseInt(key);	//converting key string to integer
		int bucketIndex= getBucketIndex(intKey);
		HashNode head = bucketArray[bucketIndex];
		HashNode prev = null;
		HashNode current = head;
		while(current != null) {
			if (current.key.equals(key)) {
				break;	//current is pointing to key you want to remove
			}
			prev = current;
			current = current.next;
		}
		if (current == null) {
			return null;	//could not find key!
		}
		
		numOfPairs--;		//decrementing numOfPairs
		if (prev !=null) {
			prev.next = current.next;	//removing element from linked list
		} else {	
			bucketArray[bucketIndex] = head.next;	//removing head of the linked list
		}
		
		
		return current.value;	//returning value of the key that was removed!
	}
	
	/**
	 * Returns all the keys of the hash table
	 * @param obj
	 */
	public ArrayList<String> getAllKeys() {
		ArrayList<String> listOfKeys = new ArrayList<String>();		
		for (int i=0; i < numOfBuckets; i++) {	//iterating through entire array
			HashNode head = bucketArray[i];
			HashNode current = head;
			while (current != null) {
				String currentKey= current.getKey();
				//System.out.println(currentKey);
				//int intCurrentKey = Integer.parseInt(currentKey);	//changing strings of keys into integers before putting in list and sorting
				listOfKeys.add(currentKey);
				current = current.next;
			} //end of while
		} //end of for-loop
		Collections.sort(listOfKeys);
		return listOfKeys;
	}
	
	/**
	 * Returns the successor (the next key) of any given key
	 * @param key
	 * @return
	 */
	public String getNextKey(String key) {
		//Getting every key and sorting it
		ArrayList<String> listOfKeys = new ArrayList<String>();
		for (int i=0; i < numOfBuckets; i++) {	//iterating through entire array
			HashNode head = bucketArray[i];
			HashNode current = head;
			while (current != null) {
				String currentKey= current.getKey();
				//int intCurrentKey = Integer.parseInt(currentKey);	//changing strings of keys into integers before putting in list and sorting
				listOfKeys.add(currentKey);
				current = current.next;
			} 
		} 
		Collections.sort(listOfKeys);	//sorting
		//Getting successor of given key
		if (listOfKeys.contains(key)) {
			int indexOfKey = listOfKeys.indexOf(key);
			int indexOfNextKey = indexOfKey +1;
			if (indexOfNextKey >= listOfKeys.size()) {
				throw new IllegalArgumentException("Key has no successor!");
			}
			String nextKey = listOfKeys.get(indexOfNextKey);
			return nextKey;
		}
		else {
			throw new IllegalArgumentException("Key does not exist in hash table");
		}
	}
	
	/**
	 * Returns the predecessor (key that comes before) of any given key
	 * @param key
	 * @return
	 */
	public String getPrevKey(String key) {
		//Getting every key and sorting it
		ArrayList<String> listOfKeys = new ArrayList<String>();
		for (int i=0; i < numOfBuckets; i++) {	//iterating through entire array
			HashNode head = bucketArray[i];
			HashNode current = head;
			while (current != null) {
				String currentKey= current.getKey();
				listOfKeys.add(currentKey);
				current = current.next;
			} 
		} 
		Collections.sort(listOfKeys);	//sorting
		//Getting predecessor of given key
		if (listOfKeys.contains(key)) {
			int indexOfKey = listOfKeys.indexOf(key);
			int indexOfPrevKey = indexOfKey - 1;
			if (indexOfPrevKey == -1) {
				throw new IllegalArgumentException("Key has no predecessor!");
			}
			String prevKey = listOfKeys.get(indexOfPrevKey);
			return prevKey;
		}
		else {
			throw new IllegalArgumentException("Key does not exist in hash table");
		}
	}
	
	/**
	 * Returns the number of keys between key 1 and key 2 (not inclusive!)
	 * @param k1
	 * @param k2
	 * @return
	 */
	public int getRangeOfKeys(String k1, String k2) {
		//Getting every key and sorting it
		ArrayList<String> listOfKeys = new ArrayList<String>();
		for (int i=0; i < numOfBuckets; i++) {	//iterating through entire array
			HashNode head = bucketArray[i];
			HashNode current = head;
			while (current != null) {
				String currentKey= current.getKey();
				listOfKeys.add(currentKey);
				current = current.next;
			} 
		} 
		Collections.sort(listOfKeys);	//sorting
		//Getting the number of keys between k1 and k2
		if (listOfKeys.contains(k1) && listOfKeys.contains(k2)) {	//checking if the hash table contains these keys
			int indexOfk1 = listOfKeys.indexOf(k1);
			int indexOfk2 = listOfKeys.indexOf(k2);
			int diffOfKeys = (indexOfk2 - indexOfk1)-1;
			//System.out.println(diffOfKeys);
			return diffOfKeys;
		} else {
			throw new IllegalArgumentException("One or two of the keys does not exist in hash table!");
		}
	}	//end of getRangeOfKeys
	
	
	
	/**
	 * Returns true if key exists in hash table. Otherwise, returns false.
	 * @param key
	 * @return
	 */
	public boolean keyExists(Integer key) {
		String strKey = Integer.toString(key);	//converting int key to string
		if (numOfPairs == 0) {	//no key-pair elements in hash table
			return false;
		}
		if (key == null) {	//key passed is null
			return false;
		}
		int bucketIndex = getBucketIndex(key);
		HashNode head = bucketArray[bucketIndex];
		HashNode current = head;
		while (current != null) {
			if (current.getKey().equals(strKey)) {
				return true;
			}
			current = current.next;
		}	//end of while
		return false;
	}	//end of keyExists method
	
	
	//****************************************************
	// MAIN METHOD
	//****************************************************
	public static void main(String[] args) {
		myHashTable table = new myHashTable(10);
		table.put("00123456", "value");
		table.put("12345678", "Sana");
		table.put("23456789", "Harry");	//(21,Sana) will get overwritten with this one
		table.put("34567891", "Danish");
		table.put("01123456", "hello");
		System.out.println(table.numOfPairs());
		System.out.println(table.isEmpty());
		table.put("12345678", "hello");
		System.out.println(table.get("12345678"));
		System.out.println(table.getAllKeys());
		System.out.println(table.getNextKey("00123456"));
		System.out.println(table.getPrevKey("34567891"));
		System.out.println(table.getRangeOfKeys("00123456", "12345678"));
	}
	
	
	   
}	//end of myHashTable Class