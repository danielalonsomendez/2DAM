package Ejercicio5_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class Ejercicio5_3 {

	public static void main(String[] args) {
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("empresa.json");
			FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
					.setProjectId("ad-bbdd-ejercicios").setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			Firestore db = options.getService();
			ApiFuture<QuerySnapshot> query = db.collection("departamentos").get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> departamentos = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot departamento : departamentos) {
				System.out.println("ID: " + departamento.getId());
				System.out.println("Nombre: " + departamento.getString("dpto_nom"));
				System.out.println("Localidad: " + departamento.getString("dpto_loc"));
				DocumentReference ref = departamento.getReference();
				List<QueryDocumentSnapshot> empleados = ref.collection("empleados").get().get().getDocuments();
				if (empleados.isEmpty()) {
					System.out.println("\tNo hay empleados en este departamento");
				}
				for (QueryDocumentSnapshot empleado : empleados) {
					System.out.println("\tID: " + empleado.getId());
					System.out.println("\tNombre: " + empleado.getString("emple_ap1"));
					System.out.println("\tComision: " + empleado.get("comision"));
					System.out.println("\tFecha alta: " + empleado.getTimestamp("fecha_alt"));
					System.out.println("\tOficio: " + empleado.getString("oficio"));
					System.out.println("\tSalario: " + empleado.getDouble("salario"));
					
					DocumentReference dirRef = (DocumentReference) empleado.get("dir");
					if (dirRef != null) {
						DocumentSnapshot dir = dirRef.get().get();
						System.out.println("\tDirector: " + dir.getString("emple_ap1") + " (" + dir.getId()+")");
					}
					System.out.println();
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
