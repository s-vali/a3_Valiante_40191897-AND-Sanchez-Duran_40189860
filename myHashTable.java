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
		this.numOfBuckets = 10;	//default capacity
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
		private Integer key;
		private String value;
		private HashNode next;	//reference to the next HashNode
		
		//Constructor
		public HashNode(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
		//Setters and Getters
		public int getKey() {	
			return key;
		}
		
		public void setKey(Integer key) {
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
	public void put(Integer key, String value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Key or Value is null!");
		}
		int bucketIndex = getBucketIndex(key);
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
	public String get(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		int bucketIndex = getBucketIndex(key);
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
	public String remove(Integer key) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		
		int bucketIndex= getBucketIndex(key);
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
	public ArrayList<Integer> getAllKeys() {
		ArrayList<Integer> listOfKeys = new ArrayList<Integer>();
		for (int i=0; i < numOfBuckets; i++) {	//iterating through entire array
			HashNode head = bucketArray[i];
			HashNode current = head;
			while (current != null) {
				int currentKey= current.getKey();
				//System.out.println(currentKey);
				listOfKeys.add(currentKey);
				current = current.next;
			} //end of while
		} //end of for-loop
		Collections.sort(listOfKeys);
		return listOfKeys;
	} 
	
	//****************************************************
	// MAIN METHOD
	//****************************************************
	public static void main(String[] args) {
		myHashTable table = new myHashTable(10);
		table.put(105, "value");
		table.put(21, "Sana");
		table.put(21, "Harry");	//(21,Sana) will get overwritten with this one
		table.put(31, "Danish");
		System.out.println(table.numOfPairs());
		System.out.println(table.isEmpty());
		System.out.println(table.get(31));
		System.out.println(table.get(21));
		System.out.println(table.get(105));
		System.out.println(table.get(90));
		System.out.println(table.numOfPairs());
		System.out.println(table.remove(31));	
		System.out.println(table.numOfPairs());
		table.put(12345678, "hello");
		System.out.println(table.get(12345678));
		System.out.println(table.getAllKeys());
	}
	
	
	   
}	//end of myHashTable Class