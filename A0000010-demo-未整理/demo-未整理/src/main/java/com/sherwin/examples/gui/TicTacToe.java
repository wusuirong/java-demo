package com.sherwin.examples.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe {
	
	JPanel topPanel;
	JFrame mainWindow;
	JPanel chessboardPanel;
	JPanel bottomPanel;
	JLabel hintLabel;
	JButton startGameBtn;
	JButton[] cells;
	
	int status;//目前状态
	
	static final int STOP = 1;
	static final int PLAY = 2;
	
	static final int WIN = 10;
	static final int DRAW = 11;
	static final int CONTINUE = 12;
	
	static final String USER_SYMBOL = "X";
	static final String COM_SYMBOL = "O";

	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
	}

	
	public TicTacToe() {
		initFields();
		initUI();
		initEventHandler();
	}
	
	/**
	 * 初始化成员变量
	 * 
	 * @author sherwin wu
	 */
	protected void initFields() {
		cells = new JButton[9];
		status = STOP;
	}
	
	/**
	 * 初始化界面
	 * 
	 * 建立窗体
	 * 上边放一个开始游戏按钮
	 * 中间放棋盘
	 * 下面是信息栏
	 * 
	 * @author sherwin wu
	 */
	protected void initUI() {
		mainWindow = new JFrame();	
		
		/*
		 * 初始化上部界面
		 */
		{
			startGameBtn = new JButton("开始游戏");
			startGameBtn.setBackground(Color.RED);
			topPanel = new JPanel();
			topPanel.add(startGameBtn);
		}

		/*
		 * 初始化棋盘
		 */
		{
			chessboardPanel = new JPanel();
			chessboardPanel.setBackground(Color.blue);
			chessboardPanel.setLayout(new GridLayout(3,3));
			
			for (int i=0; i<9; i++) {
				JButton cell = new JButton();
				if (i%2 == 0) {
					cell.setBackground(Color.yellow);
				} else {
					cell.setBackground(Color.green);
				}
				cell.setPreferredSize(new Dimension(50, 50));//设置显示时默认大小
				cell.setName("cell_" + i);//设置名称后，用户点击时才能判断点的是哪个棋格
				cell.setFont(new Font(null, Font.BOLD, 20));
				
				chessboardPanel.add(cell);
				cells[i] = cell;
			}
		}
		
		/*
		 * 初始化下部界面
		 */
		{
			bottomPanel = new JPanel();
			hintLabel = new JLabel("欢迎玩井字棋");
			hintLabel.setOpaque(true);
			hintLabel.setBackground(Color.RED);
			bottomPanel.add(hintLabel);
		}

		/*
		 * 组装整个界面
		 */
		JPanel mainPanel = null;
		{
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(topPanel, BorderLayout.NORTH);
			mainPanel.add(chessboardPanel, BorderLayout.CENTER);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		}
		
		mainWindow.setContentPane(mainPanel);
		mainWindow.pack();
		mainWindow.show();
	}
	
	/**
	 * 初始化事件处理
	 * 
	 * @author sherwin wu
	 */
	protected void initEventHandler() {
		
		/*
		 * 窗口关闭事件处理
		 */
		WindowListener wl = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};		
		mainWindow.addWindowListener(wl);
		
		/*
		 * 内部类
		 */
		class StartGameAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				status = PLAY;
				for (int i = 0; i<cells.length; i++) {
					cells[i].setText("");
				}
				hintLabel.setText("开始玩游戏啦");
			}
		}
		ActionListener al = new StartGameAction();
		startGameBtn.addActionListener(al);
		
		/*
		 * 为按钮增加点击事件
		 */
		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (status == STOP) {
					return;
				}
				Object o = e.getSource();
				if (o instanceof JButton) {
					JButton btn = (JButton)o;
					boolean ok = userTurn(btn);
					if (ok) {
						playGame();
					}					
				}
			}
		};
		for (int i = 0; i<cells.length; i++) {
			cells[i].addMouseListener(ml);
		}
	}

	/**
	 * 游戏流程
	 * 
	 * @author sherwin wu
	 */
	void playGame() {
		if (WIN == judge(USER_SYMBOL)) {
			handleWin(USER_SYMBOL);
		} else if (DRAW == judge(USER_SYMBOL)) {
			handleDraw();
		} else {
			computerTurn();
			if (WIN == judge(COM_SYMBOL)) {
				handleWin(COM_SYMBOL);
			} else if (DRAW == judge(COM_SYMBOL)) {
				handleDraw();
			}
		}
	}
	
	/**
	 * 用户下棋
	 * 
	 * @author sherwin wu
	 * @param btn
	 * @return 用户点击的格子是否空闲，不空闲则点击无效
	 */
	boolean userTurn(JButton btn) {		
		if ("".equals(btn.getText())) {
			btn.setText(USER_SYMBOL);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 电脑下棋
	 * 
	 * @author sherwin wu
	 */
	void computerTurn() {
		Random r = new Random();
		int i = r.nextInt(9);
		while (!"".equals(cells[i].getText())) {
			i = r.nextInt(9);
		}
		cells[i].setText(COM_SYMBOL);
	}

	/**
	 * 裁判判断
	 * 
	 * @author sherwin wu
	 * @param symbol
	 * @return WIN
	 *         DRAW
	 *         CONTINUE
	 */
	int judge(String symbol) {
		//检查每行
		for (int i=0; i<9; i=i+3)
		{
			if (symbol.equals(cells[i].getText())
				&& symbol.equals(cells[i+1].getText())
				&& symbol.equals(cells[i+2].getText()))
			{
					return WIN;
			}
		}
		
		//检查每列
		for (int i=0; i<3; i++)
		{
			if (symbol.equals(cells[i].getText())
				&& symbol.equals(cells[i+3].getText())
				&& symbol.equals(cells[i+6].getText()))
			{
					return WIN;
			}
		}
		
		//检查对角线
		if (symbol.equals(cells[0].getText())
			&& symbol.equals(cells[4].getText())
			&& symbol.equals(cells[8].getText()))
		{
				return WIN;
		}
		if (symbol.equals(cells[2].getText())
			&& symbol.equals(cells[4].getText())
			&& symbol.equals(cells[6].getText()))
		{
				return WIN;
		}
		
		//是否都填满了
		for (int i=0; i<9; i++) {
			if ("".equals(cells[i].getText())) {
				return CONTINUE;
			}
		}
		
		//没有空格子就平局
		return DRAW;

	}
	
	/**
	 * 处理胜利情景
	 * 
	 * @author sherwin wu
	 * @param c
	 */
	void handleWin(String symbol) {
		if (USER_SYMBOL.equals(symbol)) {
			hintLabel.setText("你胜利了");
		} else {
			hintLabel.setText("你失败了");
		}		
		status = STOP;
	}
	
	/**
	 * 处理平局情景
	 * 
	 * @author sherwin wu
	 */
	void handleDraw() {
		hintLabel.setText("打平");
		status = STOP;
	}
}
