package snipstitch;

import java.util.Vector;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.xml.parsers.*;

import java.awt.BorderLayout;
import java.io.*;

public class Editor {
	OperatingSys OS;
	Vector<Snippet> snippets = new Vector<>();
	Vector<Snippet> unSnippets = new Vector<>();
	String xmlName;
	String videoName;
	String stitchFiles = new String("concat:\"");
	Vector<String> filesToStitch = new Vector<>();
	Vector<String> filesToDelete = new Vector<>();
	String fileType = new String();
	String newVideoName = new String("newVideo");
	String ffmpegPath = new String();
	String statusMessage = new String("Standby");
	String uploadInfo = new String("ready...\n");
	public Editor () {
	}
	
	public Editor(String newXml, String newVideo, String newFileType, String ffmpegInput, String newNewVideoName) {
		xmlName = newXml;
		videoName = newVideo;
		fileType = newFileType;
		ffmpegPath = ffmpegInput;
		newVideoName = newNewVideoName;
		
		SwingUtilities.invokeLater(new Runnable() {

			JFrame frame;
			JPanel panel;
			JScrollPane scrollPane;
			JTextArea allMessages;
			String messageString;
			
			@Override
			public void run() {
				//frame = new JFrame("Snip-Stitch Test");
				frame.setBounds(100, 100, 450, 300);
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				scrollPane = new JScrollPane();
				frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
				
				allMessages = new JTextArea(messageString);
				scrollPane.setViewportView(allMessages);
			}
		});
		
		
	}
	
