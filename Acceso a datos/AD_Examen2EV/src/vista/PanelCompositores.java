package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PanelCompositores extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private DefaultTableModel defaultTableModel;

	/**
	 * Create the panel.
	 */
	public PanelCompositores() {
		setBackground(Color.orange);
		setBounds(288, 11, 688, 541);
		setLayout(null);

		JScrollPane jScrollPanel;
		jScrollPanel = new JScrollPane();
		jScrollPanel.setBounds(40, 150, 508, 267);
		add(jScrollPanel);

		String columnas[] = { "Compositor", "Nace", "Muere", "Obra", "Estilo" };

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tabla = new JTable(defaultTableModel);
		tabla.setAutoCreateRowSorter(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setRowSelectionAllowed(false);
		tabla.setCellSelectionEnabled(false);

		tabla.setDefaultEditor(Object.class, null);

		jScrollPanel.setViewportView(tabla);

	}

	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}

}
