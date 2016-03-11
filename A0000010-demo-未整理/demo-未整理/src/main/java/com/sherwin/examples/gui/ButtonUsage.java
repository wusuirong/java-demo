package com.sherwin.examples.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextField;

public class ButtonUsage {

	public void init() {
		Frame frame = new Frame();		//新建窗体
		Button btn = new Button("btn"); //新建按钮组件
		frame.add(btn);					//把按钮放入窗体
		frame.pack();					//整理窗体
		frame.setVisible(true);			//显示窗体
	}
	
	/*
	 * 执行入口
	 */
	static public void main(String[] args) {
		ButtonUsage buttonUsage = new ButtonUsage();
		buttonUsage.init();
	}

}
