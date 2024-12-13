package org.cebem.ejercicio1;
import org.cebem.GestionFicheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce la cantidad de números aleatorios: ");
        int cantidad = sc.nextInt();

        System.out.println("Selecciona el fichero donde se guardarán los números:");
        File fichero=GestionFicheros.seleccionFichero();

        Random random = new Random();
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            numeros.add(random.nextInt(101)); // Rango 0-100
        }

        try {
            FileWriter fw=new FileWriter(fichero,true);
            BufferedWriter bw=new BufferedWriter(fw);
            for (int numero : numeros) {
                bw.write(numero + "\n");
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

