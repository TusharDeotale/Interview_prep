package com.interviewprep.CoustomHashMap;

public class CustomHashMap<K, V> {

    private class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private int capacity = 16;
    private int size = 0;
    private final float loadFactor = 0.75f;
    private Entry<K, V>[] buckets;


    public CustomHashMap() {
        this.buckets = new Entry[capacity];
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Entry[capacity];
    }

    public int getSize() {
        return size;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> e = buckets[index];

        if (e == null) {
            buckets[index] = new Entry<>(key, value);
            size++;
        } else {
            while (e.next != null) {
                if (e.getKey() == key) {
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }

            if (e.getKey() == key) {
                e.setValue(value);
            }
            e.next = new Entry<>(key, value);
            size++;
        }

        if ((float) size / capacity >= loadFactor) {
            resize();
        }
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> e = buckets[index];

        if (e == null) {
            return null;
        }

        while (e != null) {
            if (e.getKey() == key) {
                return e.getValue();
            }
            e = e.next;
        }
        return null;
    }

    public V remove(K key){
        int index = hash(key);
        Entry<K, V> e = buckets[index];

        if (e == null) {
            return null;
        }

        if(e.getKey() == key){
            buckets[index] = e.next;
            e.next = null;
            size--;
            return e.getValue();
        }

        // To remove mid entry
        Entry<K, V> prev = e;
        e = e.next;
        while (e != null){
            if(e.getKey() == key){
                prev.next = e.next;
                e.next = null;
                size--;
                return e.getValue();
            }
            prev = e;
            e = e.next;
        }
        return null;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] newBuckets = new Entry[newCapacity];

        for (Entry<K, V> entry : buckets) {
            while (entry != null) {
                Entry<K, V> next = entry.next;

                int index = (entry.key.hashCode() & 0x7fffffff) % newCapacity;

                entry.next = newBuckets[index];
                newBuckets[index] = entry;

                entry = next;
            }
        }
        buckets = newBuckets;
        capacity = newCapacity;
    }
}
