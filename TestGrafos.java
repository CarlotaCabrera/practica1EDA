public class TestGrafos {
    public static void main(String[] args) {
        GrafoLista<String> g = new GrafoLista<>(10);
        g.añadirNodo("Alcoi");
        g.añadirNodo("Valencia");
        g.añadirNodo("Alicante");
        
        g.añadirArista("Alcoi", "Valencia", 100);
        g.añadirArista("Alcoi", "Alicante", 60);
        g.añadirArista("Alicante", "Valencia", 150);

        System.out.println("Prueba 4");
        g.dijkstra("Alcoi");
        
        GrafoMatriz<String> gm = new GrafoMatriz<>(5);
        gm.añadirNodo("A"); gm.añadirNodo("B"); gm.añadirNodo("C");
        gm.añadirArista("A", "B", 1);
        gm.añadirArista("B", "C", 1);
        
        System.out.println("\nPrueba BFS desde A");
        gm.BFS("A");
    }
}