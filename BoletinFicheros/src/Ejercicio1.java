import java.io.File;
import javax.swing.JOptionPane;
public class Ejercicio1 {


	public static void main(String[] args) {
			
		
		//String ruta=JOptionPane.showInputDialog(null, "Seleccione la ruta del fichero 1");
		///File miFicheroRutaAMano=new File(ruta);
		
		//if(miFicheroRutaAMano.exists()) {
		//	System.out.println("El fichero existe");
		//}
		
		File miFichero=GestionFicheros.seleccionFichero();
		
		if(miFichero.exists()) {
			System.out.println("El fichero existe");
			if(miFichero.isFile()) {
				System.out.println("Es un fichero");
				System.out.println("De nombre "+miFichero.getName());
				System.out.println("De tamaño "+miFichero.length()/1000000+" Mb");
				if (miFichero.canRead())
				{
					System.out.println("Se puede leer");
				}else
				{
					System.out.println("No se puede leer");
				}
				if (miFichero.canWrite())
				{
					System.out.println("Se puede escribir");
				}else
				{
					System.out.println("No se puede escribir");
				}
			}else if(miFichero.isDirectory()) {
				System.out.println("Es un directorio");
			}
		}else
		{
			System.out.println("El fichero NO existe");

		}

	}

}
