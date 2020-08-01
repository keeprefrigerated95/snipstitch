/***********************************************************
 * MAIN MENU
 * The menu that the user will so on opening the program
 * will have options to edit a video, or make an xml file
 ***********************************************************/
package snipstitch;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class Mainmenu {
	
	public Mainmenu() {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				JFrame frame = new JFrame("Snip-Stitch");
				JLabel welcome = new JLabel("Welcome to Snip-Stitch");
				JButton loaderButton = new JButton();
				
				//the main frame
				frame.setLayout(new GridLayout(3, 4));
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(420, 420);
				frame.setVisible(true);
				
				//welcome message
				frame.add(welcome);
				
				//button that takes you to the video editing menu
				loaderButton.setPreferredSize(new Dimension(50, 50));
				loaderButton.setText("Edit a Video");
				loaderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(false);
						Loadermenu loaderMenu = new Loadermenu();
					}
				});
				frame.add(loaderButton);
			}
			
		});
		

				
	}
}
