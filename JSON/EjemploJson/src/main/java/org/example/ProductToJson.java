package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ProductToJson {
    private static final String RUTA_ARCHIVO = "/Users/Antonio/eclipse-workspace/AADD/JSON/EjemploJson/src/main/java/org/example/DatosConexion.json";

    public static void main(String [] args){
        try {
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            DatosConexionBD datosConexion=gson.fromJson(new FileReader(RUTA_ARCHIVO),DatosConexionBD.class);
            String url="jdbc:mysql://"+datosConexion.getDirServer()+":"+datosConexion.getPuerto()+"/"+datosConexion.getBaseDatos();
            Connection conexion= DriverManager.getConnection(url,datosConexion.getUsuario(),datosConexion.getClave());

            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery("select * from products");
            ArrayList<ProductoItem> listaProductos=new ArrayList<>();
            while(rs.next()){
                ProductoItem productoItem=new ProductoItem();
                productoItem.setProductName(rs.getString(2));
                productoItem.setCategoryID(rs.getInt(4));
                productoItem.setSupplierID(rs.getInt(3));
                productoItem.setUnitPrice(rs.getDouble(6));
                productoItem.setUnitsInStock(rs.getInt(7));
                listaProductos.add(productoItem);
            }

            FileWriter fw=new FileWriter("DatosProducto.json");
            gson.toJson(listaProductos,fw);
        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
