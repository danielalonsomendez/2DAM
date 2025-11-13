package GeneraLibrosXML;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Modelo.Libro;

public class GeneraLibrosXML {

	public static void main(String[] args) {
		ArrayList<Libro> libros = Libro.obtenerLibros();
		DateFormat formatFecha = new SimpleDateFormat("dd-MM-yyyy");
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element librosElement = doc.createElement("libros");
			for (int i = 0; i < libros.size(); i++) {
				Libro libro = libros.get(i);
				Element libroElement = doc.createElement("libro");
				// Atributos
				Element title = doc.createElement("title");
				Element isbn = doc.createElement("isbn");
				Element category = doc.createElement("category");
				Element author = doc.createElement("author");
				Element year = doc.createElement("year");
				Element date = doc.createElement("date");
				Element lang = doc.createElement("lang");
				Element price = doc.createElement("price");
				// Ponerles valor
				title.appendChild(doc.createTextNode(libro.getTitle()));
				isbn.appendChild(doc.createTextNode(libro.getIsbn()));
				category.appendChild(doc.createTextNode(libro.getCategory()));
				author.appendChild(doc.createTextNode(libro.getAuthor()));
				year.appendChild(doc.createTextNode(libro.getYear() + ""));
				date.appendChild(doc.createTextNode(formatFecha.format(libro.getDate())));
				lang.appendChild(doc.createTextNode(libro.getLang()));
				price.appendChild(doc.createTextNode(libro.getPrice() + ""));
				// Anadirlo a lista libros
				librosElement.appendChild(libroElement);
				// Anadir elementos al libro
				libroElement.appendChild(title);
				libroElement.appendChild(isbn);
				libroElement.appendChild(category);
				libroElement.appendChild(author);
				libroElement.appendChild(year);
				libroElement.appendChild(date);
				libroElement.appendChild(lang);
				libroElement.appendChild(price);

			}

			doc.appendChild(librosElement);
			// Guardar archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("BookStore.xml"));
			transformer.transform(source, result);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
