public class TestTabla {
    public static void main(String[] args) {
        HashTable<String, Integer> tabla = new HashTable<>();

        // Insertar elementos
        tabla.put("uno", 1);
        tabla.put("dos", 2);
        tabla.put("tres", 3);
        System.out.println("Tamaño: " + tabla.size()); // 3

        // Obtener elemento
        System.out.println("uno: " + tabla.get("uno")); // 1

        // Comprobar si existe
        System.out.println("Contiene 'dos': " + tabla.contains("dos")); // true

        // Eliminar elemento
        tabla.remove("dos");
        System.out.println("Tamaño tras borrar: " + tabla.size()); // 2

        // Sustituir valor
        tabla.put("uno", 100);
        System.out.println("uno actualizado: " + tabla.get("uno")); // 100

        // Recorrer con for-each
        for (String key : tabla)
            System.out.println(key + " -> " + tabla.get(key));

        // Mostrar tabla
        System.out.println(tabla.toString());
    }
}