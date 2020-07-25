package snipstitch;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class Mainmenu {
	
	JFrame frame;
	JButton loaderButton;
	Loadermenu loaderMenu;
	
	public Mainmenu() {
		frame = new JFrame("Snip-Stitch");
		frame.setLayout(new GridLayout(3, 4));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setVisible(true);
		
		JLabel welcome = new JLabel("Welcome to Snip-Stitch");
		frame.add(welcome);
		
		
		loaderButton = new JButton();
		loaderButton.setPreferredSize(new Dimension(50, 50));
		loaderButton.setText("Edit a Video");
		loaderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				loaderMenu = new Loadermenu();
			}
		});
		frame.add(loaderButton);
				
	}
}
