package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Compositor;
import modelo.Estilo;
import modelo.Musica;
import vista.Principal;

public class Controlador implements ActionListener {

	private vista.Principal vistaPrincipal;

	/*
	 * *** CONSTRUCTORES ***
	 */

	/*
	 * Contructor del objeto controlador
	 * 
	 * @param vistaPrincipal Objeto vista.
	 */
	public Controlador(vista.Principal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;

		this.inicializarControlador();

	}

	private void inicializarControlador() {

		// Acciones del men� izquierdo
		this.vistaPrincipal.getBtnNuevosElementos().addActionListener(this);
		this.vistaPrincipal.getBtnNuevosElementos()
				.setActionCommand(Principal.enumAcciones.NUEVOS_ELEMENTOS.toString());

		this.vistaPrincipal.getBtnCompositores().addActionListener(this);
		this.vistaPrincipal.getBtnCompositores()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_COMPOSITORES.toString());

		this.vistaPrincipal.getBtnOperasMozart().addActionListener(this);
		this.vistaPrincipal.getBtnOperasMozart()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_OPERAS.toString());
	}

	/*** Tratamiento de las acciones ***/

	@Override
	public void actionPerformed(ActionEvent e) {

		Principal.enumAcciones accion = Principal.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {
		case CARGAR_PANEL_COMPOSITORES:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_COMPOSITORES);
			this.mCargarMusica(accion);
			break;
		case CARGAR_PANEL_OPERAS:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_OPERAS);
			this.mCargarMusica(accion);
			break;
		case NUEVOS_ELEMENTOS:
			this.mNuevosElementos(accion);
			break;
		default:
			break;

		}
	}


	private void mNuevosElementos(Principal.enumAcciones accion) {
		JsonParser parser = new JsonParser();
		FileReader fr;
		try {
			fr = new FileReader("nuevasObras.json");
			JsonElement elemento = parser.parse(fr);
			JsonArray array = elemento.getAsJsonArray();
			ArrayList<Musica> musicas = new ArrayList<Musica>();
			for (JsonElement elem : array) {
				JsonObject objeto = elem.getAsJsonObject();
				Musica musica = new Musica();
				for (Map.Entry<String, JsonElement> entry : objeto.entrySet()) {
					if (entry.getKey().equals("DescMusica")) {
						musica.setDescMusica(entry.getValue().getAsJsonPrimitive().getAsString());
					} else if (entry.getKey().equals("compoMusica")) {
						int id = entry.getValue().getAsJsonPrimitive().getAsInt();
						musica.setCompositor(new Compositor(id));
					} else if (entry.getKey().equals("estiloMusica")) {
						int id = entry.getValue().getAsJsonPrimitive().getAsInt();
						musica.setEstilo(new Estilo(id));
					}
				}
				musicas.add(musica);
			}

			for (var i = 0; i < musicas.size(); i++) {
				musicas.get(i).insertar();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void mCargarMusica(Principal.enumAcciones accion) {

		mLimpiarTabla(accion);
		List<Musica> listaMusicas = null;

		switch (accion) {
		case CARGAR_PANEL_COMPOSITORES:
			listaMusicas = Musica.obtenerCompositores1970();
			break;
		case CARGAR_PANEL_OPERAS:
			listaMusicas = Musica.obtenerOperasMozart();
			break;
		default:
			break;
		}

		String matrizInfo[][] = new String[listaMusicas.size()][5];

		for (int i = 0; i < listaMusicas.size(); i++) {
			Musica musica = listaMusicas.get(i);
			String nombreObra = listaMusicas.get(i).getDescMusica();
			switch (accion) {
			case CARGAR_PANEL_COMPOSITORES:
				matrizInfo[i][0] = musica.getCompositor().getNomCompo();
				matrizInfo[i][1] = musica.getCompositor().getNaceCompo();
				matrizInfo[i][2] = musica.getCompositor().getMuereCompo();
				matrizInfo[i][3] = nombreObra;
				matrizInfo[i][4] = musica.getEstilo().getNomEstilo();

				this.vistaPrincipal.getPanelCompositores().getDefaultTableModel().addRow(matrizInfo[i]);
				break;
			case CARGAR_PANEL_OPERAS:
				matrizInfo[i][0] = nombreObra;
				this.vistaPrincipal.getPanelOperasMozart().getDefaultTableModel().addRow(matrizInfo[i]);
				break;
			default:
				break;
			}
		}

	}

	private void mLimpiarTabla(Principal.enumAcciones accion) {
		switch (accion) {
		case CARGAR_PANEL_COMPOSITORES:
			if (this.vistaPrincipal.getPanelCompositores().getDefaultTableModel().getRowCount() > 0) {
				this.vistaPrincipal.getPanelCompositores().getDefaultTableModel().setRowCount(0);
			}
			break;
		case CARGAR_PANEL_OPERAS:
			if (this.vistaPrincipal.getPanelOperasMozart().getDefaultTableModel().getRowCount() > 0) {
				this.vistaPrincipal.getPanelOperasMozart().getDefaultTableModel().setRowCount(0);
			}
			break;
		default:
			break;
		}

	}

}