import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ElasticERLDriver {

	public static void main(String[] args) {

		/*
		ElasticERL elastic = null;
		Object obj = ElasticERL.setEINThreshold(6);
		elastic = (ElasticERL)obj;
		System.out.println(elastic.getSizeOfERL());
		elastic.add(elastic, "33240013", "value");
		elastic.add(elastic, "00255593", "value9");
		elastic.add(elastic, "86960590", "value");
		elastic.add(elastic, "87800000", "value@878");
		elastic.add(elastic, "90099499", "value");
		elastic.add(elastic, "70049505", "value");
		*/
		
		//elastic.add(elastic, 901, "value"); 
		/*
		System.out.println();
		elastic.allKeys(elastic);
		System.out.println();
		
		System.out.println("\nValue retrieved from getValue(key): " + elastic.getValues(elastic, "87800000") + "\n");
		System.out.println("Get next of key=900: " + elastic.nextKey(elastic, "86960590"));
		System.out.println("\nKeys between 191 and 700: (Assume keys exist): " + elastic.rangeKey(elastic, "86960590", "00255593")); //IS RETURNING SIZE OF ADT WHEN THE KEYS DONT EXIST
		System.out.println("Root of tree: " + elastic.avltree.getRoot().getKey());
		System.out.println("Parent of key=87800000: " + elastic.prevKey(elastic, "87800000") + "\n");
		System.out.println("Parent of key=33240013: " + elastic.prevKey(elastic, "33240013") + "\n");
		System.out.println("\nGenerated key is: " + elastic.generate(elastic) + "\n"); //the size of the ElasticERLSize must be accurate 
																			  //to the amount of elements inserted into the ADT																			  //to the amount of elements inserted into the ADT
																			  //or else null pointer exception bc the index at that
																			  //pt in the array is null
		System.out.println();
		elastic.remove(elastic, "86960590"); //Good for keys that don't exist
		elastic.allKeys(elastic);
		System.out.println("\nroot after delete 878: " + elastic.avltree.root.key);
		System.out.println();
		elastic.remove(elastic, "70049505");
		elastic.allKeys(elastic);
		System.out.println("\nroot after delete: " + elastic.avltree.root.key);
		*/
		
		/*
		 * Print welcome message
		 */
		System.out.println("***********************************************************");
		System.out.println("               Welcome to ELasticERL Driver ");
		System.out.println("***********************************************************\n");
		
		/*
		 * Read from each file and construct the appropriate ADT
		 */
		Scanner inFile = null;
		String line = "";
		String value = "";
		int nbOfEntries = 0;
		try {
	
			/*data_set1, read from file make ADT of size = 20*/
			inFile = new Scanner(new FileInputStream("data_set1"));
			ElasticERL erl = null;
			Object o = ElasticERL.setEINThreshold(20);
			erl = (ElasticERL)o;
			while(inFile.hasNextLine()) {
				line = inFile.nextLine();
				line = line.trim();
				value = "value@" + line;
				erl.add(erl, line, value);
				nbOfEntries++;
			}
			//test all methods
			System.out.println("\nFinished creating appropriate ADT. Number of entries and size of ADT is: " + nbOfEntries);
			System.out.println("Displaying all keys:"); 
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("The returned sequence is the array: " + erl.allKeys(erl));
			erl.remove(erl, "63537622");
			System.out.println("Deleting the key 63537622... Displaying all keys: "); erl.allKeys(erl);
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("Generating a new non-existent key: " + erl.generate(erl));
			System.out.println("Getting the value at 90049726: " + erl.getValues(erl, "90049726"));
			System.out.println("Root of AVL is: " + erl.getAvltree().getRoot().getKey() + ", right of root: " + erl.getAvltree().getRoot().getRight().getKey());
			System.out.println("Next key of 78804726 is: " + erl.nextKey(erl, "78804726"));
			System.out.println("Next key of 45241726 is: " + erl.prevKey(erl, "45241726"));
			System.out.println("Determining the number of keys between the range [78804726 - 96652545]: " + erl.rangeKey(erl, "78804726", "96652545"));
			
			
			/*data_set2, read from file make ADT of size = 600*/
			System.out.println("--------------------------------------------------------------");
			inFile = new Scanner(new FileInputStream("data_set2"));
			erl = null;
			o = ElasticERL.setEINThreshold(600);
			erl = (ElasticERL)o;
			nbOfEntries = 0;
			while(inFile.hasNextLine()) {
				line = inFile.nextLine();
				line = line.trim();
				value = "value@" + line;
				erl.add(erl, line, value);
				nbOfEntries++;
			}
			//test all methods
			//test all methods
			System.out.println("\nFinished creating appropriate ADT. Number of entries and size of ADT is: " + nbOfEntries);
			System.out.println("Displaying all keys:"); 
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("The returned sequence is the array: " + erl.allKeys(erl));
			erl.remove(erl, "08172280");
			System.out.println("Deleting the key 08172280... Displaying all keys: "); erl.allKeys(erl);
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("Generating a new non-existent key: " + erl.generate(erl));
			System.out.println("Getting the value at 22278157: " + erl.getValues(erl, "22278157"));
			//System.out.println("Root of AVL is: " + erl.getAvltree().getRoot().getKey() + ", right of root: " + erl.getAvltree().getRoot().getRight().getKey());
			//System.out.println("Next key of 78804726 is: " + erl.nextKey(erl, "78804726"));
			//System.out.println("Next key of 45241726 is: " + erl.prevKey(erl, "45241726"));
			//System.out.println("Determining the number of keys between the range [78804726 - 96652545]: " + erl.rangeKey(erl, "78804726", "96652545"));
			
			/*data_set3, read from file make ADT of size = 200*/
			System.out.println("--------------------------------------------------------------");
			inFile = new Scanner(new FileInputStream("data_set3"));
			erl = null;
			o = ElasticERL.setEINThreshold(200);
			erl = (ElasticERL)o;
			nbOfEntries = 0;
			while(inFile.hasNextLine()) {
				line = inFile.nextLine();
				line = line.trim();
				value = "value@" + line;
				erl.add(erl, line, value);
				nbOfEntries++;
			}
			//test all methods
			System.out.println("\nFinished creating appropriate ADT. Number of entries and size of ADT is: " + nbOfEntries);
			erl.allKeys(erl);
			
			/*data_set4, read from file make ADT of size = 800*/
			System.out.println("--------------------------------------------------------------");
			inFile = new Scanner(new FileInputStream("data_set4"));
			erl = null;
			o = ElasticERL.setEINThreshold(800);
			erl = (ElasticERL)o;
			nbOfEntries = 0;
			while(inFile.hasNextLine()) {
				line = inFile.nextLine();
				line = line.trim();
				value = "value@" + line;
				erl.add(erl, line, value);
				nbOfEntries++;
			}
			//test all methods
			System.out.println("\nFinished creating appropriate ADT. Number of entries and size of ADT is: " + nbOfEntries);
			erl.allKeys(erl);

			
		}
		catch(FileNotFoundException e) {
			System.out.println("File could not be found! Program will exit. ");
			System.exit(0);
		}
		catch(IOException e) {
			System.out.println("IO exception! Program will exit. ");
			System.exit(0);
		}
		finally {
			/*
			 * Close input file
			 */
			if(inFile != null)
				inFile.close();
			
			/*
			 * Print out message
			 */
			System.out.println("\n\n***********************************************************");
			System.out.println("                  End of ELasticERL Driver ");
			System.out.println("***********************************************************");
		}
		
		
		
		
		
		
		
	}

}
