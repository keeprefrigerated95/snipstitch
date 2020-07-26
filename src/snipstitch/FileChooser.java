package snipstitch;

import java.io.File;

import javax.swing.JFileChooser;

public class FileChooser {
	public FileChooser () {
		/*
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			
		}
		 */
	}
	
	public String getFilename() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			//System.out.println(selectedFile.getAbsolutePath());
			return selectedFile.getName();
		}
		
		return "error";
		
	}
}
