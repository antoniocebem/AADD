import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class TablaProductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaProductos frame = new TablaProductos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public TablaProductos() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 426, 238);
		contentPane.add(scrollPane);
		String columnas[]=null;
		Object [] fila=null;
		ResultSet rs=null;
		int numColumna=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/";
			
			Connection con=DriverManager.getConnection(url,"root","");
			
			Statement stmt = con.createStatement();
			
			stmt.execute("USE northwind");
			
			 rs = stmt.executeQuery("SELECT productName,unitPrice,unitsInStock FROM products"
					+ "");
			
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			numColumna=rsmd.getColumnCount();
			
			columnas=new String[numColumna];
			for(int i=0;i<numColumna;i++) {
				columnas[i]=rsmd.getColumnName(i+1);
			}
			
			
			
			
			
			
			
			

		}
		catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch (SQLException e) {
			System.out.println("Fallo en la conexion");
			e.printStackTrace();
		}
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		DefaultTableModel model=new DefaultTableModel(columnas,0);
		table_1.setModel(model);
		table_1.setAutoCreateRowSorter(true);
		
		while(rs.next()) {
			fila=new Object[numColumna];
			for(int i=0;i<numColumna;i++) {
				fila[i]=rs.getObject(i+1);
			}
			model.addRow(fila);
		}
	
	
	}
}
