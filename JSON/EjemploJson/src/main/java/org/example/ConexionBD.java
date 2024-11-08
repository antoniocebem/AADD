package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {
    private static final String RUTA_ARCHIVO = "/Users/Antonio/eclipse-workspace/AADD/JSON/EjemploJson/src/main/java/org/example/DatosConexion.json";
    public static void main (String[] args) {
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        Connection conexion;

        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            DatosConexionBD datos = gson.fromJson(reader, DatosConexionBD.class);
            String url = "jdbc:mysql://" + datos.getDirServer() + ":" + datos.getPuerto() + "/" + datos.getBaseDatos();
            System.out.println(url);
            conexion = DriverManager.getConnection(url, datos.getUsuario(), datos.getClave());
            System.out.println("Conexión exitosa a la base de datos");
            reader.close();

            //JOptionPane.showMessageDialog(null, "Seleccione el fichero con los productos a cargar");
            JFileChooser fileChooser = new JFileChooser();

            // Filtro para mostrar solo archivos JSON
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON", "json");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
            }
            File selectedFile = fileChooser.getSelectedFile();
            insertarProductos(conexion, selectedFile);
            // Cerrar la conexión
            conexion.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertarProductos(Connection conexion,File fichero){
        try {
            Statement statement = conexion.createStatement();
            statement.execute("USE NORTHWIND");
            String consultPreparada="INSERT INTO Products (ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = conexion.prepareStatement(consultPreparada);

            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            FileReader fr=new FileReader(fichero);
            List<ProductoItem> productos=new ArrayList<>();
            productos=gson.fromJson(fr,new TypeToken<List<ProductoItem>>() {}.getType());

            for(ProductoItem producto:productos){
                System.out.println(producto);
                preparedStatement.setString(1,producto.getProductName());       // ProductName
                preparedStatement.setInt(2, producto.getSupplierID());                       // SupplierID
                preparedStatement.setInt(3, producto.getCategoryID());                        // CategoryID
                preparedStatement.setString(4, producto.getQuantityPerUnit());            // QuantityPerUnit
                preparedStatement.setDouble(5, 7);                   // UnitPrice
                preparedStatement.setInt(6, producto.getUnitsInStock());                      // UnitsInStock
                preparedStatement.setInt(7, producto.getUnitsOnOrder());                        // UnitsOnOrder
                preparedStatement.setInt(8, producto.getReorderLevel());                       // ReorderLevel
                preparedStatement.setInt(9, producto.getDiscontinued());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
