public class TestLista {
    public static void main(String[] args) throws WrongIndexException {
        ListImpl<Integer> lista = new ListImpl<>();

        // Insertar elementos
        lista.insert(0, 10);
        lista.insert(1, 20);
        lista.insert(2, 30);
        System.out.println("Tamaño: " + lista.size());

        // Obtener elemento
        System.out.println("Elemento en pos 1: " + lista.get(1));

        // Buscar elemento
        System.out.println("Posición del 30: " + lista.search(30));

        // Eliminar elemento
        lista.delete(1);
        System.out.println("Tamaño tras borrar: " + lista.size());

        // Recorrer con for-each
        for (int elem : lista)
            System.out.println(elem);
    }
}