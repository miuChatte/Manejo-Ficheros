package fileManagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileSync {
		
	/**
	 * Método para sincronizar archivos desde el origen al destino
	 * @param origen: directorio del origen
	 * @param destino: directorio del destino
	 * @throws NoEsDirectorioException si la ruta origen no es un directorio
	 * @throws IOException 
	 */
    public static void sincronizarDirectorio(File origen, File destino) throws NoEsDirectorioException, IOException {
        if (!origen.isDirectory()) {
    		throw new NoEsDirectorioException();
        }
        
        File[] archivosOrigen = origen.listFiles();
        if (archivosOrigen != null) {
            for (File archivoOrigen : archivosOrigen) {
                if (archivoOrigen.isDirectory()) {
                    sincronizarDirectorio(archivoOrigen, new File(destino, archivoOrigen.getName()));
                } else {
                    File archivoDestino = new File(destino, archivoOrigen.getName());
                    if (!archivoDestino.exists() || archivoOrigen.lastModified() > archivoDestino.lastModified()) {
                        copiarArchivo(archivoOrigen, archivoDestino);
                    }
                }
            }
        }
    }
	
	
	
	/**
	 * Este método recibe un directorio y retorna: true - existe / false - no existe
	 * @param ruta: directorio del archivo
	 * @param nombre: nombre del archivo
	 * @return booleano dependiendo del estado del archivo
	 */
	
	public static boolean existeArchivo(File ruta, String nombre) {

		File newFile= new File(ruta, nombre);
		
		return newFile.exists();
	}
	
	
	/**
	 * Método para copiar un archivo
	 * @param origen: directorio de origen
	 * @param destino: directorio de destino
	 */
    public static void copiarArchivo(File origen, File destino) throws IOException {
        if (!destino.exists()) {
            destino.createNewFile();
        }
        Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Archivo copiado: " + origen.getName());
    }
    
	
	/**
	 * Este método elimina archivos del destino si ya no existen en el origen
	 * @param origen: directorio de origen
	 * @param destino: directorio de destino
	 * @throws NoEsDirectorioException si la ruta no es un directorio
	 */
    public static void eliminarArchivosDesactualizados(File origen, File destino) throws NoEsDirectorioException {
    	
            File[] archivosDestino = destino.listFiles();
            if (archivosDestino != null) {
                for (File archivoDestino : archivosDestino) {
                    if (archivoDestino.isFile()) {
                        File archivoOrigen = new File(origen, archivoDestino.getName());
                        if (!archivoOrigen.exists()) {
                            archivoDestino.delete();
                            System.out.println("Archivo eliminado: " + archivoDestino.getName());
                        }
                    }
                }
            }else {
    		throw new NoEsDirectorioException();
    	}
    	
    }
    
    
}
