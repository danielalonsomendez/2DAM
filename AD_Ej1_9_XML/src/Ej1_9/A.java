package Ej1_9;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class A {
	public static void main(String[] args) {
		try {

			File archivo = new File("cds.xml");
			DocumentBuilderFactory dbFactoria = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoria.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			doc.getDocumentElement().normalize();
			Element catalog = (Element) doc.getElementsByTagName("CATALOG").item(0);	
			NodeList cds = catalog.getElementsByTagName("CD");
			for (int temp = 0; temp < cds.getLength(); temp++) {
				Node nNode = cds.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println("\nCD: " +(temp+1));
					Element eElement = (Element) nNode;
					Node titulo = eElement.getElementsByTagName("TITLE").item(0);
					System.out.println("	Título: " + titulo.getTextContent());
					Node artista = eElement.getElementsByTagName("ARTIST").item(0);
					System.out.println("	Artista: " + artista.getTextContent());
					Node pais = eElement.getElementsByTagName("COUNTRY").item(0);
					System.out.println("	País: " + pais.getTextContent());
					Node sello = eElement.getElementsByTagName("COMPANY").item(0);
					System.out.println("	Sello: " + sello.getTextContent());
					Node precio = eElement.getElementsByTagName("PRICE").item(0);
					System.out.println("	Precio: " + precio.getTextContent());
					Node ano = eElement.getElementsByTagName("YEAR").item(0);
					System.out.println("	Año: " + ano.getTextContent());
				}
			}

		} catch (Exception e) {
			System.out.println("Error al leer el fichero XML.");
		}
	}
}
