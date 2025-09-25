package testDOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Modificar {

	public static void main(String[] args) {

		try {
			// TODO Auto-generated method stub
			File inputFile = new File("Prueba.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			
			//Primer nodo
			Node coches = doc.getFirstChild();
			//Busqueda por nodo
			Node cocheUtilitario = doc.getElementsByTagName("cochesutilitarios").item(0); // update supercar attribute
			//Modifico atributo
			NamedNodeMap attr = cocheUtilitario .getAttributes();
			Node nodeAttr = attr.getNamedItem("compania");
			nodeAttr.setTextContent("Citroen");

			// Recorro los hijos (nombrecoche)
			NodeList list = cocheUtilitario.getChildNodes();

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					//Modifico el nombre
					if ("nombrecoche".equals(eElement.getNodeName())) {
						if ("Kadjar".equals(eElement.getTextContent())) {
							eElement.setTextContent("C3");
						}
					//Modifico el nombre
						if ("307".equals(eElement.getTextContent()))
							eElement.setTextContent("C5");
					}
				}
			}
			NodeList childNodes = coches.getChildNodes();
			//Recorro los coches
			for (int count = 0; count < childNodes.getLength(); count++) {
				Node node = childNodes.item(count);
				if ("cocheslujo".equals(node.getNodeName()))
					coches.removeChild(node);
				//Eliminado cocheslujo
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
			StreamResult result =	new StreamResult(new File("coches.xml")); 
			transformer.transform(source, result);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
