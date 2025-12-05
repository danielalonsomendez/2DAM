package vista;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class PanelProvincia extends JPanel {
	private JComboBox<String> comboBox;
	private JButton btnIncluir;
	private JButton btnSeleccionar;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelProvincia() {
		setLayout(null);
		
		btnIncluir = new JButton("Incluir datos nuevos");
		btnIncluir.setBounds(29, 41, 154, 23);
		add(btnIncluir);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(31, 172, 329, 22);
		add(comboBox);
		
		btnSeleccionar = new JButton("Seleccionar provincia");
		btnSeleccionar.setBounds(31, 205, 329, 23);
		add(btnSeleccionar);
		
		JLabel lblNewLabel = new JLabel("Selecciona una provincia:");
		lblNewLabel.setBounds(29, 147, 294, 14);
		add(lblNewLabel);
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getBtnIncluir() {
		return btnIncluir;
	}

	public void setBtnIncluir(JButton btnIncluir) {
		this.btnIncluir = btnIncluir;
	}
	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}
	
	
	
}
