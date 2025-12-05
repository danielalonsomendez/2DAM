package vista;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PanelEstacionesEspacios extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelo;
	private JTable table2;
	private DefaultTableModel modelo2;
	private JButton btnSeleccionar;
	/**
	 * Create the panel.
	 */
	public PanelEstacionesEspacios() {
		setLayout(null);
		
		btnSeleccionar = new JButton("Seleccionar estación");
		btnSeleccionar.setBounds(20, 327, 327, 23);
		add(btnSeleccionar);
		
		JLabel lblNewLabel = new JLabel("Estaciones de medición");
		lblNewLabel.setBounds(20, 19, 294, 14);
		add(lblNewLabel);
		
		modelo = new DefaultTableModel(new String[] { "Nombre" }, 0);
		table = new JTable(modelo);
		table.setRowHeight(25);
		table.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 44, 327, 272);
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		modelo2 = new DefaultTableModel(new String[] { "Nombre" }, 0);
		table2 = new JTable(modelo2);
		table2.setRowHeight(25);
		table2.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(372, 44, 327, 272);
		scrollPane_1.setViewportView(table2);

		add(scrollPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("Espacios naturales");
		lblNewLabel_1.setBounds(372, 19, 294, 14);
		add(lblNewLabel_1);
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

	public JTable getTable2() {
		return table2;
	}

	public void setTable2(JTable table2) {
		this.table2 = table2;
	}

	public DefaultTableModel getModelo2() {
		return modelo2;
	}

	public void setModelo2(DefaultTableModel modelo2) {
		this.modelo2 = modelo2;
	}

	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}
	
	
}
