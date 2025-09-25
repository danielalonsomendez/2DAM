import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class PruebaQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
		
		Document xpathDoc = db.parse("libro.xml");
		XPath xpath = XPathFactory.newInstance().newXPath();
		
	
		String expresion = "sum(//tienda/ventas/venta[product =//tienda/productos/producto[@venta=//tienda/dptos/dpto[nombre=\"Carnicería\"]/@id]/@id]/cantidad)";
		expresion = "//tienda/dptos/dpto[nombre='Carnicería']/@id";
		Node cantidadventasCarniceria = (Node) xpath.evaluate(expresion,  xpathDoc,XPathConstants.NODE);
		System.out.println("1. Cantidad de ventas de la carnicería: " + cantidadventasCarniceria.getNodeValue());

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
