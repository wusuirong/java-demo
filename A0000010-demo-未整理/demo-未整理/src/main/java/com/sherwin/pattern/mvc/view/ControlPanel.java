package com.sherwin.pattern.mvc.view;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sherwin.pattern.mvc.control.BeatController;
import com.sherwin.pattern.mvc.model.intf.BeatModel;

public class ControlPanel extends JFrame {
	
	public ControlPanel(BeatController beatController) {
		this.beatController = beatController;
	}
	
	public void display() {
		frame = new JFrame("控制器");
		Panel panel = new Panel();
		frame.add(panel);
		
		panel.setLayout(new GridLayout(3,3));
		
		startBtn = new JButton("start");
		panel.add(startBtn);
		stopBtn = new JButton("stop");
		panel.add(stopBtn);
		volume = new JTextField("0");
		panel.add(volume);
		setVolBtn = new JButton("set");
		panel.add(setVolBtn);
		plusBtn = new JButton("+");
		panel.add(plusBtn);
		subBtn = new JButton("-");
		panel.add(subBtn);
		
		initEventHandler();
		frame.pack();
		frame.show();
	}
	
	private void initEventHandler() {
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beatController.start();
			}
		});
		
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beatController.stop();
			}
		});
		
		setVolBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bpm = Integer.valueOf(volume.getText());
				beatController.setBPM(bpm);
			}
		});
		
		plusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beatController.volumeUp();
			}
		});
		
		subBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beatController.volumeDown();
			}
		});
	}

	private JFrame frame;
	private BeatController beatController;
	private JButton startBtn;
	private JButton stopBtn;
	private JTextField volume;
	private JButton setVolBtn;
	private JButton plusBtn;
	private JButton subBtn;
}
