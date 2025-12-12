package com.interviewprep.CustomConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class CustomConcurrentHashMap<K, V> {

    private static class Entry<K, V> {
        final K key;
        volatile V value;
        volatile Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private volatile AtomicReferenceArray<Entry<K, V>> buckets;
    private volatile int capacity = 16;
    private final float loadFactor = 0.75f;
    private volatile int size = 0;

    public CustomConcurrentHashMap() {
        this(16);
    }

    public CustomConcurrentHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new AtomicReferenceArray<>(capacity);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> e = buckets.get(index);
        while (e != null) {
            if (e.key.equals(key)) return e.value;
            e = e.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> head = buckets.get(index);

        if (head == null) {
            // compare and swap if bucket empty
            Entry<K, V> newEntry = new Entry<>(key, value);
            if (buckets.compareAndSet(index, null, newEntry)) {
                size++;
                return;
            }
            head = buckets.get(index);
        }

        synchronized (head) {
            Entry<K, V> e = head;
            while (true) {
                if (e.key.equals(key)) {
                    e.value = value;
                    return;
                }
                if (e.next == null) {
                    e.next = new Entry<>(key, value);
                    size++;
                    return;
                }
                e = e.next;
            }
        }
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> head = buckets.get(index);
        if (head == null) return null;

        synchronized (head) {
            if (head.key.equals(key)) {
                buckets.set(index, head.next);
                size--;
                return head.value;
            }

            Entry<K, V> prev = head;
            Entry<K, V> e = head.next;
            while (e != null) {
                if (e.key.equals(key)) {
                    prev.next = e.next;
                    size--;
                    return e.value;
                }
                prev = e;
                e = e.next;
            }
        }
        return null;
    }
}