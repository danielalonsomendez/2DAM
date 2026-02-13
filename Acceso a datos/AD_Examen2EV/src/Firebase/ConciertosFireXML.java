package Firebase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import modelo.Concierto;



public class ConciertosFireXML {
	public static void main(String[] args) {
		// Conseguir datos Firebase
		ArrayList<Concierto> conciertos = new ArrayList<Concierto>();
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("conciertos.json");
			FirestoreOptions options = FirestoreOptions.getDefaultInstance().toBuilder()
					.setProjectId("examen2evad").setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();
			Firestore db = options.getService();
			ApiFuture<QuerySnapshot> query = db.collection("conciertos").get();
			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> conciertosQ = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot concierto : conciertosQ) {
				Concierto d = new Concierto(Integer.parseInt(concierto.getId()),concierto.getString("artista"),
						concierto.getDate("fecha"),concierto.getDouble("precioEntrada"));
				conciertos.add(d);
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
		// Crear XML
		try {
			File xmlFile = new File("conciertos.xml");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc;

			doc = builder.newDocument();			
			doc.getDocumentElement();
		

			Element conciertosXML = (Element) doc.createElement("conciertos");
			
			for (Concierto concierto : conciertos) {
				Element conciertoXML = doc.createElement("concierto");
				Element artista = doc.createElement("artista");
				Element fecha = doc.createElement("fecha");
				Element precioEntrada = doc.createElement("precioEntrada");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

				artista.appendChild(doc.createTextNode(concierto.getArtista()));
				fecha.appendChild(doc.createTextNode(sdf.format(concierto.getFecha())));
				precioEntrada.appendChild(doc.createTextNode(concierto.getPrecio_entrada()+""));
				conciertoXML.setAttribute("id", concierto.getId()+"");

				conciertosXML.appendChild(conciertoXML);
				conciertoXML.appendChild(artista);
				conciertoXML.appendChild(fecha);
				conciertoXML.appendChild(precioEntrada);
				
			}
			
			doc.appendChild(conciertosXML);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
