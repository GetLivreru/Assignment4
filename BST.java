import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Node<K,V>> {

    private Node<K, V> root;
    private int size;

    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    
public void put(K key, V value) {
        root = put(root, key, value);
    }
    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }
    
          public V get(K key) {
        Node<K, V> node = get(root, key);
        return node == null ? null : node.value;
    }
