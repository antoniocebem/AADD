import java.io.*;

import javax.swing.JOptionPane;

public class EjemploAccesoFicheroAleatorio {

	public static void main(String[] args) throws IOException {
		
		File miFichero=GestionFicheros.seleccionFichero();
		RandomAccessFile raf = null;
		long pos=0;
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
			while((linea=raf.readLine())!=null) {
				System.out.println(linea);
				int posicion;
				posicion=linea.indexOf(palabra);
				while(posicion!=-1) {
					StringBuilder sb=new StringBuilder(linea);
					sb.replace(posicion,posicion+palabra.length(),palabra.toUpperCase());
					linea=sb.toString();
					posicion=linea.indexOf(palabra);
					
				}

				raf.seek(pos);
                raf.writeBytes(linea + System.lineSeparator());
                
                pos = raf.getFilePointer();
				
			};
		} catch (EOFException e) {
			System.out.println("Fin del fichero");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			raf.close();
		}
	}

}
