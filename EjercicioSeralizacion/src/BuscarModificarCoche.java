import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class BuscarModificarCoche extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JTextField textFieldMatricula;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldDesposito;
	private JTextField textFieldPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarModificarCoche dialog = new BuscarModificarCoche();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscarModificarCoche() {
		setBounds(100, 100, 564, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(6, 6, 109, 27);
		contentPanel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(115, 5, 130, 26);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(313, 5, 117, 29);
		contentPanel.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(16, 177, 517, 95);
		contentPanel.add(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 40, 450, 125);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setBounds(79, 18, 130, 26);
		panel.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(79, 56, 130, 26);
		panel.add(textFieldMarca);
		textFieldMarca.setColumns(10);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setBounds(79, 94, 130, 26);
		panel.add(textFieldModelo);
		textFieldModelo.setColumns(10);
		
		textFieldDesposito = new JTextField();
		textFieldDesposito.setBounds(314, 18, 130, 26);
		panel.add(textFieldDesposito);
		textFieldDesposito.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Matrícula");
		lblNewLabel.setBounds(8, 23, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(6, 61, 61, 16);
		panel.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(6, 99, 61, 16);
		panel.add(lblModelo);
		
		JLabel lblDepsito = new JLabel("Depósito");
		lblDepsito.setBounds(241, 24, 61, 16);
		panel.add(lblDepsito);
		
		JLabel lblNewLabel_3_1 = new JLabel("Precio");
		lblNewLabel_3_1.setBounds(241, 61, 61, 16);
		panel.add(lblNewLabel_3_1);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(314, 56, 130, 26);
		panel.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(312, 94, 117, 29);
		panel.add(btnNewButton_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
