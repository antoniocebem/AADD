import java.io.*;

public class Principal {

	public static void main(String[] args) {
		DataOutputStream ds = null;
		File miFichero = null;
		try {
			miFichero = GestionFicheros.seleccionFichero();
			FileOutputStream fe = new FileOutputStream(miFichero, true);
			ds = new DataOutputStream(fe);
			// array de enteros

			double[] array = { 5.25, 145.18, 2.37, 143.12, 147.10, 169.19, 47.65 };
			String[] nombres = { "Beatriz", "Ana", "Maria", "Juan", "Pedro", "Teresa", "Marta" };
			for (double i : array) {
				ds.writeDouble(i);
			}
			for (String s : nombres) {
				//ds.writeUTF(s);
			}
			// ANÁLOGO A LO ANTERIOR
			for (int i = 0; i < nombres.length; i++) {
				// ds.writeUTF(nombres[i]);
			}

		} catch (FileNotFoundException fnfe) {
			System.out.println("No se pudo abrir el fichero Enteros.txt");

		} catch (IOException e) {
			System.out.println("No se pudo escribir en el fichero Enteros.txt");
		} finally {
			try {
				ds.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			leerFicheroBinario(miFichero);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void leerFicheroBinario(File ficheroALeer) throws IOException {
		try {
			FileInputStream fis=new FileInputStream(ficheroALeer);
			DataInputStream dis=new DataInputStream(fis);
			while(true) {
				System.out.println(dis.readDouble());
			}
		} catch(EOFException eof) {
			eof.printStackTrace();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}