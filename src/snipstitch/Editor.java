/*************************************************************
 * EDITOR
 * the actual code for loading an xml, using it to make 
 * copies of sections of the video, make a concatenated copy of
 * all of the new clips and then delete all of the unwanted files
 ****************************************************************/

package snipstitch;

import java.util.Vector;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.SwingUtilities;
import javax.xml.parsers.*;
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
		
			@Override
			public void run() {
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
	}
	
	//stitch the snippets back together
	public void stitch() throws IOException, InterruptedException {
		//create txt file with mp4 files to concatenate
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
		String wholeFile = newVideoName + fileType;
		ProcessBuilder processBuilder = new ProcessBuilder(ffmpegPath, "-f", "concat", "-safe",
				 "0", "-i", fileName, "-c", "copy", wholeFile);
		Process process = processBuilder.inheritIO().start();
		process.waitFor();
	}
	
	//delete unwanted files
	public void cleanup() {
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
}
