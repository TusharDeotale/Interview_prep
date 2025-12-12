package com.interviewprep.LRU;

import java.util.HashMap;
import java.util.Map;

public class LRUcache<K, V> {

    private final int capacity;
    private int size;
    private final Map<Object, Node> hashMap;
    private final DoublyLinkedList internalQueue;

    public LRUcache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.internalQueue = new DoublyLinkedList();
    }

    public V get(K key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        internalQueue.moveNodeToFront(node);
        return hashMap.get(key).value;
    }

    public void put(K key, V value) {
        Node currentNode = hashMap.get(key);

        if (currentNode != null) {
            currentNode.value = value;
            internalQueue.moveNodeToFront(currentNode);
            return;
        }

        if (size == capacity) {
            K rearNodeKey = internalQueue.getRearKey();
            internalQueue.removeNodeFromRear();
            hashMap.remove(rearNodeKey);
            size--;
        }

        Node node = new Node(key, value);
        internalQueue.addNodeToFront(node);
        hashMap.put(key, node);
        size++;

    }

    private class Node {
        K key;
        V value;
        Node next, prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private class DoublyLinkedList {
        private Node front, rear;

        public DoublyLinkedList() {
            this.front = null;
            this.rear = null;
        }

        private void addNodeToFront(final Node node) {
            if (rear == null) {
                front = rear = node;
                return;
            }

            node.next = front;
            front.prev = node;
            front = node;
        }

        private void moveNodeToFront(final Node node) {
            if (node == front) {
                return;
            }

            if (node == rear) {
                rear = node.prev;
                rear.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.prev = null;
            node.next = front;
            front.prev = node;
            front = node;

        }

        private void removeNodeFromRear() {
            System.out.println("Deleting key :: " + rear.key);
            if (front == rear) {
                front = rear = null;
            } else {
                rear = rear.prev;
                rear.next = null;
            }
        }

        public K getRearKey() {
            return rear.key;
        }
    }

}
