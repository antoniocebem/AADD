import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class FormularioCoche extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldMatricula;
	private JTextField textFieldDeposito;
	private JTextField textFieldPrecio;
	private JTable table;
	private ArrayList<Coche> coches=new ArrayList<Coche>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioCoche frame = new FormularioCoche();
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
	public FormularioCoche() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Marca");
		lblNewLabel.setBounds(22, 40, 120, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(22, 68, 120, 16);
		contentPane.add(lblModelo);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(285, 38, 120, 16);
		contentPane.add(lblMatricula);
		
		JLabel lblCapacidadDeposito = new JLabel("Litros deposito");
		lblCapacidadDeposito.setBounds(285, 68, 105, 16);
		contentPane.add(lblCapacidadDeposito);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(22, 96, 120, 16);
		contentPane.add(lblPrecio);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(132, 33, 130, 26);
		contentPane.add(textFieldMarca);
		textFieldMarca.setColumns(10);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setColumns(10);
		textFieldModelo.setBounds(132, 63, 130, 26);
		contentPane.add(textFieldModelo);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(398, 33, 130, 26);
		contentPane.add(textFieldMatricula);
		
		textFieldDeposito = new JTextField();
		textFieldDeposito.setColumns(10);
		textFieldDeposito.setBounds(398, 63, 130, 26);
		contentPane.add(textFieldDeposito);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(132, 96, 130, 26);
		contentPane.add(textFieldPrecio);
		
		JButton btnNewButtonExportar = new JButton("Exportar");
		btnNewButtonExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(coches.size());
					guardarCoches(coches);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButtonExportar.setBounds(462, 288, 117, 29);
		contentPane.add(btnNewButtonExportar);
		
		String[] columnNames = {"Marca", "Modelo", "Matrícula", "Capacidad Depósito", "Precio"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 134, 536, 142);
		contentPane.add(scrollPane);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JButton btnNewButtonImportar = new JButton("Importar");
		btnNewButtonImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File ficheroEntrada=GestionFicheros.seleccionFichero();
				try {
					FileInputStream fis=new FileInputStream(ficheroEntrada);
					ObjectInputStream ois = new ObjectInputStream(fis);
					ArrayList<Coche> cochesImportados=new ArrayList<Coche>();
					try {
						while(true) {
							Coche c=(Coche) ois.readObject();
							cochesImportados.add(c);
							System.out.println(c);
							tableModel.addRow(new Object[] {c.getMarca(), c.getModelo(), c.getMatricula(), c.getTamanhoDeposito(), c.getPrecio()});
						}
					}catch(EOFException e1) {
						
					}
					ois.close();
					fis.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonImportar.setBounds(333, 288, 117, 29);
		contentPane.add(btnNewButtonImportar);
		
		JButton btnNewButton = new JButton("Añadir Coche");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if(textFieldMarca.getText().equals("") || textFieldModelo.getText().equals("") || textFieldMatricula.getText().equals("") || textFieldDeposito.getText().equals("") || textFieldPrecio.getText().equals("")){
					throw new CamposIncompletosException("RELLENA TODOS LOS CAMPOS");
				}
				else {
					String marca = textFieldMarca.getText();
					String modelo = textFieldModelo.getText();
					String matricula = textFieldMatricula.getText();
					int litros=Integer.parseInt(textFieldDeposito.getText());
					double precio=Double.parseDouble(textFieldPrecio.getText());
					Coche coche = new Coche(matricula, marca, modelo, litros, precio);
					
					Coche miCoche=new Coche(matricula,marca,modelo,litros,precio);
					coches.add(miCoche);
					tableModel.addRow(new Object[] {marca, modelo, matricula, litros, precio});
					textFieldMarca.setText("");
					textFieldModelo.setText("");
					textFieldMatricula.setText("");
					textFieldDeposito.setText("");
					textFieldPrecio.setText("");
					
				}
				}
				catch(CamposIncompletosException e1) {
					JOptionPane.showMessageDialog(null, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);

				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Introduce un valor numerico", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
		});
		btnNewButton.setBounds(285, 96, 117, 29);
		contentPane.add(btnNewButton);
	}
	
	private boolean guardarCoches(ArrayList<Coche> coches) throws IOException {
		File ficheroCoches = GestionFicheros.seleccionFichero();
		boolean append = ficheroCoches.exists();
		try (FileOutputStream fos = new FileOutputStream(ficheroCoches, append);
				ObjectOutputStream oos =  new ObjectOutputStream(fos)) {
			for (Coche c : coches) {
				oos.writeObject(c);
			}
			oos.close();
			JOptionPane.showMessageDialog(null, "Coches guardados correctamente", "Coches guardados", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return true;
	}
		
	}

