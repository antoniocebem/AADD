
public class CamposIncompletosException extends Exception {

	public CamposIncompletosException() {
		super("Faltan datos por rellenar");
	}
	
	public CamposIncompletosException(String message) {
		super(message);
	}

}
