import java.io.*;

import javax.swing.JOptionPane;

public class EjemploAccesoFicheroAleatorio {

    public static void main(String[] args) throws IOException {
        
        File miFichero = GestionFicheros.seleccionFichero();
        RandomAccessFile raf = null;
        long pos = 0;

        try {
            raf = new RandomAccessFile(miFichero, "rw");
        
            String palabra = JOptionPane.showInputDialog(null, "Introduce la palabra a poner en mayúsculas");
            String linea;
        
            while ((linea = raf.readLine()) != null) {
            	
                int posicion = linea.indexOf(palabra);
                while (posicion != -1) {
                	
                    StringBuilder sb = new StringBuilder(linea);
                    sb.replace(posicion, posicion + palabra.length(), palabra.toUpperCase());
                    linea = sb.toString();
                    posicion = linea.indexOf(palabra, posicion + palabra.length());
                }

                raf.seek(pos);
                raf.writeBytes(linea + System.lineSeparator());
                
                pos = raf.getFilePointer();
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }
}