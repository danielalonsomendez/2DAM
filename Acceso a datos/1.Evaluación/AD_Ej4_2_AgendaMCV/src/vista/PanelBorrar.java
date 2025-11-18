package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;


public class PanelBorrar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaContactos;
	private DefaultTableModel defaultTableModel;
	private JButton btnBorrarContacto;
	private JLabel lblErrores;
	

	/**
	 * Create the panel.
	 */
	public PanelBorrar() {
		setBackground(Color.MAGENTA);
		setBounds(288, 11, 688, 541);
		setLayout(null);

		
		
		JScrollPane jScrollPanel;
		jScrollPanel = new JScrollPane();
		jScrollPanel.setBounds(40, 150, 508, 267);
		add(jScrollPanel);
		
		

		String columnas[] = { "Id", "Nombre", "Teléfono", "E-mail" };

		defaultTableModel = new DefaultTableModel(columnas, 0);

		 tablaContactos = new JTable(defaultTableModel);
		tablaContactos.setAutoCreateRowSorter(true);
		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaContactos.setRowSelectionAllowed(false);
		tablaContactos.setCellSelectionEnabled(false);

		
		tablaContactos.setDefaultEditor(Object.class, null); //Anulamos la edici�n en la propia celda


		jScrollPanel.setViewportView(tablaContactos);
		
		btnBorrarContacto = new JButton("Borrar contacto");
		btnBorrarContacto.setBounds(384, 104, 161, 23);
		add(btnBorrarContacto);
		
		lblErrores = new JLabel("");
		lblErrores.setForeground(new Color(255, 0, 0));
		lblErrores.setBounds(40, 104, 304, 23);
		add(lblErrores);

		
	}

	public void mOcultarMostrar(boolean visible) {
		btnBorrarContacto.setVisible(visible);
		if(!visible) {
			lblErrores.setText("Seleccione un contacto de la tabla");
			lblErrores.setForeground(Color.BLACK);
		}else {
			lblErrores.setText("");
			lblErrores.setForeground(Color.RED);
		}
	}
	
	

	public JLabel getLblErrores() {
		return lblErrores;
	}
	public void setLblErrores(JLabel lblErrores) {
		this.lblErrores = lblErrores;
	}
	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}

	public JTable getTablaContactos() {
		return tablaContactos;
	}

	public void setTablaContactos(JTable tablaContactos) {
		this.tablaContactos = tablaContactos;
	}

	public JButton getBtnInsertarContacto() {
		return btnBorrarContacto;
	}

	public void setBtnInsertarContacto(JButton btnInsertarContacto) {
		this.btnBorrarContacto = btnInsertarContacto;
	}
	
}
