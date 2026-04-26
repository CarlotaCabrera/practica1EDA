import java.util.*;

public class GrafoLista<T> {
    // 1. Estructura interna para las conexiones
    private static class Arista {
        int destino;
        double peso;
        Arista(int d, double p) { this.destino = d; this.peso = p; }
    }

    // 2. Atributos de la clase
    private HashTable<T, Integer> nodos = new HashTable<>();
    private ListImpl<Arista>[] adyacencia; 
    private T[] etiquetas;
    private int numNodos = 0;

    // 3. Constructor
    @SuppressWarnings("unchecked")
    public GrafoLista(int capacidad) {
        this.adyacencia = new ListImpl[capacidad];
        this.etiquetas = (T[]) new Object[capacidad];
    }

    // 4. Añadir Nodo
    public void añadirNodo(T valor) {
        if (!nodos.contains(valor)) {
            nodos.put(valor, numNodos);
            etiquetas[numNodos] = valor;
            adyacencia[numNodos] = new ListImpl<>(); 
            numNodos++;
        }
    }

    // 5. Añadir Arista (con el try-catch para tu ListImpl)
    public void añadirArista(T origen, T destino, double peso) {
        Integer i = nodos.get(origen);
        Integer j = nodos.get(destino);
        
        if (i != null && j != null) {
            try {
                // Posición 0 primero, luego el objeto Arista
                adyacencia[i].insert(0, new Arista(j, peso));
            } catch (Exception e) {
                System.err.println("Error al insertar arista: " + e.getMessage());
            }
        }
    }

    // 6. Algoritmo de Dijkstra
    public void dijkstra(T inicio) {
        Integer start = nodos.get(inicio);
        if (start == null) return;

        double[] dist = new double[numNodos];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        // Cola de prioridad para optimizar la búsqueda del nodo más cercano
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(idx -> dist[idx]));
        pq.add(start);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            
            // Usamos el iterador de tu ListImpl para recorrer las aristas
            for (Arista a : adyacencia[u]) {
                if (dist[u] + a.peso < dist[a.destino]) {
                    dist[a.destino] = dist[u] + a.peso;
                    pq.add(a.destino);
                }
            }
        }

        System.out.println("Resultados Dijkstra desde " + inicio + ":");
        for (int i = 0; i < numNodos; i++) {
            System.out.println("Distancia a " + etiquetas[i] + ": " + dist[i]);
        }
    }
} // Cierre de la clase GrafoLista