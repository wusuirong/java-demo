package com.sherwin.application.scrabblet;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Administrator
 *
 */
public class Scrabblet extends Applet implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} 
/*	  *//**
	 * server 是与运行游戏服务器的Web服务器的连接
	 *//*
	private ServerConnection server;
	  *//**
	 * server的名字是ServerName
	 *//*
	private String serverName;
	  *//**
	 * bag是游戏中共用的字母袋。两个游戏参与者有各自的装字母块的口袋，这两个口袋被初始化为相同的随机序列以此保持同步。
	 *//*
	private Bag bag; //
	  *//**
	 * board是棋盘的副本。参加游戏的两个人各有一个棋盘，在每一个回合后，由游戏保持这两个棋盘的同步。
	 *//*
	private Board board;
	  *//**
	 * 如果不能访问网络服务器，则设置single标志，游戏可以在单用户模式下进行。
	 *//*
	private boolean single = false;
	  *//**
	 * 在轮到这一方操作时，布尔变量ourturn 为true 。
	 *//*
	private boolean ourturn;
	  *//**
	 * 如果游戏者找不到合适的单词，可以不在棋盘上放置任何字母方块的情况下连续按下两次Done按钮。
	 * 用seen_pass 变量标记是否已经按下过Done按钮。
	 *//*
	private boolean seen_pass = false;
	  *//**
	 * 为了管理与远程对手的棋盘间的同步，在theirs中保存了已选择的方块拷贝。
	 * 主要用theirs检查对手是否有作弊，因此小应用程序并不显示theirs的内容。
	 *//*
	private Letter theirs[] = new Letter[7]; 
	  *//**
	 * 自己的名字。
	 *//*
	private String name; 
	  *//**
	 * 对手的名字。
	 *//*
	private String others_name; 
	
	
	//以下是界面组件
	  *//**
	 * topPanel 保存prompt
	 *//*
	private Panel topPanel; 
	  private Label prompt; 
	  *//**
	 * namefield 保存在启动时得到的用户名
	 *//*
	private TextField namefield; 
	  *//**
	 * done 按钮用来指示回合的结束
	 *//*
	private Button done; 
	  *//**
	 * 用于输入对话的消息
	 *//*
	private TextField chat; 
	 
	  *//**
	 * idList 显示可选的对手
	 *//*
	private List idList; 
	  *//**
	 * challenge 按钮将自己和对手连接在一起
	 *//*
	private Button challenge; 
	  *//**
	 * ican Canvas 保存起始阶段显示的游戏名和版权声明
	 *//*
	private Canvas ican; 
	
	  
	  * 这个方法建立BorderLayout，找出是从哪个Internet主机上下载的这个小应用程序，然后创建一个splash 屏幕画布。 
	 * @see java.applet.Applet#init()
	 
	public void init() { 
		    setLayout(new BorderLayout()); 
		    serverName = getCodeBase().getHost(); 
		    if (serverName.equals("")) 
		      serverName = "localhost"; 
		    ican = new IntroCanvas(); 
		  } 
	
	   
	   * 在浏览器重新显示小应用程序所在的页面时调用start( ) 方法。在方法的开始部分的try
块用来捕获网络连接错误。如果能够建立一个新的ServerConnection，则意味着以前没有运
行过start( ) 方法，所以创建提示用户输入姓名的屏幕。在此时，经the splash screen, ican ，放
在窗口的中央。如果name非空，则意味着用户离开过这个页面，现在是重新返回到这个页
面的。游戏假定已经获得用户的名字，可以跳过nameEntered( )。当用户在名字输入区域敲
击返回时，调用这个nameEntered( )方法。在结尾处的validate( )  方法确保所有AWT组件都
被正确更新。 
如果产生异常，则认为网络连接失败，进入单用户模式。调用start_game( )启动游戏。 
	 * @see java.applet.Applet#start()
	 
	public void start() { 
		    try { 
		      showStatus("Connecting to " + serverName); 
		      server = new ServerConnection(this, serverName); 
		      server.start(); 
		      showStatus("Connected: " + serverName); 
		 
		      if (name == null) { 
		        prompt = new Label("Enter your name here:"); 
		        namefield = new TextField(20); 
		        namefield.addActionListener(this); 
		        topPanel = new Panel(); 
		        topPanel.setBackground(new Color(255, 255, 200)); 
		        topPanel.add(prompt); 
		        topPanel.add(namefield); 
		        add("North", topPanel); 
		        add("Center", ican); 
		      } else { 
		        if (chat != null) { 
		          remove(chat); 
		          remove(board); 
		          remove(done); 
		        } 
		        nameEntered(name); 
		      } 
		      validate(); 
		    } catch (Exception e) { 
		      single = true; 
		      start_Game((int)(0x7fffffff * Math.random())); 
		    } 
	  }
	
	  
	  * 在用户离开小应用程序所在页面时调用stop( ) 方法。这里，程序仅仅通知服务器用户已
经离开。如果在用户重新返回页面，则在start( ) 方法中重新创建网络连接。 
	 * @see java.applet.Applet#stop()
	 
	public void stop() { 
		   if (!single) 
		     server.quit(); 
		  } 
	
	  *//**
	   * 在新用户进入游戏时调用add( )方法。将用户名加入List对象。特别注意add( )方法的字
符串格式，在后面将使用这个字符串从选手列表中抽取ID。 
	 * @param id
	 * @param hostname
	 * @param name
	 *//*
	void add(String id, String hostname, String name) { 
		    delete(id); // in case it is already there. 
		    idList.add("(" + id + ")  " + name + "@" + hostname); 
		    showStatus("Choose a player from the list"); 
		  } 
	
	  *//**
	   * 在用户不想将自己标志为可选对手时调用delete( )方法。当用户退出或是选好对手准备
开始游戏时调用这个方法。在这个方法里，通过提取圆括号中的值在列表中逐个的寻找id
字符串。如果列表中没有名字（而且并没有开始游戏：bag == null ），则小应用程序显示一
个特殊的消息通知用户挂起，直至找到对手。 
	 * @param id
	 *//*
	void delete(String id) { 
		    for (int i = 0; i < idList.getItemCount(); i++) { 
		      String s = idList.getItem(i); 
		      s = s.substring(s.indexOf("(") + 1, s.indexOf(")")); 
		      if (s.equals(id)) { 
		        idList.remove(i); 
		        break; 
		      } 
		    } 
		    if (idList.getItemCount() == 0 && bag == null) 
		      showStatus("Wait for other players to arrive."); 
		  } 
	
	  *//**
	   * getName( ) 方法和delete( )很类似，除了它只是简单的提取名字然后返回。如果没有找
到id ，则返回null 。 
	 * @param id
	 * @return
	 *//*
	private String getName(String id) { 
		    for (int i = 0; i < idList.getItemCount(); i++) { 
		      String s = idList.getItem(i); 
		      String id1 = s.substring(s.indexOf("(") + 1, s.indexOf(")")); 
		      if (id1.equals(id)) { 
		        return s.substring(s.indexOf(" ") + 3, s.indexOf("@")); 
		      } 
		    } 
		    return null; 
		  } 
	
	 
	  *//**
	   * 在另一个用户向本地用户挑战时由ServerConnection调用challenge( ) 方法。本来可以将
这个方法实现得更为复杂，允许提示用户接受或是拒绝挑战，但是这个方法实现为自动的
接受挑战。注意，用来启动游戏的随机数初值，被传送到对手的accept( ) 方法中。这样，两
方初始化了相同随机状态的两个字母方块袋以确保游戏的同步性。调用server.delete( ) 确保
不再接受其他游戏者的挑战。同时注意，本地游戏者通过设置ourturn 为false 放弃了先手。 
	 * @param id
	 *//*
	void challenge(String id) { 
	    ourturn = false; 
	    int seed = (int)(0x7fffffff * Math.random()); 
	    others_name = getName(id);   // who was it? 
	    showStatus("challenged by " + others_name); 
	 
	    // put some confirmation here... 
	 
	    server.accept(id, seed); 
	    server.delete(); 
	    start_Game(seed); 
	  } 
	
	  *//**
	   * accept( ) 是远方用户回应刚才提到的server.accept( )调用的方法。与对手一样，必须调
用server.delete( ) 将自己从可选的游戏者列表中删除。通过将ourturn 设为true 这个用户首先开
始拼写。 
	 * @param id
	 * @param seed
	 *//*
	void accept(String id, int seed) { 
		    ourturn = true; 
		    others_name = getName(id); 
		    server.delete(); 
		    start_Game(seed); 
		  }

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} */
}
