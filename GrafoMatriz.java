import java.util.*;

public class GrafoMatriz<T> {
    private double[][] matriz;
    private HashTable<T, Integer> nodos;
    private T[] etiquetas;
    private int numNodos;

    public GrafoMatriz(int capacidadInicial) {
        matriz = new double[capacidadInicial][capacidadInicial];
        nodos = new HashTable<>();
        etiquetas = (T[]) new Object[capacidadInicial];
        numNodos = 0;
        for (double[] fila : matriz) Arrays.fill(fila, Double.POSITIVE_INFINITY);
    }

    public void añadirNodo(T valor) {
        if (!nodos.contains(valor)) {
            if (numNodos == matriz.length) redimensionar();
            nodos.put(valor, numNodos);
            etiquetas[numNodos] = valor;
            matriz[numNodos][numNodos] = 0;
            numNodos++;
        }
    }

    private void redimensionar() {
        int nuevaCap = matriz.length * 2;
        double[][] nuevaMatriz = new double[nuevaCap][nuevaCap];
        for (double[] fila : nuevaMatriz) Arrays.fill(fila, Double.POSITIVE_INFINITY);
        for (int i = 0; i < matriz.length; i++) 
            System.arraycopy(matriz[i], 0, nuevaMatriz[i], 0, matriz.length);
        matriz = nuevaMatriz;
        etiquetas = Arrays.copyOf(etiquetas, nuevaCap);
    }

    public void añadirArista(T origen, T destino, double peso) {
        Integer i = nodos.get(origen);
        Integer j = nodos.get(destino);
        if (i != null && j != null) matriz[i][j] = peso;
    }

    // BFS (Anchura)
    public void BFS(T inicio) {
        Integer idx = nodos.get(inicio);
        if (idx == null) return;
        boolean[] visitados = new boolean[numNodos];
        java.util.Queue<Integer> cola = new java.util.LinkedList<>();
        cola.add(idx);
        visitados[idx] = true;
        while (!cola.isEmpty()) {
            int u = cola.poll();
            System.out.print(etiquetas[u] + " ");
            for (int v = 0; v < numNodos; v++) {
                if (matriz[u][v] != Double.POSITIVE_INFINITY && !visitados[v]) {
                    visitados[v] = true;
                    cola.add(v);
                }
            }
        }
        System.out.println();
    }
}