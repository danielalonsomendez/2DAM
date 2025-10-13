package vista;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Vista {
    private JFrame frame;
    private JTextField nombreField;
    private JTextField idField;
    private JButton submitButton;
    private JTextArea outputArea;

    public Vista() {
        frame = new JFrame("Gestión de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(15);
        nombreLabel.setBounds(10, 10, 120, 20);
        nombreField.setBounds(80, 10, 120, 20); 
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 30, 120, 20);
        idField = new JTextField(15);
        		idField.setBounds(80, 30, 120, 20); ;
        submitButton = new JButton("Agregar Estudiante");
        submitButton.setBounds(110, 60, 200, 20); 
        outputArea = new JTextArea(10, 30);
        outputArea .setBounds(10, 90, 200, 200); 
        outputArea.setEditable(false);

        frame.add(nombreLabel);
        frame.add(nombreField);
        frame.add(idLabel);
        frame.add(idField);
        frame.add(submitButton);
        JScrollPane jsp=  new JScrollPane(outputArea);
        jsp.setBounds(10, 90, 340, 190); 
        frame.add(jsp);

        frame.setVisible(true);
    }

    public String getNombre() {
        return nombreField.getText();
    }

    public String getId() {
        return idField.getText();
    }

    public void setOutput(String text) {
        outputArea.setText(text);
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}