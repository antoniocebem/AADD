package org.cebem.Ejercicio2;

import org.cebem.GestionFicheros;
import java.io.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        File fichero= GestionFicheros.seleccionFicheroCSV();


        String productoMasCaro = "";
        double precioMasAlto = 0.0;

        try {
            FileReader fr=new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                String [] partes=linea.split(",");
                String producto = partes[0].trim();
                double precio=Double.parseDouble(partes[1].trim());
                if (precio > precioMasAlto) {
                    precioMasAlto = precio;
                    productoMasCaro = producto;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            System.out.println("El producto más caro es: " + productoMasCaro + " con un precio de " + precioMasAlto);

        }
    }
}
