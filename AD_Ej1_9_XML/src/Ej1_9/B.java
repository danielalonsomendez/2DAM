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

public class B {

	public static void main(String[] args) {

		try {
			File inputFile = new File("cds.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			
			Element catalog = (Element) doc.getElementsByTagName("CATALOG").item(0);		
			
			Element cd = doc.createElement("CD");
			Element titulo = doc.createElement("TITLE");
			Element artista = doc.createElement("ARTIST");
			Element pais = doc.createElement("COUNTRY");
			Element sello = doc.createElement("COMPANY");
			Element precio = doc.createElement("PRICE");
			Element ano = doc.createElement("YEAR");

			titulo.appendChild(doc.createTextNode("Titulo CD Prueba"));
			artista.appendChild(doc.createTextNode("Daniel Alonso"));
			pais.appendChild(doc.createTextNode("Espana"));
			sello.appendChild(doc.createTextNode("Spain"));
			precio.appendChild(doc.createTextNode("5.50"));
			ano.appendChild(doc.createTextNode("2025"));

			catalog.appendChild(cd);
			cd.appendChild(titulo);
			cd.appendChild(artista);
			cd.appendChild(pais);
			cd.appendChild(sello);
			cd.appendChild(precio);
			cd.appendChild(ano);
			
//					Creo Transformer para escribir
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// Dom Source a traves del doc
			DOMSource source = new DOMSource(doc);
			// Streamresult con el File a crrar
			StreamResult result = new StreamResult(inputFile);
			// Lo envio al archivo
			transformer.transform(source, result);
//					Lo saco por consola
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
