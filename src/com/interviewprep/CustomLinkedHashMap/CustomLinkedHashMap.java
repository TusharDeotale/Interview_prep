package com.interviewprep.CustomLinkedHashMap;

import com.interviewprep.CoustomHashMap.CustomHashMap;

public class CustomLinkedHashMap<K, V> {

    private class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> prev, next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
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
    private Entry<K, V> head, tail;

    public CustomLinkedHashMap() {
        this.buckets = new Entry[capacity];
    }

    public CustomLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Entry[this.capacity];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> e = buckets[index];
        Entry<K, V> newEntry;
        if (e == null) {
            newEntry = new Entry<>(key, value);
            buckets[index] = newEntry;
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
            newEntry = new Entry<>(key, value);
            e.next = newEntry;
            size++;
        }

        if (tail == null) {
            head = tail = newEntry;
        } else {
            tail.next = newEntry;
            newEntry.prev = tail;
            tail = newEntry;
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

    public void display() {
        Entry<K, V> curr_entry = head;
        while (curr_entry != null) {
            System.out.println(curr_entry.getKey() + " = " + curr_entry.getValue());
            curr_entry = curr_entry.next;
        }
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
