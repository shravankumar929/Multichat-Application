/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbotapp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author student
 */
public class saveConfig {
    public static String getconfigFileLocation() throws IOException{
        String filename;
        filename = "/home/"+ saveConfig.getUsername()+"/.config/chatapp.xml";
        return filename;
    }
    public static String getUsername() throws IOException{
        Process child = Runtime.getRuntime().exec("whoami");
        InputStream in = child.getInputStream();
        int c;
        StringBuilder sb = new StringBuilder();
        while ((c = in.read()) != -1) {
            sb.append((char) c);
        }
        String str = sb.toString();
        return str.trim();
    }
    public static void createFile() throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
        String filename = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("config");
        doc.appendChild(rootElement);
        Element nickName = doc.createElement("nickname");
        nickName.appendChild(doc.createTextNode("student"));
        rootElement.appendChild(nickName);
        Element gender = doc.createElement("gender");
        gender.appendChild(doc.createTextNode("Male"));
        rootElement.appendChild(gender);
        Element directory = doc.createElement("directory");
        directory.appendChild(doc.createTextNode("/home/student/Downloads"));
        rootElement.appendChild(directory);
        Element aboutMe = doc.createElement("aboutme");
        aboutMe.appendChild(doc.createTextNode("I am single"));
        rootElement.appendChild(aboutMe);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);

    }
    static void setNickName(String nickName) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("nickname".equals(node.getNodeName())) {
			node.setTextContent(nickName);
		   }

	}
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
        
    }

    static void setGender(String gender) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("gender".equals(node.getNodeName())) {
			node.setTextContent(gender);
		   }

	}
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
    }

    static void setDownloadDirectory(String downloadDirectory) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("directory".equals(node.getNodeName())) {
			node.setTextContent(downloadDirectory);
		   }

	}
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
    }

    static void setAboutMe(String aboutMe) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("aboutme".equals(node.getNodeName())) {
			node.setTextContent(aboutMe);
		   }

	}
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filepath));
        transformer.transform(source, result);
    }
    public static String getNickName() throws IOException, ParserConfigurationException, TransformerException, SAXException{
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        String nickName = "";
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("nickname".equals(node.getNodeName())) {
			nickName = node.getTextContent();
		   }

	}
        return nickName;
 }
    public static String getGender() throws IOException, ParserConfigurationException, SAXException, TransformerException, TransformerException{
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        String gender = "";
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("gender".equals(node.getNodeName())) {
			gender = node.getTextContent();
		   }

	}
        return gender;
    }
    public static String getDownloadDirectory() throws IOException, ParserConfigurationException, TransformerException, SAXException{
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        String directory = "";
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("directory".equals(node.getNodeName())) {
			directory = node.getTextContent();
		   }

	}
        return directory;
    }
    public static String getAboutMe() throws IOException, ParserConfigurationException, SAXException, TransformerException{
        File fp = new File(saveConfig.getconfigFileLocation());
        if(fp.exists()==false) saveConfig.createFile();
        String filepath = saveConfig.getconfigFileLocation();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(filepath);
        Node staff = doc.getElementsByTagName("config").item(0);
        NodeList list = staff.getChildNodes();
        String aboutMe = "";
        for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
		   if ("aboutme".equals(node.getNodeName())) {
			aboutMe = node.getTextContent();
		   }

	}
        return aboutMe;
    }
}
