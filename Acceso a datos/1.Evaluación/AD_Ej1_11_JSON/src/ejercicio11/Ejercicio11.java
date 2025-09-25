package ejercicio11;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;



public class Ejercicio11 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonParser parser = new JsonParser();
		final String url = "stocks.json";

		try {

			FileReader fr = new FileReader(url);
			JsonElement datos = parser.parse(fr);

			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			System.out.println("Array. Numero de elementos: " + array.size());
			while (iter.hasNext()) {
				System.out.println("Objeto:");
				JsonElement entrada = iter.next();
				JsonObject objeto = entrada.getAsJsonObject();
				Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();
				
				JsonPrimitive valor = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println("Atributo: company\n\tTexto: " + valor.getAsString());
				
				JsonPrimitive valor2 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println("Atributo: description\n\tTexto: " + valor2.getAsString());
				
				double valor3 = iter2.next().getValue().getAsDouble();
				System.out.println("Atributo: initial_price\n\tNúmero: " + valor3);
				
				double valor4 = iter2.next().getValue().getAsDouble();
				System.out.println("Atributo: price_2002\n\tNúmero: " + valor4);						

				double valor5 = iter2.next().getValue().getAsDouble();
				System.out.println("Atributo: price_2007\n\tNúmero: " + valor5);	
				
				JsonPrimitive valor6 = iter2.next().getValue().getAsJsonPrimitive();
				System.out.println("Atributo: symbol\n\tTexto: " + valor6.getAsString());
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
