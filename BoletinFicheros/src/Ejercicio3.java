import java.io.File;
import javax.swing.JOptionPane;

public class Ejercicio3 {

	

	public static void main(String[] args) {
		File ficheroRestaurantes=GestionFicheros.seleccionFicheroCSV();
		String nombre,direccion,ciudad,provincia,zipCode;
		nombre=JOptionPane.showInputDialog(null, "Introduce el nombre");
		direccion=JOptionPane.showInputDialog(null, "Introduce la direccion");
		ciudad=JOptionPane.showInputDialog(null, "Introduce la ciudad");
		provincia=JOptionPane.showInputDialog(null, "Introduce la provincia");
		zipCode=JOptionPane.showInputDialog(null, "Introduce el zipCode");
		
		String linea=nombre+';'+direccion+';'+ciudad+';'+provincia+';'+zipCode;
		GestionFicheros.anhadirLineaCSV(ficheroRestaurantes, linea);
	}
	
}
