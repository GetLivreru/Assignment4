import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Node<K,V>> {

    private Node<K, V> root; // root node of the binary search tree
    private int size; // number of nodes in the binary search tree

    public static class Node<K, V> {
        private K key; // key associated with the node
        private V value; // value associated with the node
        private Node<K, V> left, right; // left and right children of the node

        public Node(K key, V value) {  // constructor
            this.key = key;
            this.value = value;
        }
    }
    
    
public void put(K key, V value) { // insert a key-value pair into the binary search tree
        root = put(root, key, value); // recursively insert the node
    }
    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++; // increment the size of the binary search tree
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key); // compare the given key with the key of the current node
        if (cmp < 0) { // if the given key is less than the key of the current node, insert the key-value pair into the left subtree
            node.left = put(node.left, key, value);
        } else if (cmp > 0) { // if the given key is greater than the key of the current node, insert the key-value pair into the right subtree
            node.right = put(node.right, key, value);
        } else { // if the given key is equal to the key of the current node, update the value of the node with the given value
            node.value = value;
        }
        return node;
    }
    
          public V get(K key) { // get the value associated with the given key
        Node<K, V> node = get(root, key); // recursively get the node with the given key
        return node == null ? null : node.value; // return the value of the node if it exists, otherwise return null
    }
    
    private Node<K, V> get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }
    public void delete(K key) {  // delete the node with the given key from the binary search tree
        root = delete(root, key); // recursively delete the node
    }
    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) {
                size--;
                return node.left;
            }
            if (node.left == null) {
                size--;
                return node.right;
            }
            Node<K, V> temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }
private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new InOrderIterator<>(root);
    }

    private static class InOrderIterator<K extends Comparable<K>, V> implements Iterator<Node<K, V>> {

        private Node<K, V> current;
        private Stack<Node<K, V>> stack;

        public InOrderIterator(Node<K, V> root) {
            current = root;
            stack = new Stack<>();
        }
        @Override
        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }

        @Override
        public Node<K, V> next() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            Node<K, V> node = current;
            current = current.right;
            return node;
        }
    }
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "five");
        tree.put(2, "two");
        tree.put(7, "seven");
        tree.put(1, "one");
        tree.put(6, "six");
         System.out.println("Size: " + tree.size());

        for (Node<Integer, String> node : tree) {
            System.out.println("Key: " + node.key + " Value: " + node.value);
        }

        tree.delete(2);

        System.out.println("Size: " + tree.size());

        for (Node<Integer, String> node : tree) {
            System.out.println("Key: " + node.key + " Value: " + node.value);
            
        }
    }
}

