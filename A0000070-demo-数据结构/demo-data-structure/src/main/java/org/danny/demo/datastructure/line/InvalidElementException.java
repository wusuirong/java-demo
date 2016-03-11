package org.danny.demo.datastructure.line;

/**
 * 如果元素无效则报这个错误
 */
public class InvalidElementException extends Exception {
	
	public InvalidElementException() {
		super();
	}
	
	public InvalidElementException(String str) {
		super(str);
	}

}
