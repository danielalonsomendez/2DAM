package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;


public class PanelOperasMozart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private DefaultTableModel defaultTableModel;
	private JLabel lblErrores;
	

	/**
	 * Create the panel.
	 */
	public PanelOperasMozart() {
		setBackground(new Color(128, 255, 255));
		setBounds(288, 11, 688, 541);
		setLayout(null);

		
		
		JScrollPane jScrollPanel;
		jScrollPanel = new JScrollPane();
		jScrollPanel.setBounds(40, 150, 508, 267);
		add(jScrollPanel);
		
		

		String columnas[] = { "Obra" };

		defaultTableModel = new DefaultTableModel(columnas, 0);

		 tabla = new JTable(defaultTableModel);
		tabla.setAutoCreateRowSorter(true);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setRowSelectionAllowed(false);
		tabla.setCellSelectionEnabled(false);

		
		tabla.setDefaultEditor(Object.class, null); //Anulamos la edici�n en la propia celda


		jScrollPanel.setViewportView(tabla);
	
		
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

	public JTable gettabla() {
		return tabla;
	}

	public void settabla(JTable tabla) {
		this.tabla = tabla;
	}

	
}
