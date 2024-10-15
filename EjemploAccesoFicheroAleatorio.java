import java.io.*;

import javax.swing.JOptionPane;

public class EjemploAccesoFicheroAleatorio {

	public static void main(String[] args) {
		
		File miFichero=GestionFicheros.seleccionFichero();
		RandomAccessFile raf = null;
		double pos=0;
		try {
			raf=new RandomAccessFile(miFichero, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Introduce la palabra a poner en mayúsculas");
		String palabra=JOptionPane.showInputDialog(null, "Introduce la palabra a poner en mayúsculas");
		String linea;
		try {
			do {
				linea=raf.readLine();
				System.out.println(linea);

				int posicion;
				posicion=linea.indexOf(palabra);
				while(posicion!=-1) {
					System.out.println("SOY INFINITO");
					StringBuilder sb=new StringBuilder(linea);
					sb.replace(posicion,posicion+palabra.length(),palabra.toUpperCase());
					linea=sb.toString();
					posicion=linea.indexOf(palabra);
					
				}
				raf.seek((long) pos);
				raf.writeBytes(linea);
				
				pos = raf.getFilePointer();
				
			}while(linea!=null);
		} catch (EOFException e) {
			System.out.println("Fin del fichero");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
