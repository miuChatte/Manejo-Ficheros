package fileManagement;

public class NoEsDirectorioException extends Exception {

	public NoEsDirectorioException() {
		super("La ruta no es un directorio");
	}
}
