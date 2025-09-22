package Ej1_10;

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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ej1_10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();

			Document xpathDoc = db.parse("ventas.xml");
			XPath xpath = XPathFactory.newInstance().newXPath();

			// 1. Cantidad de ventas de la carnicería
			String expresion1 = "sum(//tienda/ventas/venta[producto =//tienda/productos/producto[@venta=//tienda/dptos/dpto[nombre='Carnicería']/@id]/@id]/cantidad)";
			double cantidadventasCarniceria = (double) xpath.evaluate(expresion1, xpathDoc, XPathConstants.NUMBER);
			System.out.println("1. Cantidad de ventas de la carnicería: \n" + cantidadventasCarniceria);

			// 2. Precio de productos de carnicería.
			String expresion2 = "//tienda/productos/producto[@venta=//tienda/dptos/dpto[nombre='Carnicería']/@id]/precio";
			NodeList precios = (NodeList) xpath.evaluate(expresion2, xpathDoc, XPathConstants.NODESET);

			System.out.println("2. Precio de productos de carnicería:");
			for (int i = 0; i < precios.getLength(); i++) {
				System.out.println(precios.item(i).getTextContent());
			}
			// 3. Nombre del producto del que se han vendido 3 unidades.
			String expresion3 = "//tienda/productos/producto[@id=//tienda/ventas/venta[cantidad=3]/producto]/nombre";
			NodeList nombres = (NodeList) xpath.evaluate(expresion3, xpathDoc, XPathConstants.NODESET);
			System.out.println("3. Nombre del producto del que se han vendido 3 unidades:");
			for (int i = 0; i < nombres.getLength(); i++) {
				System.out.println(nombres.item(i).getTextContent());
			}
			// 4. Responsable del producto con nombre de Naranjas.
			String expresion4 = "//tienda/dptos/dpto[@id=//tienda/productos/producto[nombre='Naranjas']/@venta]/responsable";
			Node responsableNaranjas = (Node) xpath.evaluate(expresion4, xpathDoc, XPathConstants.NODE);
			System.out.println(
					"4. Responsable del producto con nombre de Naranjas: \n" + responsableNaranjas.getTextContent());

			// 5. Responsable de la venta realizada el 10/03/2013.
			String expresion5 = "//tienda/dptos/dpto[@id=//tienda/productos/producto[@id=//tienda/ventas/venta[data='2013/3/10']/producto]/@venta]/responsable";
			Node responsableFecha = (Node) xpath.evaluate(expresion5, xpathDoc, XPathConstants.NODE);
			System.out.println(
					"5. Responsable de la venta realizada el 10/03/2013: \n" + responsableFecha.getTextContent());

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
