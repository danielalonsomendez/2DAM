package Ej1_9;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class C {

	public static void main(String[] args) {

		try {
			// TODO Auto-generated method stub
			File inputFile = new File("cds.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			
			//Primer nodo
			Element catalog = (Element) doc.getElementsByTagName("CATALOG").item(0);	
			NodeList cds = catalog.getElementsByTagName("CD");
			for (int temp = 0; temp < cds.getLength(); temp++) {
				Node node = cds.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					if (eElement.getElementsByTagName("NUMBER").item(0) == null) {
						Element numero = doc.createElement("NUMBER");
						numero.appendChild(doc.createTextNode(temp+1+""));
						eElement.appendChild(numero);
					}
				
				}
			}

//			Creo transformer para exportar el resultado
			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);
			System.out.println("-----------Modified File-----------");
			//Lo saco por consola
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			//Creo un nuevo xml
			StreamResult result =	new StreamResult(new File("cds.xml")); 
			transformer.transform(source, result);

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

}
