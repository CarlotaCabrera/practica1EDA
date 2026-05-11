package eda.adt;
import eda.exceptions.*;
public interface Stack<E> {
    void push(E data);
    E pop() throws WrongIndexException;
    E peek() throws WrongIndexException;
    int size();
    boolean isEmpty();
}