public class TestABB {
    public static void main(String[] args) {
        BST<Integer> arbol = new BST<>();
        int[] datos = {50, 30, 70, 20, 40, 60, 80};
        
        for (int n : datos) arbol.add(n);

        System.out.println("Prueba");
        
        System.out.print("Preorden: ");
        arbol.preOrden();

        System.out.print("Inorden: ");
        arbol.inOrden();

        System.out.print("Postorden: ");
        arbol.postOrden();

        System.out.print("Anchura (Niveles): ");
        arbol.recorridoAnchura();

        System.out.println("\n--- PRUEBA mayoresQue(50) ---");
        System.out.print("Nodos mayores que 50: ");
        arbol.mayoresQue(50);
    }
}