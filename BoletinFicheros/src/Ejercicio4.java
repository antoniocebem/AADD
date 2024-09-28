import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio4 {

	public static void main(String[] args) {
		File ficheroOriginal=GestionFicheros.seleccionFicheroCSV();
		File ficheroSalida = GestionFicheros.crearFicheroCSV("miFichero3CSV.csv");
		if(ficheroOriginal.exists()) {
			try {
				FileReader fr = new FileReader(ficheroOriginal);
				BufferedReader br = new BufferedReader(fr);
				String linea=null;
				while ((linea = br.readLine()) != null) {
					
					String [] datos=linea.split(";");
					String zipCode=datos[4];
					if(!zipCode.startsWith("6")) {
						GestionFicheros.anhadirLineaCSV(ficheroSalida, linea);

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
