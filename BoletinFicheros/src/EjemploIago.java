import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class EjemploIago {

	public static void main(String[] args) {
		File miFichero=GestionFicheros.seleccionFichero();
		if(miFichero.exists()) {
			System.out.println("El fichero existe");
			try {
				FileReader fr=new FileReader(miFichero);
				BufferedReader bf=new BufferedReader(fr);
				String linea=null;
				while((linea=bf.readLine())!=null) {
					String [] datos=linea.split(";");
					for(String dato:datos) {
						System.out.println(dato);
					}
					System.out.println("---------");
				}
				fr.close();
				bf.close();
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
