package com.sherwin.pattern.mvc.view;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sherwin.pattern.mvc.model.intf.BPMObserver;
import com.sherwin.pattern.mvc.model.intf.BeatModel;
import com.sherwin.pattern.mvc.model.intf.BeatObserver;

public class ViewPanel implements BeatObserver, BPMObserver {

	public ViewPanel(BeatModel beatModel) {
		this.beatModel = beatModel;
		beatModel.registerObserver((BeatObserver)this);
		beatModel.registerObserver((BPMObserver)this);
	}
	
	public void display() {
		frame = new JFrame("显示器");
		Panel panel = new Panel();
		frame.add(panel);
		panel.setLayout(new BorderLayout());

		tf = new JTextField(Integer.toString(beatModel.getBPM()));
		panel.add(tf, BorderLayout.CENTER);
		
		frame.pack();
		frame.show();
	}

	public void bpmChanged() {
		tf.setText(Integer.toString(beatModel.getBPM()));
	}
	
	public void beated() {
		
	}
	
	private JFrame frame;
	private BeatModel beatModel;
	
	private JTextField tf;
}
