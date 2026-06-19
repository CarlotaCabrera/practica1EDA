package eda.ds;
import eda.adt.*;
import eda.exceptions.*;
import java.util.*;

public class GrafoLista<T> {
    private static class Arista {
        int destino;
        double peso;
        Arista(int d, double p) { this.destino = d; this.peso = p; }
    }

    private HashTable<T, Integer> nodos = new HashTable<>();
    private ListImpl<Arista>[] adyacencia; 
    private T[] etiquetas;
    private int numNodos = 0;

    @SuppressWarnings("unchecked")
    public GrafoLista(int capacidad) {
        this.adyacencia = new ListImpl[capacidad];
        this.etiquetas = (T[]) new Object[capacidad];
    }

    public void añadirNodo(T valor) {
        if (!nodos.contains(valor)) {
            nodos.put(valor, numNodos);
            etiquetas[numNodos] = valor;
            adyacencia[numNodos] = new ListImpl<>(); 
            numNodos++;
        }
    }

    public void añadirArista(T origen, T destino, double peso) {
        Integer i = nodos.get(origen);
        Integer j = nodos.get(destino);
        
        if (i != null && j != null) {
            try {
                adyacencia[i].insert(0, new Arista(j, peso));
            } catch (Exception e) {
                System.err.println("Error al insertar arista: " + e.getMessage());
            }
        }
    }

    public void dijkstra(T inicio) {
        Integer start = nodos.get(inicio);
        if (start == null) return;

        double[] dist = new double[numNodos];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(idx -> dist[idx]));
        pq.add(start);

        while (!pq.isEmpty()) {
            int u = pq.poll();

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
}