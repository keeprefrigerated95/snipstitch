package snipstitch;


import java.awt.EventQueue;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JFileChooser;

public class run {
	
	Settings settings;
	
	public run() {
		try {
			settings = new Settings();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		Mainmenu mainmenu = new Mainmenu();		
	}
}
