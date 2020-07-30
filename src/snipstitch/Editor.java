package snipstitch;

import java.util.Vector;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class Editor {
	Vector<Snippet> snippets = new Vector<>();
	String xmlName;
	String videoName;
	Vector<String> vidsToStitch = new Vector<>();
	
	public Editor () {
		
	}
	
	public Editor(String newXml, String newVideo) {
		xmlName = newXml;
		videoName = newVideo;
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
	        		int startSecond = Integer.parseInt(elem.getElementsByTagName("startSecond").item(0).getChildNodes().item(0).getNodeValue());
	        		int startMinute = Integer.parseInt(elem.getElementsByTagName("startMinute").item(0).getChildNodes().item(0).getNodeValue());
	        		int startHour = Integer.parseInt(elem.getElementsByTagName("startHour").item(0).getChildNodes().item(0).getNodeValue());
	        		int endSecond = Integer.parseInt(elem.getElementsByTagName("endSecond").item(0).getChildNodes().item(0).getNodeValue());
	        		int endMinute = Integer.parseInt(elem.getElementsByTagName("endMinute").item(0).getChildNodes().item(0).getNodeValue());
	        		int endHour = Integer.parseInt(elem.getElementsByTagName("endHour").item(0).getChildNodes().item(0).getNodeValue());
	        		snippets.add(new Snippet(description, startSecond, startMinute, startHour, endSecond, endMinute, endHour));
		    	}
		    }
		    
		    
		} catch (Exception e) {
	        e.printStackTrace();
	       }
		
	}
	
	public void snipStitch() {
		
	}
	
	//snips out all the clips from the main video
	public void snip() throws IOException, InterruptedException {
		
		for(int i = 0; i < snippets.size(); i++) {
			//ffmpeg -i 20sec.mp4 -ss 0:0:1 -to 0:0:5 -c copy foobar.mp4
			//https://ffmpeg.org/faq.html#How-can-I-join-video-files_003f
			//ffmpeg cat foobar.mpg foobar.mpg > foobar2.mpg
			String command = "ffmpeg -i " + videoName + " -ss " + snippets.get(i).getStartTime() +
					" -to " + snippets.get(i).getEndTime() + " -c copy hominahomina" + i + ".mp4";
			
			String newFile = "foobar" + String.valueOf(i) + ".mp4";
			ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", videoName, "-ss",
					snippets.get(i).getStartTime(), "-to", snippets.get(i).getEndTime(), newFile);
			processBuilder.start();
			
			/*
			String input = "cmd /c start ffmpeg.exe /K" + command + " /K \"Start\"";
			
			try
	        { 
	            // Just one line and you are done !  
	            // We have given a command to start cmd 
	            // /K : Carries out command specified by string 
	           Runtime.getRuntime().exec(new String[] {input}); 
	  
	        } 
	        catch (Exception e) 
	        { 
	            System.out.println("HEY Buddy ! U r Doing Something Wrong "); 
	            e.printStackTrace(); 
	        }
	        */
		}
			
	}
	
	public void stitch() {
		for(int i = 0; i < vidsToStitch.size(); i++) {
			
		}
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
