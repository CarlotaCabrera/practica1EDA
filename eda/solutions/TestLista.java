package eda.solutions;
import eda.adt.*;
import eda.ds.*;
import eda.exceptions.*;
public class TestLista {
    public static void main(String[] args) throws WrongIndexException {
        ListImpl<Integer> lista = new ListImpl<>();

        lista.insert(0, 10);
        lista.insert(1, 20);
        lista.insert(2, 30);
        System.out.println("Tamaño: " + lista.size());

        System.out.println("Elemento en pos 1: " + lista.get(1));

        System.out.println("Posición del 30: " + lista.search(30));

        lista.delete(1);
        System.out.println("Tamaño tras borrar: " + lista.size());

        for (int elem : lista)
            System.out.println(elem);
    }
}