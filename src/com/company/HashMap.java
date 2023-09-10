package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HashMap<K, V> {

    private class Element {
        public K key;
        public V value;

        Element (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    protected Element[] table = (Element[]) Array.newInstance(Element.class, 1000000);
    protected int size = 0;

    private int rehashing(K key, int index, boolean hasAlready) {
        int truthIndex = index;
        int countFilledCell = 0;

        while (true) {
            if ((table[truthIndex] == null && !hasAlready) || table[truthIndex].key.equals(key)) {
                break;
            }
            if (hasAlready && !table[truthIndex].key.equals(key)) {
                countFilledCell += 1;
            }
            if (countFilledCell == size) {
                truthIndex = -1;
                break;
            }

            if (index + 3 <= table.length) {
                truthIndex = 4 - (table.length - truthIndex);
            } else {
                truthIndex += 3;
            }
        }

        return truthIndex;
    }

    private int hash(K key) {
        String symbolsOfKey = key.toString();
        int len = symbolsOfKey.length();

        double h = 0;

        for (int i = 0; i < len; i++) {
            Object symbol = symbolsOfKey.charAt(i);
            h = (h * len) + symbol.hashCode();
        }

        return (int) (h % table.length);
    }


    public V put(K key, V value) {
        int index = hash(key);
        if (table[index] != null && !table[index].key.equals(key)) {
            index = rehashing(key, index, false);
        }

        boolean isNew = true;
        V oldValue = (table[index] == null) ? (null) : (table[index].value);
        if (table[index] != null && table[index].key.equals(key)) {
            isNew = false;
        }
        table[index] = new Element(key, value);
        size = (isNew) ? (size + 1) : (size);

        return oldValue;
    }

    public V get(K key) {
        int index = hash(key);

        if (table[index] == null) {
            return null;
        } else if (!table[index].key.equals(key)) {
            index = rehashing(key, index, true);
        }

        if (index == -1) {
            return null;
        }

        return table[index].value;
    }

    public ArrayList<Element> getSet() {
        ArrayList<Element> set = new ArrayList<>();
        int countOfFoundElem = 0;
        for (int i = 0; i < table.length; i++) {
            if (countOfFoundElem == size) {
                break;
            }
            if (table[i] != null) {
                set.add(table[i]);
                countOfFoundElem += 1;
            }
        }

        return set;
    }

    public V remove(K key) {
        int index = hash(key);
        if (table[index] != null && !table[index].key.equals(key)) {
            index = rehashing(key, index, true);
        }

        V oldValue = (index != -1) ? (null) : (table[index].value);
        table[index] = null;
        size = (index != -1) ? (size - 1) : (size);

        return null;
    }

    public int size() {
        return size;
    }

}
