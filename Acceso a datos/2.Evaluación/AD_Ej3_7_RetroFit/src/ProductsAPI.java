import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface ProductsAPI {
    @GET("/products")
    Call<List<Product>> obtenerProductos();
    @POST("/products")
    Call<Product> crearProducto(@Body Product producto);
    
    @PUT("/products/{id}")
    Call<Product> actualizarProducto(@Path("id") int id,@Body Product producto);
    
    @DELETE("/products/{id}")
    Call<Void> eliminarProducto(@Path("id") int id);
}
