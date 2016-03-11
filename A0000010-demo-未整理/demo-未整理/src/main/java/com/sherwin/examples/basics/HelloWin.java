/*
 * 基本java程序结构
 */
package com.sherwin.examples.basics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloWin { 
    public static void main(String[] args) {
    	JFrame frame = new JFrame();

    	JLabel label = new JLabel("Hello,win");
    	frame.add(label);
    	
    	frame.pack();
    	frame.show();
    }
}
