import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
public class Ejercicio2 {



	public static void main(String[] args) {
			
		//JOptionPane.showMessageDialog(null, "Seleccione la ruta del fichero de restaurantes");
		//String ruta=JOptionPane.showInputDialog(null, "Seleccione la ruta del fichero de restaurantes");
		File restaurantes=GestionFicheros.seleccionFicheroCSV();
		if(restaurantes.exists()) {
			System.out.println("El fichero existe");
			try {
				FileReader fr = new FileReader(restaurantes);
				BufferedReader br = new BufferedReader(fr);
				String linea=null;
				while ((linea = br.readLine()) != null) {
					
					String [] datos=linea.split(";");
					String zipCode=datos[4];
					if(zipCode.startsWith("6")) {
						char a=zipCode.charAt(0);
						String comienzo=zipCode.substring(0, 1);
						System.out.println(a);
						System.out.println(comienzo);
						System.out.println(linea);
					}
					
					
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
