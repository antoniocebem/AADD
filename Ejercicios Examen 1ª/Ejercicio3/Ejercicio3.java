package org.cebem.Ejercicio3;
import java.sql.*;
public class Ejercicio3 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/Northwind";
        String usuario = "root";
        String pass = "";


        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, usuario, pass);
            System.out.println("Conexión a la base de datos establecida.");

            String consultaPreparada = "INSERT INTO Products (ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement insert = connection.prepareStatement(consultaPreparada)) {
                insert.setString(1, "Jamones Luis");
                insert.setInt(2, 1);
                insert.setInt(3, 1);
                insert.setString(4, "10 cajas");
                insert.setDouble(5, 70.0);
                insert.setInt(6, 100);
                insert.setInt(7, 0);
                insert.setInt(8, 10);
                insert.setBoolean(9, false);

               insert.executeUpdate();
               System.out.println("Producto insertado correctamente.");


                String consultaProductos = "SELECT ProductID, ProductName, UnitPrice FROM Products WHERE UnitPrice > ?";


                PreparedStatement consulta = connection.prepareStatement(consultaProductos);
                consulta.setDouble(1, 50.0);
                ResultSet rs = consulta.executeQuery();
                System.out.println("Productos con precio mayor a 50:");
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    double unitPrice = rs.getDouble("UnitPrice");
                    System.out.println("ID: "+ productId+" Nombre: "+productName+ " Precio:" +unitPrice);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
