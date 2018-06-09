import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {

	public static void main(String[] args) {
		try {
			
			//DOM to analytic xml file
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("languages.xml"));
			
			Element root = document.getDocumentElement();
			System.out.println("cat = " + root.getAttribute("cat"));
			
			NodeList list = root.getElementsByTagName("lan");
			for (int i = 0; i < list.getLength(); i++) {
				//item() return Node class, Node is super class of Element
				Element lan = (Element) list.item(i);
				System.out.println("------------");
				System.out.println("id = " + lan.getAttribute("id"));
				
				NodeList children = lan.getChildNodes();
				for (int j = 0; j < children.getLength(); j++) {
					Node child = children.item(j);
					if (child instanceof Element) {
						System.out.println(child.getNodeName() + " = " + child.getTextContent());
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

}
