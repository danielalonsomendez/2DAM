import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Principal {

	public static void main(String[] args) {
		// --- Crear instancia de Retrofit ---
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://fakestoreapi.com") // URL base de la API
				.addConverterFactory(GsonConverterFactory.create()) // Conversor JSON <-> objetos Java
				.build();

		// --- Crear la interfaz de la API ---
		ProductsAPI api = retrofit.create(ProductsAPI.class);

		// --- PETICIÓN GET ---
		Call<List<Product>> llamadaGet = api.obtenerProductos();
		File file = new File("productos.json");
		Gson gson = new GsonBuilder().create();
		try {
			Response<List<Product>> respuestaGet = llamadaGet.execute();
			if (respuestaGet.isSuccessful() && respuestaGet.body() != null) {
				List<Product> listaProduct = respuestaGet.body();
				System.out.println("GET productos: " + listaProduct.size());
				for (Product p : listaProduct) {
					System.out.println(p.toString());
				}

				try (FileWriter writer = new FileWriter(file)) {
					gson.toJson(listaProduct, writer);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// LEER JSON
		Product primerProducto = null;
		try (Reader reader = new FileReader(file)) {
			Product[] products = gson.fromJson(reader, Product[].class);
			primerProducto = products[0];
			System.out.println("Primero: " + primerProducto.getTitle());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// --- PETICIÓN POST ---
		Call<Product> llamadaPost = api.crearProducto(primerProducto);
		Product productoCreado = null;
		try {
			Response<Product> respuestaPost = llamadaPost.execute();
			if (respuestaPost.isSuccessful() && respuestaPost.body() != null) {
				Product product = respuestaPost.body();
				productoCreado = product;
				System.out.println("POST id=" + product.getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// --- PETICIÓN PUT ---
		primerProducto.setTitle("UPDATED");
		Call<Product> llamadaPut = api.actualizarProducto(primerProducto.getId(),primerProducto);
		try {
			Response<Product> respuestaPut = llamadaPut.execute();

			if (respuestaPut.isSuccessful() && respuestaPut.body() != null) {
				Product product = respuestaPut.body();
				System.out.println("PUT title=" + product.getTitle());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// --- PETICIÓN DELETE ---
		Call<Void> llamadaDelete = api.eliminarProducto(productoCreado.getId());
		try {
			Response<Void> respuestaDelete = llamadaDelete.execute();
			System.out.println("DELETE code=" + respuestaDelete.code());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
