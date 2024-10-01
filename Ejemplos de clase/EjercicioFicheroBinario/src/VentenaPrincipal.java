import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentenaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentenaPrincipal frame = new VentenaPrincipal();
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
	public VentenaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(54, 60, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(54, 94, 61, 16);
		contentPane.add(lblEdad);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(123, 55, 130, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldEdad = new JTextField();
		textFieldEdad.setColumns(10);
		textFieldEdad.setBounds(123, 89, 130, 26);
		contentPane.add(textFieldEdad);
		
		JButton btnNewButton = new JButton("Guardar en fichero");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String nombre=textFieldNombre.getText();
				int edad=Integer.parseInt(textFieldEdad.getText());
				
				System.out.println("Nombre: "+nombre);
				System.out.println("Edad: "+edad);
				
				File ficheroBinario=GestionFicheros.seleccionFichero();
				if (ficheroBinario.exists()) {
					FileOutputStream fps=new FileOutputStream(ficheroBinario,false);
					DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroBinario));
					dos.writeUTF(nombre);
					dos.writeInt(edad);
					dos.close();
					fps.close();
				}
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Edad inválida", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(102, 140, 155, 29);
		contentPane.add(btnNewButton);
		
		JButton btnLeerfichero = new JButton("Leer Fichero");
		btnLeerfichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File ficheroBinario=GestionFicheros.seleccionFichero();
				if (ficheroBinario.exists()) {
					try {
						FileInputStream fis=new FileInputStream(ficheroBinario);
						DataInputStream dis = new DataInputStream(new FileInputStream(ficheroBinario));
						String nombre=dis.readUTF();
						int edad=dis.readInt();
						textFieldNombre.setText(nombre);
						textFieldEdad.setText(Integer.toString(edad));
						
						dis.close();
						fis.close();
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnLeerfichero.setBounds(102, 181, 155, 29);
		contentPane.add(btnLeerfichero);
	}

}
