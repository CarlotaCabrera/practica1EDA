import java.io.*;

public class Espacio3DImpl<T> implements Serializable {
    private HashTable<String, T> tabla = new HashTable<>();

    public void insertar(int x, int y, int z, T dato) {
        tabla.put(x + "," + y + "," + z, dato);
    }

    public T recuperar(int x, int y, int z) {
        return tabla.get(x + "," + y + "," + z);
    }

    public void guardar(String fichero) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(this.tabla);
        }
    }

    public static <T> Espacio3DImpl<T> cargar(String fichero) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            Espacio3DImpl<T> nuevo = new Espacio3DImpl<>();
            nuevo.tabla = (HashTable<String, T>) ois.readObject();
            return nuevo;
        }
    }
}