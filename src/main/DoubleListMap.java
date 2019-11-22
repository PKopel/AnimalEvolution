package main;

import java.util.Map;
import java.util.*;

public class DoubleListMap<K, V> implements Map<K, V> {
    private static final int SIZE = 997;
    @SuppressWarnings("unchecked")
    private
    my.util.LinkedEntry<K, V>[] buckets = new my.util.LinkedEntry[SIZE];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        int index = Math.abs(key.hashCode()) % SIZE;
        V oldValue = null;
        my.util.LinkedEntry<K, V> temp = buckets[index];
        my.util.LinkedEntry<K, V> newEntry =
                new my.util.LinkedEntry<K, V>(key, value, null, null);
        if (buckets[index] == null) {
            buckets[index] = newEntry;
            size++;
        } else if (!this.containsKey(key)) {
            while (temp.hasNext()) temp = temp.getNext();
            temp.setNext(newEntry);
            newEntry.setPrevious(temp);
            size++;
        } else {
            while (temp.hasNext() && !temp.getKey().equals(key))
                temp = temp.getNext();
            oldValue = temp.getValue();
            temp.setValue(value);
        }
        return oldValue;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) this.put(key, m.get(key));

    }

    @Override
    public boolean containsKey(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return false;
        my.util.LinkedEntry<K, V> temp = buckets[index];
        while (temp.hasNext() && !temp.getKey().equals(key))
            temp = temp.getNext();
        return temp.getKey().equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (my.util.LinkedEntry<K, V> l : buckets) {
            if (l != null)
                do {
                    if (l.getValue().equals(value)) return true;
                    else l = l.getNext();
                } while (l != null);
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if (this.containsKey(key)) {
            int index = Math.abs(key.hashCode()) % SIZE;
            my.util.LinkedEntry<K, V> temp = buckets[index];
            while (temp.hasNext() && !temp.getKey().equals(key))
                temp = temp.getNext();
            return temp.getValue();
        } else return null;
    }

    @Override
    public V remove(Object key) {
        if (this.containsKey(key)) {
            int index = Math.abs(key.hashCode()) % SIZE;
            my.util.LinkedEntry<K, V> n = buckets[index];
            do {
                if (n.getKey().equals(key)) {
                    if (n.hasNext() && n.hasPrevious()) {
                        n.getPrevious().setNext(n.getNext());
                        n.getNext().setPrevious(n.getPrevious());
                    } else if (!n.hasNext() && n.hasPrevious()) {
                        n.getPrevious().setNext(null);
                    } else if (n.hasNext() && !n.hasPrevious()) {
                        buckets[Math.abs(key.hashCode()) % SIZE] = n.getNext();
                        n.getNext().setPrevious(null);
                    } else
                        buckets[Math.abs(key.hashCode()) % SIZE] = null;
                    size--;
                    return n.getValue();
                } else n = n.getNext();
            } while (n != null);
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < 997; i++) buckets[i] = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (
                my.util.LinkedEntry<K, V> temp : buckets)
            while (temp != null) {
                keySet.add(temp.getKey());
                temp = temp.getNext();
            }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<V>();
        for (my.util.LinkedEntry<K, V> temp : buckets)
            while (temp != null) {
                values.add(temp.getValue());
                temp = temp.getNext();
            }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet =
                new HashSet<Map.Entry<K, V>>();
        for (my.util.LinkedEntry<K, V> temp : buckets)
            while (temp != null) {
                entrySet.add(temp);
                temp = temp.getNext();
            }
        return entrySet;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (my.util.LinkedEntry<K, V> temp : buckets)
            if (temp != null)
                while (temp != null) {
                    builder.append(", " + temp.getKey()
                            + "==" + temp.getValue());
                    temp = temp.getNext();
                }
        if (!this.isEmpty())
            builder.replace(1, 3, "");
        builder.append("}");
        return builder.toString();
    }
}
