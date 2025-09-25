package ejercicio11pro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Ejercicio11PRO {

	public static void main(String[] args) {
		JsonParser parser = new JsonParser();
		final String url = "stocks.json";

		try {
			FileReader fr = new FileReader(url);
			JsonElement datos = parser.parse(fr);
			imprimirElemento(datos,"");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void imprimirElemento(JsonElement elemento,String nombreAtributo) {
		if (elemento.isJsonObject()) {
			System.out.println("Objeto:");
			JsonObject objeto = elemento.getAsJsonObject();
			for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {
				imprimirElemento(entry.getValue(), entry.getKey());
			}
		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			System.out.println("Array: Número de elementos: " + array.size());
			for (JsonElement elem : array) {
				imprimirElemento(elem,"");
			}
		} else if (elemento.isJsonPrimitive()) {
			JsonPrimitive valor = elemento.getAsJsonPrimitive();
			System.out.println("Atributo: "+nombreAtributo );
			if (valor.isString()) {
				System.out.println("\tTexto: " + valor.getAsString());
			} else if (valor.isNumber()) {
				System.out.println("\tNúmero: " + valor.getAsDouble());
			} else if (valor.isBoolean()) {
				System.out.println("\tBooleano: " + valor.getAsBoolean());
			}
		}
	}
}