	//opens xml and loads to snippets vector
	public void uploadSnippets() throws ParserConfigurationException, SAXException, IOException {
		//https://www.javatpoint.com/how-to-read-xml-file-in-java
		try {
			File xmlFile = new File(xmlName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(xmlFile);

		    //optional, but recommended
		    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		    doc.getDocumentElement().normalize(); 
		    NodeList nodeList = doc.getElementsByTagName("snippet");
		    
		    for (int i = 0; i < nodeList.getLength(); i++) {
		    	Node node = nodeList.item(i);
		    	if (node.getNodeType() == Node.ELEMENT_NODE) {
		    		Element elem = (Element) node;
		    		String description = elem.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue();
		    		//start time
	        		int startHour = Integer.parseInt(elem.getElementsByTagName("sHr").item(0).getChildNodes().item(0).getNodeValue());
	        		int startMinute = Integer.parseInt(elem.getElementsByTagName("sMin").item(0).getChildNodes().item(0).getNodeValue());
	        		int startSecond = Integer.parseInt(elem.getElementsByTagName("sSec").item(0).getChildNodes().item(0).getNodeValue());
	        		
	        		//end time
	        		int endHour = Integer.parseInt(elem.getElementsByTagName("eHr").item(0).getChildNodes().item(0).getNodeValue());
	        		int endMinute = Integer.parseInt(elem.getElementsByTagName("eMin").item(0).getChildNodes().item(0).getNodeValue());
	        		int endSecond = Integer.parseInt(elem.getElementsByTagName("eSec").item(0).getChildNodes().item(0).getNodeValue());


	        		snippets.add(new Snippet(description, startSecond, startMinute, startHour, endSecond, endMinute, endHour));
		    	}
		    }
		} catch (Exception e) {
	        e.printStackTrace();
	       }
		/*
		//invert the snippets to prep for ffmpeg
		for(int i = 0; i < unSnippets.size(); i++) {
			String snippetName = new String("snippet " + (i + 1));
			if(unSnippets.get(i).getStartHour() == 0
					&& unSnippets.get(i).getStartMinute() == 0
					&& unSnippets.get(i).getStartSecond() == 0 && i == 0) {
				snippets.add(new Snippet(snippetName, unSnippets.get(i).getEndSecond(), unSnippets.get(i).getEndMinute(), unSnippets.get(i).getEndHour(),
					unSnippets.get(i + 1).getStartSecond(), unSnippets.get(i + 1).getStartMinute(), unSnippets.get(i + 1).getStartHour()));
			}
			
			else if(i == 0) {
				snippets.add(new Snippet(snippetName, 0, 0, 0,
						unSnippets.get(i).getStartSecond(), unSnippets.get(i).getStartMinute(), unSnippets.get(i).getStartHour()));	
			}
			
			else {
				snippets.add(new Snippet(snippetName, unSnippets.get(i).getEndSecond(), unSnippets.get(i).getEndMinute(), unSnippets.get(i).getEndHour(),
						unSnippets.get(i + 1).getStartSecond(), unSnippets.get(i + 1).getStartMinute(), unSnippets.get(i + 1).getStartHour()));
			}
		}
		*/	
	}
	//snips out all the clips from the main video
	public void snip() throws IOException, InterruptedException {
		
		for(int i = 0; i < snippets.size(); i++) {
			
			//https://stackoverflow.com/questions/9885643/ffmpeg-executed-from-javas-processbuilder-does-not-return-under-windows-7/9885717#9885717
			//ffmpeg -i 20sec.mp4 -ss 0:0:1 -to 0:0:5 -c copy foobar.mp4
			String newFile = "foobar" + String.valueOf(i) + fileType;
			//https://superuser.com/questions/42537/is-there-any-sudo-command-for-windows
			ProcessBuilder processBuilder = new ProcessBuilder(ffmpegPath, "-i", videoName, "-ss",
					snippets.get(i).getStartTime(), "-to", snippets.get(i).getEndTime(), newFile);
						
			Process process = processBuilder.inheritIO().start();
		    process.waitFor();
			
			//add to the list of files to be concat later
			filesToStitch.add(newFile);
			filesToDelete.add(newFile);
			
		}
		//System.out.println(stitchFiles);
	}
	
	//stitch the snippets back together
	public void stitch() throws IOException, InterruptedException {
		//create txt file with mp4 files to concatenate
		//https://www.w3schools.com/java/java_files_create.asp
		//uploadDisplay.appendToStatus("Stitching up the snippets\n");
		String fileName = "snippetsList.txt";
		filesToDelete.add(fileName);
		try {
		      File snippetsTxt = new File(fileName);
		      FileWriter writer = new FileWriter(fileName);
		      //makes the txt file with files to concatenate
		      for(int i = 0; i < filesToStitch.size(); i++) {
		    	  
		    	  if(i == filesToStitch.size() - 1) {
		    		  writer.write("file '" + filesToStitch.get(i) + "'");
		    	  }
		    	  
		    	  else {
		    		  writer.write("file '" + filesToStitch.get(i) + "'\n");
		    	  }
		      }
		      
		      writer.close();
		      
		} catch (IOException e) {
		      e.printStackTrace();
		      }
		//concatenate the files
		//https://stackoverflow.com/questions/7333232/how-to-concatenate-two-mp4-files-using-ffmpeg
		String wholeFile = newVideoName + fileType;
		ProcessBuilder processBuilder = new ProcessBuilder(ffmpegPath, "-f", "concat", "-safe",
				 "0", "-i", fileName, "-c", "copy", wholeFile);
		Process process = processBuilder.inheritIO().start();
		process.waitFor();
	}
	
	//delete unwanted files
	public void cleanup() {
		System.out.println("Cleaning up");
		for(int i = 0; i < filesToDelete.size(); i++) {
			File deleteThis = new File(filesToDelete.get(i));
			
			try {
				deleteThis.delete();
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setFileType(String newType) {
		fileType = newType;
	}
	
	public void displaySnippets() {
		for(int i = 0; i < snippets.size(); i++) {
			System.out.println(snippets.get(i).getDescription() + " Start: " + snippets.get(i).getStartTime() + " End: " + snippets.get(i).getEndTime() + "\n");
		}
	}	
}

//is it an xml file and video file?
//is the xml file formatted correctly?
//are there times in the xml that don't match up with the video?
