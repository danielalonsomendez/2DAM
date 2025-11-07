package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Contacto;
import vista.Principal;

public class ControladorContacto implements ActionListener, ListSelectionListener {

	private vista.Principal vistaPrincipal;

	/*
	 * *** CONSTRUCTORES ***
	 */

	/*
	 * Contructor del objeto controlador
	 * 
	 * @param vistaPrincipal Objeto vista.
	 */
	public ControladorContacto(vista.Principal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;

		this.inicializarControlador();

	}

	private void inicializarControlador() {

		// Acciones del men� izquierdo
		this.vistaPrincipal.getBtnConsultarContactos().addActionListener(this);
		this.vistaPrincipal.getBtnConsultarContactos()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_CONSULTA.toString());

		this.vistaPrincipal.getBtnInsertarContacto().addActionListener(this);
		this.vistaPrincipal.getBtnInsertarContacto()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_INSERTAR.toString());

		this.vistaPrincipal.getBtnModificarContacto().addActionListener(this);
		this.vistaPrincipal.getBtnModificarContacto()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_MODIFICAR.toString());

		this.vistaPrincipal.getBtnEliminarContacto().addActionListener(this);
		this.vistaPrincipal.getBtnEliminarContacto()
				.setActionCommand(Principal.enumAcciones.CARGAR_PANEL_ELIMINAR.toString());

		// Acciones del panel Insertar
		this.vistaPrincipal.getPanelAnadir().getBtnInsertarContacto().addActionListener(this);
		this.vistaPrincipal.getPanelAnadir().getBtnInsertarContacto()
				.setActionCommand(Principal.enumAcciones.INSERTAR_CONTACTO.toString());

		// Acciones del panel Modificar
		this.vistaPrincipal.getPanelModificar().getTablaContactos().getSelectionModel().addListSelectionListener(this);
		this.vistaPrincipal.getPanelModificar().getBtnInsertarContacto()
				.setActionCommand(Principal.enumAcciones.MODIFICAR_CONTACTO.toString());
		this.vistaPrincipal.getPanelModificar().getBtnInsertarContacto().addActionListener(this);
		// Acciones del panel Eliminar
		this.vistaPrincipal.getPanelBorrar().getTablaContactos().getSelectionModel().addListSelectionListener(this);
		this.vistaPrincipal.getPanelBorrar().getBtnInsertarContacto()
				.setActionCommand(Principal.enumAcciones.ELIMINAR_CONTACTO.toString());
		this.vistaPrincipal.getPanelBorrar().getBtnInsertarContacto().addActionListener(this);
	}

	/*** Tratamiento de las acciones ***/

	@Override
	public void actionPerformed(ActionEvent e) {

		Principal.enumAcciones accion = Principal.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {
		case CARGAR_PANEL_CONSULTA:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_CONSULTA);
			this.mCargarContactos(accion);
			break;
		case CARGAR_PANEL_INSERTAR:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_INSERTAR);
			this.mCargarContactos(accion);
			break;
		case CARGAR_PANEL_MODIFICAR:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_MODIFICAR);
			this.mCargarContactos(accion);
			break;
		case CARGAR_PANEL_ELIMINAR:
			this.vistaPrincipal.mVisualizarPaneles(Principal.enumAcciones.CARGAR_PANEL_ELIMINAR);
			this.mCargarContactos(accion);
			break;
		case INSERTAR_CONTACTO:
			this.mInsertarContacto(false);
			break;
		case MODIFICAR_CONTACTO:
			this.mInsertarContacto(true);
			break;
		case ELIMINAR_CONTACTO:
			this.mEliminarContacto();
			break;
		default:
			break;

		}
	}

	/*** Llamados a m�todos CRUD ***/

	/*** Otros metodos ***/

	private void mCargarContactos(Principal.enumAcciones accion) {

		Contacto contactos = new Contacto();

		mLimpiarTabla(accion);

		ArrayList<Contacto> listaContactos = contactos.mObtenerContactos();

		String matrizInfo[][] = new String[listaContactos.size()][4];

		for (int i = 0; i < listaContactos.size(); i++) {
			matrizInfo[i][0] = listaContactos.get(i).getIdContacto();
			matrizInfo[i][1] = listaContactos.get(i).getNombre();
			matrizInfo[i][2] = String.valueOf(listaContactos.get(i).getTelefono());
			matrizInfo[i][3] = listaContactos.get(i).getEmail();

			switch (accion) {
			case CARGAR_PANEL_CONSULTA:
				this.vistaPrincipal.getPanelConsultar().getDefaultTableModel().addRow(matrizInfo[i]);
				break;
			case CARGAR_PANEL_INSERTAR:
				this.vistaPrincipal.getPanelAnadir().getDefaultTableModel().addRow(matrizInfo[i]);
				break;
			case CARGAR_PANEL_MODIFICAR:
				this.vistaPrincipal.getPanelModificar().getDefaultTableModel().addRow(matrizInfo[i]);
				break;
			case CARGAR_PANEL_ELIMINAR:
				this.vistaPrincipal.getPanelBorrar().getDefaultTableModel().addRow(matrizInfo[i]);
				break;
			default:
				break;
			}
		}

	}

	private void mLimpiarTabla(Principal.enumAcciones accion) {

		switch (accion) {
		case CARGAR_PANEL_CONSULTA:
			if (this.vistaPrincipal.getPanelConsultar().getDefaultTableModel().getRowCount() > 0) {
				this.vistaPrincipal.getPanelConsultar().getDefaultTableModel().setRowCount(0);
			}
			break;
		case CARGAR_PANEL_INSERTAR:
			if (this.vistaPrincipal.getPanelAnadir().getDefaultTableModel().getRowCount() > 0) {
				this.vistaPrincipal.getPanelAnadir().getDefaultTableModel().setRowCount(0);
			}
			break;
		case CARGAR_PANEL_MODIFICAR:
			vistaPrincipal.getPanelModificar().mOcultarMostrar(false);
			if (this.vistaPrincipal.getPanelModificar().getDefaultTableModel().getRowCount() > 0) {
				this.vistaPrincipal.getPanelModificar().getDefaultTableModel().setRowCount(0);
			}
			break;
		case CARGAR_PANEL_ELIMINAR:
			vistaPrincipal.getPanelBorrar().mOcultarMostrar(false);
			if (this.vistaPrincipal.getPanelBorrar().getDefaultTableModel().getRowCount() > 0) {
				this.vistaPrincipal.getPanelBorrar().getDefaultTableModel().setRowCount(0);
			}
			break;
		default:
			break;
		}

	}

	private void mInsertarContacto(boolean creado) {
		String nombre = this.vistaPrincipal.getPanelAnadir().getTxtNombre().getText().trim();
		String email = this.vistaPrincipal.getPanelAnadir().getTxtEmail().getText().trim();
		String telefonoStr = this.vistaPrincipal.getPanelAnadir().getTxtTelefono().getText().trim();
		if (creado) {
			nombre = this.vistaPrincipal.getPanelModificar().getTxtNombre().getText().trim();
			email = this.vistaPrincipal.getPanelModificar().getTxtEmail().getText().trim();
			telefonoStr = this.vistaPrincipal.getPanelModificar().getTxtTelefono().getText().trim();
		}
		double telefono;
		try {
			telefono = Double.parseDouble(telefonoStr);
		} catch (NumberFormatException ex) {
			this.vistaPrincipal.getPanelAnadir().getLblErrores().setText("El teléfono debe ser numérico");
			this.vistaPrincipal.getPanelModificar().getLblErrores().setText("El teléfono debe ser numérico");
			return;
		}
		if (nombre.isEmpty() || email.isEmpty()) {
			this.vistaPrincipal.getPanelAnadir().getLblErrores().setText("El nombre y el email son obligatorios");
			this.vistaPrincipal.getPanelModificar().getLblErrores().setText("El nombre y el email son obligatorios");
			return;
		}

		Contacto contacto = new Contacto();
		contacto.setNombre(nombre);
		contacto.setTelefono(telefono);
		contacto.setEmail(email);
		if (creado == false) {
			if (contacto.mAnadirContacto()) {
				this.mCargarContactos(Principal.enumAcciones.CARGAR_PANEL_INSERTAR);
				this.vistaPrincipal.getPanelAnadir().mVaciar();
			} else {
				this.vistaPrincipal.getPanelAnadir().getLblErrores().setText("Error al insertar contacto");
			}
		} else {
			contacto.setIdContacto(
					this.mContactoSeleccionado(vistaPrincipal.getPanelModificar().getTablaContactos()).getIdContacto());
			if (contacto.mModificarContacto()) {
				this.mCargarContactos(Principal.enumAcciones.CARGAR_PANEL_MODIFICAR);
				this.vistaPrincipal.getPanelModificar().mVaciar();
			} else {
				this.vistaPrincipal.getPanelModificar().getLblErrores().setText("Error al modificar contacto");
			}
		}
	}
	public void mEliminarContacto() {
		Contacto contactoSeleccionado = mContactoSeleccionado(vistaPrincipal.getPanelBorrar().getTablaContactos());
		if (contactoSeleccionado != null) {
			if (contactoSeleccionado.mBorrarContacto()) {
				this.mCargarContactos(Principal.enumAcciones.CARGAR_PANEL_ELIMINAR);
				this.vistaPrincipal.getPanelBorrar().mOcultarMostrar(false);
			} else {
				this.vistaPrincipal.getPanelBorrar().getLblErrores().setText("Error al eliminar contacto");
			}
		}
	}

	public Contacto mContactoSeleccionado(JTable tablaContactos) {
		if (tablaContactos.getSelectedRow() != -1) {
			String IDSeleccionado = tablaContactos.getValueAt(tablaContactos.getSelectedRow(), 0).toString();
			Contacto contactos = new Contacto();
			ArrayList<Contacto> listaContactos = contactos.mObtenerContactos();
			for (int i = 0; i < listaContactos.size(); i++) {
				if (listaContactos.get(i).getIdContacto().equals(IDSeleccionado)) {
					return listaContactos.get(i);
				}
			}
		}
		return null;
	}

	private void mCargarDatosModificar() {
		Contacto contactoSeleccionado = mContactoSeleccionado(vistaPrincipal.getPanelModificar().getTablaContactos());
		vistaPrincipal.getPanelModificar().mOcultarMostrar(contactoSeleccionado != null);
		if (contactoSeleccionado != null) {
			vistaPrincipal.getPanelModificar().getTxtNombre().setText(contactoSeleccionado.getNombre());
			vistaPrincipal.getPanelModificar().getTxtTelefono()
					.setText(String.valueOf(contactoSeleccionado.getTelefono()));
			vistaPrincipal.getPanelModificar().getTxtEmail().setText(contactoSeleccionado.getEmail());
		}

	}

	private void mCargarDatosBorrar() {
		Contacto contactoSeleccionado = mContactoSeleccionado(vistaPrincipal.getPanelBorrar().getTablaContactos());
		vistaPrincipal.getPanelBorrar().mOcultarMostrar(contactoSeleccionado != null);
	}
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == vistaPrincipal.getPanelModificar().getTablaContactos().getSelectionModel()) {
			mCargarDatosModificar();
		} else if (e.getSource() == vistaPrincipal.getPanelBorrar().getTablaContactos().getSelectionModel()) {

			mCargarDatosBorrar();
		}
	}

}