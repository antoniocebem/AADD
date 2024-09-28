import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton miBoton = new JButton("SELECCIÓN FICHERO");
		miBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fichero = GestionFicheros.seleccionFicheroCSV();
				textField.setText(fichero.getAbsolutePath());
			}
		});
		miBoton.setBounds(157, 122, 150, 29);
		contentPane.add(miBoton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(25, 181, 403, 26);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
