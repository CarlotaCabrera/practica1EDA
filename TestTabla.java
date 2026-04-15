public class TestTabla {
    public static void main(String[] args) {
        HashTable<String, Integer> tabla = new HashTable<>();

        // Insertar elementos
        tabla.put("uno", 1);
        tabla.put("dos", 2);
        tabla.put("tres", 3);
        System.out.println("Tamaño: " + tabla.size());

        // Obtener elemento
        System.out.println("uno: " + tabla.get("uno"));

        // Comprobar si existe
        System.out.println("Contiene 'dos': " + tabla.contains("dos"));

        // Eliminar elemento
        tabla.remove("dos");
        System.out.println("Tamaño tras borrar: " + tabla.size());

        // Sustituir valor
        tabla.put("uno", 100);
        System.out.println("uno actualizado: " + tabla.get("uno"));

        // Recorrer con for-each
        for (String key : tabla)
            System.out.println(key + " -> " + tabla.get(key));

        // Mostrar tabla
        System.out.println(tabla.toString());
    }
}