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
	
	public Editor () {
		
	}
	
	public Editor(String newXml, String newVideo) {
		xmlName = newXml;
		videoName = newVideo;
	}
	
	//opens xml and loads to snippets vector
	public void uploadSnippets() throws ParserConfigurationException, SAXException, IOException {
		//Get Document Builder
		/*
		System.out.println("HELLO3");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        System.out.println("HELLO3.5");
        Document xmlFile = builder.parse(new File(xmlName));
        System.out.println("HELLO3.75");
        NodeList nodeList = xmlFile.getDocumentElement().getChildNodes();
        System.out.println("HELLO4");
        for (int i = 0; i < nodeList.getLength(); i++) {
        	System.out.println("HELLO5");
        	Node node = nodeList.item(i);
        	
        	if (node.getNodeType() == Node.ELEMENT_NODE) {
        		System.out.println("HELLO6");
        		
        		Element elem = (Element) node;
        		System.out.println("HELLO6.1");
        		String snippet = node.getAttributes().getNamedItem("snippet").getNodeValue();
        		System.out.println("HELLO6.2");
        		String description = elem.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue();
        		int startSecond = Integer.parseInt(elem.getElementsByTagName("startSecond").item(0).getChildNodes().item(0).getNodeValue());
        		int startMinute = Integer.parseInt(elem.getElementsByTagName("startMinute").item(0).getChildNodes().item(0).getNodeValue());
        		int startHour = Integer.parseInt(elem.getElementsByTagName("startHour").item(0).getChildNodes().item(0).getNodeValue());
        		int endSecond = Integer.parseInt(elem.getElementsByTagName("startSecond").item(0).getChildNodes().item(0).getNodeValue());
        		int endMinute = Integer.parseInt(elem.getElementsByTagName("startMinute").item(0).getChildNodes().item(0).getNodeValue());
        		int endHour = Integer.parseInt(elem.getElementsByTagName("startHour").item(0).getChildNodes().item(0).getNodeValue());
        		System.out.println("HELLO6.3");
        		snippets.add(new Snippet(description, startSecond, startMinute, startHour, endSecond, endMinute, endHour));
        		System.out.println("HELLO7");
        	}
        }
        */
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
	        		int endSecond = Integer.parseInt(elem.getElementsByTagName("startSecond").item(0).getChildNodes().item(0).getNodeValue());
	        		int endMinute = Integer.parseInt(elem.getElementsByTagName("startMinute").item(0).getChildNodes().item(0).getNodeValue());
	        		int endHour = Integer.parseInt(elem.getElementsByTagName("startHour").item(0).getChildNodes().item(0).getNodeValue());
	        		snippets.add(new Snippet(description, startSecond, startMinute, startHour, endSecond, endMinute, endHour));
		    	}
		    }
		    
		    
		} catch (Exception e) {
	        e.printStackTrace();
	       }
		
	}
	
	public void snip() {
		
	}
	
	public void stitch() {
		
	}
	
	public void displaySnippets() {
		for(int i = 0; i < snippets.size(); i++) {
			System.out.println(snippets.get(i).getDescription() + "Start: " + snippets.get(i).getStartTime() + " End: " + snippets.get(i).getEndTime() + "\n");
		}
	}
}

//is it an xml file and video file?
//is the xml file formatted correctly?
//are there times in the xml that don't match up with the video?
