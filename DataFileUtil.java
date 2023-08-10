package MOBLIMA;

import java.io.*;
import java.util.Arrays;

/**
 * DataFileUtil Class
 * The dataFileUtil class mainly includes the function to access serialized files, 
 * and the function to load seat layout from data files ,etc.
 * 
 * @author lijiaqian
 *
 */
public class DataFileUtil {
	/**
	 * save object to data file
	 * @param obj
	 */
	public static void saveToFile(Object obj,String fn) {
	      try
	      {
	         FileOutputStream fileOut = new FileOutputStream(fn);
	         ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
	         
	         objOut.writeObject(obj);
	         objOut.close();
	         fileOut.close();
	         //System.out.printf("Serialized data is saved in /data/data.ser");
	      }catch(IOException e)
	      {
	          e.printStackTrace();
	      }
	}
	/**
	 * load object from data file
	 * @return object
	 */
	
	public static Object readFromFile(String fn) {

		  Object obj;
		  try{
			 FileInputStream fileIn = new FileInputStream(fn);
			 ObjectInputStream objIn = new ObjectInputStream(fileIn);
			 obj = objIn.readObject();
			 objIn.close();
			 fileIn.close();
			  
			 return obj;
		  }catch(IOException e){
			 e.printStackTrace();
			 return null;
		  }catch(ClassNotFoundException e){
		     e.printStackTrace();
		     return null;
		  }
	}
	
	/**
	 * load data from txt file
	 */
	
	 public static String[][] loadDataFromTxtFile(String fileName) {
		 	FileReader fReader = null;
		    String[][] result = new String[0][0];
		    
			try {
				fReader = new FileReader(fileName);
			    BufferedReader brStream  =new BufferedReader(fReader);			    
			    String str;
				str = brStream.readLine();
			    String[] lst;
			    int n;
			    
			    while ( str != null ) {
			    	lst=str.split("\t");			    	
			    	n=result.length+1;
			    	result=Arrays.copyOf(result, n);		    	
			    	result[n-1]=Arrays.copyOf(lst, lst.length);
			    	str = brStream.readLine();
			    }
			    brStream.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		} 
}
