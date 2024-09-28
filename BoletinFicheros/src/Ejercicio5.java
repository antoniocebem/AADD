import java.io.File;
public class Ejercicio5 {

	public static void main(String[] args) {
		
		File ficheroABorrar=GestionFicheros.seleccionFichero();
		try {
		if(ficheroABorrar.exists()) {
			if(ficheroABorrar.delete()) {
				System.out.println("Fichero borrado");
			}else {
				System.out.println("No se ha podido borrar el fichero");
			}
		}
		}catch(NullPointerException e) {
			System.out.println("No se ha seleccionado un fichero");
		}

	}

}
