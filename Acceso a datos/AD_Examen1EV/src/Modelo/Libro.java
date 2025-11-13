package Modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import Conexion.Conexion;

public class Libro {

	private String isbn;
	private String category;
	private String title;
	private String lang;
	private String author;
	private int year;
	private double price;
	private Date date;

	private final static String collectionName = "libros";
	private final static String fieldTitle = "title";
	private final static String fieldCategory = "category";
	private final static String fieldLang = "lang";
	private final static String fieldAuthor = "author";
	private final static String fieldYear = "year";
	private final static String fieldPrice = "price";
	private final static String fieldDate = "date";

	public Libro() {

	}

	public Libro(String isbn, String category, String title, String lang, String author, int year, double price,
			Date date) {
		super();
		this.isbn = isbn;
		this.category = category;
		this.title = title;
		this.lang = lang;
		this.author = author;
		this.year = year;
		this.price = price;
		this.date = date;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", category=" + category + ", title=" + title + ", lang=" + lang + ", author="
				+ author + ", year=" + year + ", price=" + price + ", date=" + date + "]";
	}

	public static ArrayList<Libro> obtenerLibros() {

		Firestore conexion = null;

		ArrayList<Libro> lista = new ArrayList<Libro>();

		try {
			conexion = Conexion.conectar();
			ApiFuture<QuerySnapshot> query = conexion.collection(Libro.collectionName).get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> libros = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot libro : libros) {
				Libro l = new Libro();
				l.setIsbn(libro.getId());
				l.setCategory(libro.getString(Libro.fieldCategory));
				l.setTitle(libro.getString(Libro.fieldTitle));
				l.setAuthor(libro.getString(Libro.fieldAuthor));
				l.setLang(libro.getString(Libro.fieldLang));
				l.setYear(libro.getLong(Libro.fieldYear).intValue());
				l.setPrice(libro.getDouble(Libro.fieldPrice));
				l.setDate(libro.getDate(Libro.fieldDate));
				lista.add(l);
			}
			conexion.close();

		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Error");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	public static ArrayList<Libro> obtenerLibrosWeb() {

		Firestore conexion = null;

		ArrayList<Libro> lista = new ArrayList<Libro>();

		try {
			conexion = Conexion.conectar();
			ApiFuture<QuerySnapshot> query = conexion.collection(Libro.collectionName).whereEqualTo(fieldCategory, "WEB").whereGreaterThan(fieldPrice, 29.95).get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> libros = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot libro : libros) {
				Libro l = new Libro();
				l.setIsbn(libro.getId());
				l.setTitle(libro.getString(Libro.fieldTitle));
				l.setYear(libro.getLong(Libro.fieldYear).intValue());
				lista.add(l);
			}
			conexion.close();

		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Error");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}
	public void anadir() {
		Map<String, Object> datos = new HashMap<>();
		datos.put(fieldTitle, title);
		datos.put(fieldCategory, category);
		datos.put(fieldAuthor, author);
		datos.put(fieldLang, lang);
		datos.put(fieldYear, year);
		datos.put(fieldDate,date);
		datos.put(fieldPrice, price);
		
		Firestore conexion;
		try {
			conexion = Conexion.conectar();
			conexion.collection("libros").document(isbn).set(datos).get();
			conexion.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
