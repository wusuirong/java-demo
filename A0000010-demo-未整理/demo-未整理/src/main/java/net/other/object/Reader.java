package net.other.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Reader {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("e:/oo.dat"));
		
		Writer w = new Writer();
		
		w = (Writer)ois.readObject();
		System.out.println(w.i + w.s);
		
//		w = (Writer)ois.readObject();
//		System.out.println(w.i + w.s);
		
		ois.close();
	}

}
