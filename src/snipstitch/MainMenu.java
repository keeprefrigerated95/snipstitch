/***********************************************************
 * MAIN MENU
 * Goes to the loading menu and a help meny
 ***********************************************************/
package snipstitch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu {
	
	public MainMenu() {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				//the main frame
				JFrame frame = new JFrame("Snip-Stitch");
				frame.setBounds(100, 100, 450, 300);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//the main panel
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				//replaces the default java icon
				ImageIcon icon = new ImageIcon("scissors2.png");
				frame.setIconImage(icon.getImage());
				
				//welcome message
				JLabel welcome = new JLabel("Welcome to Snip-Stitch");
				welcome.setFont(new Font("Verdana", Font.PLAIN, 23));
				welcome.setBounds(77, 11, 282, 36);
				panel.add(welcome);
				
				//go do the loading/editor page
				JButton loaderButton = new JButton("Edit a Video");
				loaderButton.setBounds(158, 91, 100, 30);
				loaderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(false);
						Loadermenu loaderMenu = new Loadermenu();
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
							}
						});
						
					}
				});
				panel.add(loaderButton);
				
				
				//go to settings
				JButton settingsButton = new JButton("Help");
				settingsButton.setBounds(158, 149, 100, 30);
				settingsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(false);
						
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								HelpWindow helpWindow = new HelpWindow();
							}
						});
					}
				});
				panel.add(settingsButton);			
			}
		});	
	}
}
