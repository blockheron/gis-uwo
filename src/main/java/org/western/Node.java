package org.western;

/*
 * Node class is generic and can be used for any type of key and value
 * Node class extends Comparable so that it can be compared to other nodes
 * @author: Maxwell Ding
 */
public class Node {
    private Node left;
    private Node right;
    private int height;
    private Object key;
    private Object value;
    // Node constructor
    public <K extends Comparable<K>, V> Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.height = 0;
    }

    public <K, V extends Comparable<V>> Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.height = 0;
    }

    // Getters and setters
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public Object getKey() {
        return key;
    }
    public void setKey(Object key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    // compareTo() compares the node to another node
    public int compareTo(Node node) {
        return ((Comparable) value).compareTo(node.getValue());
    }
}
