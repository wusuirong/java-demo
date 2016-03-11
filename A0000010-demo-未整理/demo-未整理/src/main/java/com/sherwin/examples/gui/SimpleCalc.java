package com.sherwin.examples.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class SimpleCalc {
	
	/*
	 * 显示界面
	 */
	public void display() {
		Frame frame = new Frame();				//一个有标题栏，右上角按钮，右键菜单的窗口
		
		frame.setBackground(Color.gray);		//设置窗口背景色
		frame.setSize(300,200);					//设置窗口默认大小
		frame.setLayout(new FlowLayout());		//设置窗口采用流式布局
		
		TextField adder1 = new TextField();		//新建被加数输入框，并添加到窗口里
		frame.add(adder1);
		
		Label plusSymbol = new Label("+");		//新建一个加号的标签，并添加到窗口里
		frame.add(plusSymbol);
		
		TextField adder2 = new TextField();		//新建加数输入框，并添加到窗口里
		frame.add(adder2);
		
		Button computeBtn = new Button("=");	//新建一个计算按钮，并添加到窗口里
		frame.add(computeBtn);
		
		TextField sum = new TextField();		//新建结果输出框，并添加到窗口里
		frame.add(sum);		
		
		frame.pack();							//pack 字面就是打包，捆紧，就是让窗口根据内部组件大小智能调整大小，试试不用这行的效果
		frame.setVisible(true);					//显示窗口
	}
	
	/*
	 * 执行入口
	 */
	static public void main(String[] args) {
		SimpleCalc caculator = new SimpleCalc();
		caculator.display();
	}

}
