package com.sherwin.examples.gui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HandleEventSample extends Frame {
	
	public void init() {
		this.setSize(400, 300);									//设置窗口默认大小
		this.setLayout(new FlowLayout());						//布局管理器只能用一种，所以是setXX
		
		Button button = new Button("点击");
		button.setPreferredSize(new Dimension(100, 50));		//设置按钮最佳大小
		this.add(button);
		
		WindowCloseHandler wch = new WindowCloseHandler();
		this.addWindowListener(wch);							//窗口注册关闭处理器，因为可以有多个，所以是addXX
		
		/*
		 * 新建监听器时要把组件引用传给监听器
		 * 这样监听器处理事件时才能访问组件的属性和方法
		 * 以后介绍内部类时就可以让监听器直接访问组件的属性和方法
		 */
		ButtonClickHandler bch = new ButtonClickHandler(button);
		button.addActionListener(bch);							//按钮注册点击处理器
		
		MouseMoveHandler mmh = new MouseMoveHandler(button);
		button.addMouseListener(mmh);							//按钮注册鼠标动作处理器
	}
	
	static public void main(String[] args) {
		HandleEventSample be = new HandleEventSample();
		be.init();
		be.setVisible(true);
	}
}

/**
 * 窗口关闭事件处理器
 *
 * @author sherwin wu
 */
class WindowCloseHandler implements WindowListener {

	/**
	 * 我们只关心窗口关闭的事件，其他方法不关心，留空即可
	 * 看上去实现很多空方法很麻烦，所以java提供了监听器的默认实现--适配器，以后介绍
	 */
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

/**
 * 按钮点击处理器
 *
 * @author sherwin wu
 */
class ButtonClickHandler implements ActionListener {
	
	Button btn;
	
	public ButtonClickHandler(Button btn) {
		this.btn = btn;
	}

	public void actionPerformed(ActionEvent e) {
		btn.setLabel("被点击了");
	}
}

/**
 * 鼠标动作处理器
 *
 * @author sherwin wu
 */
class MouseMoveHandler implements MouseListener {
	
	Button btn;
	
	public MouseMoveHandler(Button btn) {
		this.btn = btn;
	}
	
	public void mouseEntered(MouseEvent e) {
		btn.setLabel("鼠标来了");
	}

	public void mouseExited(MouseEvent e) {
		btn.setLabel("鼠标走了");
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
