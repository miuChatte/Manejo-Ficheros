package Consola;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import fileManagement.FileSync;
import fileManagement.NoEsDirectorioException;

public class Main {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException, NoEsDirectorioException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce ruta de origen:");
        String sourcePath = scanner.nextLine();
        File sourceDir = new File(sourcePath);

        if (!sourceDir.isDirectory()) {
            throw new NoEsDirectorioException();
        }

        listProperties(sourceDir);

        System.out.println("Contenidos del directorio origen:");
        System.out.println(listDirectory(sourceDir, ""));

        System.out.println("Introduce ruta de destino:");
        String destPath = scanner.nextLine();
        File destDir = new File(destPath);

        if (!destDir.isDirectory()) {
            throw new NoEsDirectorioException();
        }

        System.out.println("Sincronizando directorios...");
        FileSync.sincronizarDirectorio(sourceDir, destDir);

        System.out.println("Eliminando archivos desactualizados en destino...");
        FileSync.eliminarArchivosDesactualizados(sourceDir, destDir);

        scanner.close();
    }

	    static void listProperties(File file) {
	        if (file.exists()) {
	            System.out.println("Nombre de archivo " + file.getName());
	            System.out.println("Ejecutable " + (file.canExecute() ? "Si" : "No"));
	            System.out.println("Tamaño de archivo: " + file.length() + " bytes");
	        } else {
	            System.out.println("Archivo: no existe");
	        }
	    }

	    static String listDirectory(File directory, String tab) throws NoEsDirectorioException  {
	        if (!directory.isDirectory()) {
        		throw new NoEsDirectorioException();	            
	        }

	        StringBuilder result = new StringBuilder();
	        File[] files = directory.listFiles();

	        for (File f : files) {
	            if (!f.isDirectory()) {
	                result.append(tab).append(f.getName()).append("\n");
	            } else {
	                result.append(tab).append(f.getName()).append("\n")
	                        .append(listDirectory(f, tab + "\t"));
	            }
	        }

	        return result.toString();
	    }
	}
