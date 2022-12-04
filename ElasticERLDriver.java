import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ElasticERLDriver {

	public static void main(String[] args) {	
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
			System.out.println("\nFinished creating appropriate ADT (AVLTree) from 'data_set1.txt'. Number of entries and size of ADT is: " + nbOfEntries);
			System.out.println("Displaying all keys:"); 
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("\nThe returned sequence is the array: " + erl.allKeys(erl));
			erl.remove(erl, "63537622");
			System.out.println("Deleting the key 63537622... Displaying all keys: "); erl.allKeys(erl);
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("Generating a new non-existent key: " + erl.generate(erl));
			System.out.println("Getting the value at 90049726: " + erl.getValues(erl, "90049726"));
			System.out.println("Root of AVL is: " + erl.getAvltree().getRoot().getKey() + ", right of root: " + erl.getAvltree().getRoot().getRight().getKey());
			System.out.println("Next key of 78804726 is: " + erl.nextKey(erl, "78804726"));
			System.out.println("Previous key of 45241726 is: " + erl.prevKey(erl, "45241726"));
			System.out.println("Determining the number of keys between the range [78804726 - 96652545]: " + erl.rangeKey(erl, "78804726", "96652545"));
			
			
			/*data_set2, read from file make ADT of size = 600*/
			System.out.println("-----------------------------------------------------------");
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
			System.out.println("\nFinished creating appropriate ADT (AVLTree) from 'data_set2.txt'. Number of entries and size of ADT is: " + nbOfEntries);
			System.out.println("Displaying all keys:"); 
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("\nThe returned sequence is the array: " + erl.allKeys(erl));
			erl.remove(erl, "08172280");
			System.out.println("Deleting the key 08172280... Displaying all keys: "); erl.allKeys(erl);
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("Generating a new non-existent key: " + erl.generate(erl));
			System.out.println("Getting the value at 22278157: " + erl.getValues(erl, "22278157"));
			System.out.println("Root of AVL is: " + erl.getAvltree().getRoot().getKey() + ", right of root: " + erl.getAvltree().getRoot().getRight().getKey());
			System.out.println("Next key of 36195726 is: " + erl.nextKey(erl, "36195726"));
			System.out.println("Previous key of 37028865 is: " + erl.prevKey(erl, "37028865"));
			System.out.println("Determining the number of keys between the range [01276726 - 01741257]: " + erl.rangeKey(erl, "01276726", "01741257"));
			
			/*data_set3, read from file make ADT of size = 200*/
			System.out.println("-----------------------------------------------------------");
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
			System.out.println("\nFinished creating appropriate ADT (AVLTree) from 'data_set3.txt'. Number of entries and size of ADT is: " + nbOfEntries);
			System.out.println("Displaying all keys:"); 
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("\nThe returned sequence is the array: " + erl.allKeys(erl));
			erl.remove(erl, "93569136");
			System.out.println("Deleting the key 93569136... Displaying all keys: "); erl.allKeys(erl);
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("Generating a new non-existent key: " + erl.generate(erl));
			System.out.println("Getting the value at 72374414: " + erl.getValues(erl, "72374414"));
			System.out.println("Root of AVL is: " + erl.getAvltree().getRoot().getKey() + ", right of root: " + erl.getAvltree().getRoot().getRight().getKey());
			System.out.println("Next key of 37032136 is: " + erl.nextKey(erl, "37032136"));
			System.out.println("Previous key of 21558146 is: " + erl.prevKey(erl, "21558146"));
			System.out.println("Determining the number of keys between the range [31053414 - 33216297]: " + erl.rangeKey(erl, "31053414", "33216297"));
			
			/*data_set4, read from file make ADT of size = 800*/
			System.out.println("-----------------------------------------------------------");
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
			System.out.println("\nFinished creating appropriate ADT (AVLTree) from 'data_set4.txt'. Number of entries and size of ADT is: " + nbOfEntries);
			System.out.println("Displaying all keys:"); 
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("\nThe returned sequence is the array: " + erl.allKeys(erl));
			erl.remove(erl, "00508324");
			System.out.println("Deleting the key 00508324... Displaying all keys: "); erl.allKeys(erl);
			erl.getAvltree().displayAVL(erl.getAvltree().getRoot(), erl.getSizeOfERL());
			System.out.println("Generating a new non-existent key: " + erl.generate(erl));
			System.out.println("Getting the value at 00332454: " + erl.getValues(erl, "00332454"));
			System.out.println("Root of AVL is: " + erl.getAvltree().getRoot().getKey() + ", right of root: " + erl.getAvltree().getRoot().getRight().getKey());
			System.out.println("Next key of 33285671 is: " + erl.nextKey(erl, "33285671"));
			System.out.println("Previous key of 00576763 is: " + erl.prevKey(erl, "00576763"));
			System.out.println("Determining the number of keys between the range [00225903 - 00576763]: " + erl.rangeKey(erl, "00225903", "00576763"));

			
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
