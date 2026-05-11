package eda.ds;
import eda.adt.*;
import eda.exceptions.*;
public interface DictionaryRepeatedKeys<K, V> extends Iterable<K> {
    void put(K key, V value);
    ListImpl<V> get(K key);
    int size();
}