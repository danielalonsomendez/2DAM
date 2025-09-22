package testDOM;

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Crear {

	public static void main(String[] args) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// Elemento raiz
			Element elementoRaiz = doc.createElement("coches");
			doc.appendChild(elementoRaiz);
//					Creo Hijo
			Element cochesU = doc.createElement("cochesutilitarios");
			elementoRaiz.appendChild(cochesU);
			// Modifico atributo
			Attr attr = doc.createAttribute("compania");
			attr.setValue("Hyundai");
			cochesU.setAttributeNode(attr);
			// Creo hijo y lo introduzco
			Element nCoche = doc.createElement("nombrecoche");
			Attr attrType = doc.createAttribute("tipo");
			attrType.setValue("4x4");
			nCoche.setAttributeNode(attrType);
			nCoche.appendChild(doc.createTextNode("Tucson"));
			cochesU.appendChild(nCoche);
			// Otro hijo
			Element nCoche1 = doc.createElement("nombrecoche");
			Attr attrType1 = doc.createAttribute("tipo");
			attrType1.setValue("normal");
			nCoche1.setAttributeNode(attrType1);
			nCoche1.appendChild(doc.createTextNode("Coupe"));
			cochesU.appendChild(nCoche1);
//					Creo Transformer para escribir
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			// Dom Source a traves del doc
			DOMSource source = new DOMSource(doc);
			// Streamresult con el File a crrar
			StreamResult result = new StreamResult(new File("coches.xml"));
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
