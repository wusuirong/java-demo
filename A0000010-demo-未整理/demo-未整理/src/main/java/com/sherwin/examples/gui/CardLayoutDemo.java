package com.sherwin.examples.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLayoutDemo extends Frame implements ActionListener {
	Panel cardPanel;
	CardLayout cardLayout;
	Button previousBtn, nextBtn;

	public CardLayoutDemo() {
		this.setLayout(new BorderLayout());

		Panel controlPanel = new Panel();
		this.add(controlPanel, BorderLayout.NORTH);
		
		previousBtn = new Button("previous");
		nextBtn = new Button("next");
		controlPanel.add(previousBtn);
		controlPanel.add(nextBtn);

		cardPanel = new Panel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);

		Label label1 = new Label("Label1");
		Panel panel1 = new Panel();
		panel1.add(label1);

		Label label2 = new Label("Label2");
		Panel panel2 = new Panel();
		panel2.add(label2);

		cardPanel.add(panel1, "panel1");
		cardPanel.add(panel2, "panel2");

		add(cardPanel, BorderLayout.CENTER);

		previousBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}

	public static void main(String[] args) {
		CardLayoutDemo f = new CardLayoutDemo();
		f.setTitle("卡片布局演示");
		f.pack();
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == previousBtn) {
			cardLayout.show(cardPanel, "panel1");
		} else {
			cardLayout.show(cardPanel, "panel2");
		}
	}

}
