package Ejercicio5_3_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

import Modelo.Departamento;
import Modelo.Empleado;

public class Ejercicio5_3_1 {

	public static void main(String[] args) {
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("empresa.json");
			FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
					.setProjectId("ad-bbdd-ejercicios").setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			Firestore db = options.getService();
			ApiFuture<QuerySnapshot> query = db.collection("departamentos").get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> departamentosQ = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot departamento : departamentosQ) {
				Departamento d = new Departamento(departamento.getId(),departamento.getString("dpto_loc"),
						departamento.getString("dpto_nom"));
				departamentos.add(d);
				DocumentReference ref = departamento.getReference();
				List<QueryDocumentSnapshot> empleados = ref.collection("empleados").get().get().getDocuments();
				for (QueryDocumentSnapshot empleado : empleados) {
					Empleado e = crearEmpleado(empleado);
					d.addEmpleado(e);
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

		for (Departamento d : departamentos) {
			System.out.println(d);
			for (Empleado e : d.getEmpleados()) {
				System.out.println("\t" + e);
			}
		}

	}

	public static Empleado crearEmpleado(DocumentSnapshot empleado) throws InterruptedException, ExecutionException {
		Empleado e = new Empleado(empleado.getId(),empleado.getString("emple_ap1"), empleado.getDate("fecha_alt"),
				empleado.getString("oficio"), empleado.getDouble("salario"));
		
		if (empleado.getDouble("comision") != null) {
			e.setComision(empleado.getDouble("comision"));
		}
		DocumentReference dirRef = (DocumentReference) empleado.get("dir");
		if (dirRef != null) {
			e.setDir(crearEmpleado(dirRef.get().get()));
		}
		return e;
	}

}
