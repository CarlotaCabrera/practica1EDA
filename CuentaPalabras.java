import java.io.*;
import java.util.*;

public class CuentaPalabras {
    public static void main(String[] args) {
        String fichero = args.length > 0 ? args[0] : "texto.txt";
        HashTable<String, Integer> tabla = new HashTable<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fichero));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] palabras = linea.split("[^a-zA-ZáéíóúÁÉÍÓÚñÑ]+");
                for (String palabra : palabras) {
                    if (!palabra.isEmpty()) {
                        palabra = palabra.toLowerCase();
                        if (tabla.contains(palabra))
                            tabla.put(palabra, tabla.get(palabra) + 1);
                        else
                            tabla.put(palabra, 1);
                    }
                }
            }
            reader.close();

            for (String palabra : tabla)
                System.out.println(palabra + ": " + tabla.get(palabra));

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado: " + fichero);
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}