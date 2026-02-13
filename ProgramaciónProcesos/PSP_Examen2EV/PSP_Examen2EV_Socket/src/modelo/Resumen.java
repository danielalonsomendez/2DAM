package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Resumen {

	public static String  generarResumen(String frase) {
		String resumen = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte bytes[] = frase.getBytes();
			md.update(bytes);
			
			byte resumenBytes[] = md.digest();
			resumen = new String(resumenBytes);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resumen;
	}
}
