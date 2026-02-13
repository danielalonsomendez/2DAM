package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import java.awt.Font;



import java.awt.Color;

public class Principal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//Acciones
	public static  enum enumAcciones{
		CARGAR_PANEL_COMPOSITORES,
		CARGAR_PANEL_OPERAS,
		NUEVOS_ELEMENTOS
	}
	
	
	private JPanel panelContenedor;
	private PanelCompositores panelCompositores;
	private PanelOperasMozart panelOperasMozart;
	
	
	private JButton btnCompositores; 
	private JButton btnOperasMozart;
	private JButton btnNuevosElementos;
	



	public Principal() {
		
		//Panel que contiene todo el contenido de la ventana
		mCrearPanelContenedor(); 
		
		//Panel izquierdo, contiene el men� del programa.
		mCrearPanelMenu();
	
		//Panel que contiene el listado de contactos.
		mCrearPanelCompositores();
		
		//Panel que contiene el formulario para anadir contactos.
		mCrearPanelOperasMozart();
		
	
			
	}
	
	
	//*** Creaci�n de paneles ***
	
	private void mCrearPanelContenedor() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		panelContenedor = new JPanel();
		panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenedor);
		panelContenedor.setLayout(null);
		
	}

	private void mCrearPanelMenu() {
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.YELLOW);
		panelMenu.setBounds(10, 11, 268, 541);
		panelContenedor.add(panelMenu);
		panelMenu.setLayout(null);
		
		btnCompositores = new JButton("Compositores");
		btnCompositores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCompositores.setBounds(30, 75, 216, 35);
		panelMenu.add(btnCompositores);
		
		btnOperasMozart = new JButton("Operas Mozart");
		btnOperasMozart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOperasMozart.setBounds(30, 167, 216, 35);
		panelMenu.add(btnOperasMozart);
		
		btnNuevosElementos = new JButton("Nuevos elementos");
		btnNuevosElementos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNuevosElementos.setBounds(28, 271, 216, 35);
		panelMenu.add(btnNuevosElementos);
		

	}

	private void mCrearPanelCompositores() {
		panelCompositores = new PanelCompositores();
		panelContenedor.add(panelCompositores);
		panelCompositores.setVisible(false);
		
	}
	private void mCrearPanelOperasMozart() {
		panelOperasMozart = new PanelOperasMozart();
		panelContenedor.add(panelOperasMozart);
		panelOperasMozart.setVisible(false);
		
	}


	//*** FIN creaci�n de paneles ***
	
	
	public void mVisualizarPaneles(enumAcciones panel) {
		
		panelCompositores.setVisible(false);
		panelOperasMozart.setVisible(false);
		
		switch (panel) {
		case CARGAR_PANEL_COMPOSITORES: 
			panelCompositores.setVisible(true);
			break;
		case CARGAR_PANEL_OPERAS:
			panelOperasMozart.setVisible(true);
			break;
		default:
			break;
			

		}
	}


	public JPanel getPanelContenedor() {
		return panelContenedor;
	}


	public void setPanelContenedor(JPanel panelContenedor) {
		this.panelContenedor = panelContenedor;
	}


	public PanelCompositores getPanelCompositores() {
		return panelCompositores;
	}


	public void setPanelCompositores(PanelCompositores panelCompositores) {
		this.panelCompositores = panelCompositores;
	}


	public PanelOperasMozart getPanelOperasMozart() {
		return panelOperasMozart;
	}


	public void setPanelOperasMozart(PanelOperasMozart panelOperasMozart) {
		this.panelOperasMozart = panelOperasMozart;
	}


	public JButton getBtnCompositores() {
		return btnCompositores;
	}


	public void setBtnCompositores(JButton btnCompositores) {
		this.btnCompositores = btnCompositores;
	}


	public JButton getBtnOperasMozart() {
		return btnOperasMozart;
	}


	public void setBtnOperasMozart(JButton btnOperasMozart) {
		this.btnOperasMozart = btnOperasMozart;
	}


	public JButton getBtnNuevosElementos() {
		return btnNuevosElementos;
	}


	public void setBtnNuevosElementos(JButton btnNuevosElementos) {
		this.btnNuevosElementos = btnNuevosElementos;
	}


	



	
}
