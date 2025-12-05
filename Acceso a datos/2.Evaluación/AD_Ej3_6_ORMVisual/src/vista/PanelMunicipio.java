package vista;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelMunicipio extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelo;
	private JButton btnSeleccionar;

	/**
	 * Create the panel.
	 */
	public PanelMunicipio() {
		setLayout(null);
		
		btnSeleccionar = new JButton("Seleccionar provincia");
		btnSeleccionar.setBounds(20, 327, 503, 23);
		add(btnSeleccionar);
		
		JLabel lblNewLabel = new JLabel("Selecciona un municipio:");
		lblNewLabel.setBounds(20, 19, 294, 14);
		add(lblNewLabel);
		
		modelo = new DefaultTableModel(new String[] { "Nombre" }, 0);
		table = new JTable(modelo);
		table.setRowHeight(25);
		table.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 44, 503, 272);
		scrollPane.setViewportView(table);
		add(scrollPane);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}

	

}
