public class TestABB {
    public static void main(String[] args) {
        BST<Integer> arbol = new BST<>();
        int[] datos = {50, 30, 70, 20, 40, 60, 80};
        
        System.out.println("Insertando elementos...");
        for (int n : datos) arbol.add(n);

        System.out.println("Recorrido en orden:");
        for (Integer n : arbol) {
            System.out.print(n + " ");
        }
        
        System.out.println("\nBorrando el 30...");
        arbol.delete(30);
        
        System.out.print("Nuevo orden: ");
        for (Integer n : arbol) System.out.print(n + " ");
    }
}
