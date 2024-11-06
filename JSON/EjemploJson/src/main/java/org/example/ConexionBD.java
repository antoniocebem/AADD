package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexionBD {
    private static final String RUTA_ARCHIVO = "/Users/antonio/IdeaProjects/EjemploJson/EjemploJson/src/main/java/org/example/DatosConexion.json";
    public static void main (String[] args) {
        Gson gson=new GsonBuilder().setPrettyPrinting().create();


        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            DatosConexion datos = gson.fromJson(reader, DatosConexion.class);
            String url = "jdbc:mysql://" + datos.getDirServer() + ":" + datos.getPuerto() + "/" + datos.getBaseDatos();
            System.out.println(url);
            Connection conexion = DriverManager.getConnection(url, datos.getUsuario(), datos.getClave());
            System.out.println("Conexión exitosa a la base de datos");
            reader.close();

            FileWriter fw = new FileWriter("/Users/antonio/IdeaProjects/EjemploJson/EjemploJson/src/main/java/org/example/DatosConexion.json",true);
            datos.setBaseDatos("sakila");
            System.out.println(datos);

            List<DatosConexion> datosList=new ArrayList<>();
            datosList.add(datos);

            gson.toJson(datos,fw);
            fw.close();

            // Cerrar la conexión
            conexion.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
