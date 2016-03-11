package org.sherwin.algorithm.string;

public interface CharString {
	
	public void clear();
	
	public boolean compare(CharString s);
	
	public int length();
	
	public CharString contact(CharString s);
	
	public CharString substr(int index, int length);
}
