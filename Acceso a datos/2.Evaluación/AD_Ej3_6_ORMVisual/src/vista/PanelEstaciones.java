package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelEstaciones extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelo;
	private JLabel lblTitulo;

	/**
	 * Create the panel.
	 */
	public PanelEstaciones() {
		setLayout(null);

		lblTitulo = new JLabel("");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(20, 19, 897, 28);
		add(lblTitulo);

		modelo = new DefaultTableModel(new String[] { "Fecha", "Hora", "Ica", "Dir viento", "H. Relativa", "P. Atmos",
				"Precip", "Rad Solar", "Temp AM", "V Viento" }, 0);
		table = new JTable(modelo);
		table.setRowHeight(25);
		table.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 58, 897, 272);
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

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

}
