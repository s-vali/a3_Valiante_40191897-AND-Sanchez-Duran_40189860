
public class ElasticERLDriver {

	public static void main(String[] args) {

		ElasticERL elastic = null;
		Object obj = ElasticERL.setEINThreshold(10);
		elastic = (ElasticERL)obj;
		System.out.println(elastic.getSizeOfERL());
		elastic.add(elastic, 890, "value");
		elastic.add(elastic, 191, "value");
		elastic.add(elastic, 239, "value");
		elastic.add(elastic, 878, "value@878");
		elastic.add(elastic, 900, "value");
		elastic.add(elastic, 700, "value");
		
		//elastic.add(elastic, 901, "value");
		elastic.allKeys(elastic);
		//System.out.println("\nValue retrieved from getValue(key): " + elastic.getValues(elastic, 878));
		//System.out.println("Get next of key=900: " + elastic.nextKey(elastic, 900));
		System.out.println("Keys between 191 and 700: (Assume keys exist): " + elastic.rangeKey(elastic, 191, 700));
		////System.out.println("Parent of key=191: " + elastic.prevKey(elastic, 191));
		//i.avltree.inorderTraversal(i.avltree.getRoot(), 10);
		//MODIFY PARENT AFTER RESTRUCTURE
		
	}

}
