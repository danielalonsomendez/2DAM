package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class PanelAnadir extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaContactos;
	private DefaultTableModel defaultTableModel;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JButton btnInsertarContacto;
	private JLabel lblErrores;
	

	/**
	 * Create the panel.
	 */
	public PanelAnadir() {
		setBackground(new Color(128, 255, 255));
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
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(40, 39, 60, 14);
		add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(110, 39, 129, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(110, 76, 129, 20);
		add(txtTelefono);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(40, 76, 72, 14);
		add(lblTelefono);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(319, 76, 129, 20);
		add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(263, 79, 46, 14);
		add(lblEmail);
		
		btnInsertarContacto = new JButton("Insertar contacto");
		btnInsertarContacto.setBounds(384, 104, 161, 23);
		add(btnInsertarContacto);
		
		lblErrores = new JLabel("");
		lblErrores.setForeground(new Color(255, 0, 0));
		lblErrores.setBounds(40, 104, 304, 23);
		add(lblErrores);

		
	}
	public void mVaciar() {
		this.txtNombre.setText("");
		this.txtTelefono.setText("");
		this.txtEmail.setText("");
		this.lblErrores.setText("");
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

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JButton getBtnInsertarContacto() {
		return btnInsertarContacto;
	}

	public void setBtnInsertarContacto(JButton btnInsertarContacto) {
		this.btnInsertarContacto = btnInsertarContacto;
	}
}
