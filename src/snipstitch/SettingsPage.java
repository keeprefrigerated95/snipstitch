package snipstitch;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SettingsPage {
	public SettingsPage() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				JFrame frame = new JFrame("Snip-Stitch");
				frame.setBounds(100, 100, 450, 300);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				/*
				//welcome message
				JLabel welcome = new JLabel("Welcome to Snip-Stitch");
				welcome.setFont(new Font("Verdana", Font.PLAIN, 23));
				welcome.setBounds(77, 11, 282, 36);
				panel.add(welcome);
				*/
				
				//go do the loading/editor page
				JButton loaderButton = new JButton("Edit a Video");
				loaderButton.setBounds(158, 91, 100, 30);
				loaderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(false);
						Loadermenu loaderMenu = new Loadermenu();
					}
				});
				panel.add(loaderButton);
				
				//go to settings
				JButton settingsButton = new JButton("Settings");
				settingsButton.setBounds(158, 149, 100, 30);
				settingsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//frame.setVisible(false);
						//Loadermenu loaderMenu = new Loadermenu();
					}
				});
				panel.add(settingsButton);					
			}
		});	
	}

}
