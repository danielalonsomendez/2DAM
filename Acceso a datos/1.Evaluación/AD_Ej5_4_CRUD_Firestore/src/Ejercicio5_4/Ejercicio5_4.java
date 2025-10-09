package Ejercicio5_4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;


public class Ejercicio5_4 {

	public static void main(String[] args) {
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("empresa.json");
			FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
					.setProjectId("ad-bbdd-ejercicios").setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			Firestore db = options.getService();

			// EJ 1
			DocumentReference depInformatica = insertarDepartamento(db);
			// EJ 2
			insertarEmpleado(db, depInformatica);
			// EJ 3
			modificarEmpleado(db);
			// EJ 4
			eliminarEmpleadosMadrid(db);
			// EJ 5
			eliminarEmpleadoMayorSalario(db);
			db.close();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static DocumentReference insertarDepartamento(Firestore db) throws InterruptedException, ExecutionException {
		CollectionReference departamentosRef = db.collection("departamentos");
		Map<String, Object> nuevoDepartamento = new HashMap<>();
		nuevoDepartamento.put("dpto_nom", "INFORMÁTICA");
		nuevoDepartamento.put("dpto_loc", "Bilbao");
		DocumentReference depRef = departamentosRef.document("50");
		depRef.set(nuevoDepartamento);
		System.out.println("Departamento INFORMÁTICA insertado");
		return depRef;
	}

	public static void insertarEmpleado(Firestore db, DocumentReference depto)
			throws InterruptedException, ExecutionException {
		CollectionReference empleadosRef = depto.collection("empleados");
		Map<String, Object> nuevoEmpleado = new HashMap<>();
		nuevoEmpleado.put("apellido", "Pérez");
		nuevoEmpleado.put("oficio", "DIRECTOR");
		nuevoEmpleado.put("salario", 2300);
		nuevoEmpleado.put("comision", 1000);
		Date fecha = new Date();
		nuevoEmpleado.put("fecha_alt", fecha);
		DocumentReference empRef = empleadosRef.document("1099");
		empRef.set(nuevoEmpleado);
		System.out.println("Empleado PÉREZ insertado en INFORMÁTICA");
	}
	public static void modificarEmpleado(Firestore db) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> query = db.collection("departamentos").document("20").collection("empleados")
				.whereEqualTo("emple_ap1", "GIL").get();
		List<QueryDocumentSnapshot> empleados = query.get().getDocuments();
		for (QueryDocumentSnapshot empleado : empleados) {
			Map<String, Object> datosEmpleado = empleado.getData();
			datosEmpleado.put("salario", 1300);
			Date fecha = new Date();
			long unDia = 24 * 60 * 60 * 1000;
			Date fechaAyer = new Date(fecha.getTime() - unDia);
			datosEmpleado.put("fecha_alt", fechaAyer);	
			DocumentReference empRef = empleado.getReference();
			empRef.update(datosEmpleado);
			System.out.println("Empleado GIL modificado");
		}
	}
	public static void eliminarEmpleadosMadrid(Firestore db) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> query = db.collection("departamentos").whereEqualTo("dpto_loc", "MADRID").get();
		List<QueryDocumentSnapshot> departamentos = query.get().getDocuments();
		for (QueryDocumentSnapshot departamento : departamentos) {
			ApiFuture<QuerySnapshot> queryEmpleados = departamento.getReference().collection("empleados").get();
			List<QueryDocumentSnapshot> empleados = queryEmpleados.get().getDocuments();
			for (QueryDocumentSnapshot empleado : empleados) {
				empleado.getReference().delete();
				System.out.println("Empleado " + empleado.getString("emple_ap1") + " eliminado");
			}
		}
	}
	public static void eliminarEmpleadoMayorSalario(Firestore db) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> query = db.collection("departamentos").whereEqualTo("dpto_nom", "CONTABILIDAD").get();
		List<QueryDocumentSnapshot> departamentos = query.get().getDocuments();
		for (QueryDocumentSnapshot departamento : departamentos) {
			ApiFuture<QuerySnapshot> queryEmpleados = departamento.getReference().collection("empleados").get();
			double maxSalario = 0;
			QueryDocumentSnapshot empMax = null;
			List<QueryDocumentSnapshot> empleados = queryEmpleados.get().getDocuments();
			for (QueryDocumentSnapshot empleado : empleados) {
				Map<String, Object> datosEmpleado = empleado.getData();
				double salario =  Double.parseDouble(datosEmpleado.get("salario")+"");
				if (salario > maxSalario) {
					maxSalario = salario;
					empMax = empleado;
				}
			}
			if (empMax != null) {
				empMax.getReference().delete();
				System.out.println("Empleado " + empMax.getString("emple_ap1") + " eliminado");
			}
		}
	}


}
