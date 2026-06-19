package eda.solutions;
import eda.adt.*;
import eda.ds.*;
import eda.exceptions.*;
public class TestTabla {
    public static void main(String[] args) {
        HashTable<String, Integer> tabla = new HashTable<>();

        tabla.put("uno", 1);
        tabla.put("dos", 2);
        tabla.put("tres", 3);
        System.out.println("Tamaño: " + tabla.size());

        System.out.println("uno: " + tabla.get("uno"));

        System.out.println("Contiene 'dos': " + tabla.contains("dos"));

        tabla.remove("dos");
        System.out.println("Tamaño tras borrar: " + tabla.size());

        tabla.put("uno", 100);
        System.out.println("uno actualizado: " + tabla.get("uno"));

        for (String key : tabla)
            System.out.println(key + " -> " + tabla.get(key));

        System.out.println(tabla.toString());
    }
}