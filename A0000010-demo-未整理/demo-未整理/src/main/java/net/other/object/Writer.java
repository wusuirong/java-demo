package net.other.object;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Writer implements Serializable {
	
	int i;
	
	public void hello() {
		System.out.println("hello");
	}
	
	String s;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("e:/oo.dat"));
		
		Writer w = new Writer();
		w.i = 12;
		w.s = "man";		
		oos.writeObject(w);
		
//		w = new Writer();
//		w.i = 34;
//		w.s = "woman";		
//		oos.writeObject(w);
		
		oos.close();
	}

}
