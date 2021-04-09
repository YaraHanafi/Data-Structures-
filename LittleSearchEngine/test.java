import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test{
    //Testing my own stuff so remove later
	public static void main(String[] args)
	throws FileNotFoundException { 
	LittleSearchEngine d = new LittleSearchEngine();
	d.makeIndex("try.txt","noisewords.txt");
	System.out.println("\nHere's a list of all mappings:");
	Set<String> keys = d.keywordsIndex.keySet();  // Set is of type String since keys are Strings
	Iterator<String> iterator = keys.iterator();
	while (iterator.hasNext()) {
		String key = iterator.next();
		System.out.println(key + " --> " + d.keywordsIndex.get(key));
		
		
	}

	ArrayList<String> de = new ArrayList<String>();
	de = d.top5search("gg", "go");
	System.out.println("Arraylist contains: " + de.toString());
	
	/*LittleSearchEngine d = new LittleSearchEngine();
	ArrayList<Integer> x = new ArrayList<Integer>();
	int [] a = {12,10,7,5,3,1,11};
	x = d.insertLastOccurrence(a);
	System.out.println("Arraylist contains: " + x.toString());  */
	
}
}