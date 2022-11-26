

public class ElasticERL {
	
	/*
	 * variables and constants of ElasticERL
	 */
	ElasticAVL avltree = null;
	int sizeOfERL;
	//Doubly linked
	
	/*
	 * constructor
	 */
	public ElasticERL(int size) {
		avltree = new ElasticAVL();
		//other ADT
		sizeOfERL = size;
	}
	
	/*
	 * getters and setters
	 */
	public ElasticAVL getAvltree() { return avltree; }
	public void setAvltree(ElasticAVL avltree) { this.avltree = avltree; }
	public int getSizeOfERL() { return sizeOfERL; }
	public void setSizeOfERL(int sizeOfERL) { this.sizeOfERL = sizeOfERL; }
	
	/*
	 * class methods
	 */
	/**
	 * Create the appropriate ADT based on the size
	 * 
	 * @param size
	 * @return Object
	 */
	public static Object setEINThreshold(int size) {
		if(size < 1000) {
			return new ElasticERL(size); 
		} 
		else {
			//return creation of other ADT
			return new ElasticERL(size);
		}	
	}
	
	/**
	 * Return a sorted sequence of all the keys in the ADT
	 * 
	 * @param elasticERLObj
	 * @return
	 */
	public Object allKeys(ElasticERL elasticERLObj) {
		if(elasticERLObj.getSizeOfERL() < 1000) { //AVL Case
			//First sort the tree into an array by inorder traversal
			elasticERLObj.getAvltree().inorderTraversalAVL(avltree.getRoot(), elasticERLObj.getSizeOfERL());
			//Get the created array in the ElasticAVL class and upcast to Object array
			Object[] sortedArray = elasticERLObj.getAvltree().getSortedArray();
			return sortedArray;
		} 
		else {
			return null; //return sequence of other ADT
		}
	}
	
	/**
	 * Add an entry to the appropriate ADT
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @param value
	 */
	public void add(ElasticERL elasticERLObj, int key, String value) {
		if(elasticERLObj.getSizeOfERL() < 1000) { //if the size set for the ADT is <1000, insert into AVL tree
			avltree.setRoot(avltree.insert(key, avltree.getRoot(), value));
		} 
		else {
			
		}
	}
	
	/**
	 * Remove an entry from the appropriate ADT
	 * 
	 * @param elasticERLObj
	 * @param key
	 */
	public void remove(ElasticERL elasticERLObj, long key) {
		//NOT DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}
	
	/**
	 * Return the value at the indicated key in the ADT
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @return String
	 */
	public String getValues(ElasticERL elasticERLObj, long key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			return (elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot())).getValue();
		} 
		else {
			return "";
		}
	}
	
	/**
	 * Returns the key of the successor of the key passed
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @return long
	 */
	public long nextKey(ElasticERL elasticERLObj, long key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			System.out.println(elasticERLObj.getAvltree().getRoot().getKey());
			//System.out.println("left:" + elasticERLObj.getAvltree().getRoot().getLeft().getKey());
			//System.out.println("right:" + elasticERLObj.getAvltree().getRoot().getRight().getKey());
			//System.out.println("right:" + elasticERLObj.getAvltree().getRoot().getRight().getLeft().getKey());
			
			ElasticAVL.Node node = elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot());	
			if(node.getRight() == null) //reached an external node
				return 0;
			else 
				return node.getRight().getKey();
		} 
		else {
			return 1000;
		}
	}
	
	/**
	 * Returns the predecessor of the key passed
	 * 
	 * @param elasticERLObj
	 * @param key
	 * @return long 
	 */
	//FIX PARENT OF AVL + ADD IF PARENT IS NULL OR IF IS ROOT
	public long prevKey(ElasticERL elasticERLObj, long key) {
		if(elasticERLObj.getSizeOfERL() < 1000) {
			return (elasticERLObj.getAvltree().searchAVL(key, elasticERLObj.getAvltree().getRoot())).getParent().getKey();
		} 
		else {
			return 1000;
		}
	}
	
	public int rangeKey(ElasticERL elasticERLObj, long key1, long key2) {
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
					int temp = 0;
					temp = key1;
					key1 = key2;
					key2 = temp;
				}
			elasticERLObj.avltree.printArray();
				
				for(int i = 0; i < sortedArray.length; i++) {
					ElasticAVL.Node node = (ElasticAVL.Node)sortedArray[i];//downcast to Node in ElasticAVL
					if(node.getKey() >= key1)
						count++;
					if(node.getKey() == key2)
						break;
				}
				
			return count;
		} 
		else {
			return 1000;
		}
	}
	
	

	
	
	

}
