package collection;

/***
 * @author Vishal Tavande
 *
 * @param key to be stored
 * @param value to be stored
 */
public class CustomLinkedHashMap<K, V> {

    private int capacity;

    private final int DEFAULT_CAPACITY = 5;

    private static class Node<K,V> {
        private K key;
        private V value;
        private Node next;
        private Node before, after;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = this.before = this.after = null;
        }
    }

    private Node head, tail;

    private Node table[];

    public CustomLinkedHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Node[this.capacity];
        this.head = this.tail = null;
    }

    /***
     * Used to retrieve <b>value</b> inside {@link CustomLinkedHashMap} instance
     * @param key
     * @return {@code null} if map does not find matching key in it. If matching key found then it will return that <b>value</b>.
     */
    public V get(K key) {
        return getValue(hash(key), key);
    }

    /***
     * Used to insert <b>key-value</b> pair inside {@link CustomLinkedHashMap} instance
     * @param key
     * @param value
     * @return {@code null} if map does not find matching key in it. If matching key found then it will replace old value with new value and return old value
     */
    public V put(K key, V value) {
        return putValue(hash(key), key, value);
    }

    /***
     * More specific {@code get} method which is internal to {@link CustomLinkedHashMap} class
     * @param hash
     * @param key
     * @return {@code null} if map does not find matching key in it. If matching key found then it will return that <b>value</b>.
     */
    private V getValue(int hash, K key) {
        int index = hash%capacity;

        Node tmp = table[index];

        while(tmp != null) {
            if(key.equals(tmp.key))
                return tmp.value;
            tmp = tmp.next;
        }
        return null;
    }

    /***
     * More specific {@code put} method which is internal to {@link CustomLinkedHashMap} class
     * @param hash
     * @param key
     * @param value
     * @return {@code null} if map does not find matching key in it. If matching key found then it will replace old value with new value and return old value
     */
    private V putValue(int hash, K key, V value) {
        Node temp = new Node(key, value);
        int index = hash%capacity;
        if(table[index] == null) {
            //update head
            if(head == null) {
                head = tail = temp;
                table[index] = temp;
                return null;
            }
            table[index] = temp;

            if(tail != null) {
                tail.after = temp;
                temp.before = tail;
                tail = temp;
            }
            return null;
        }
        else {
            Node tmp = table[index];
            Node prev = null;
            while(tmp != null) {
                if(temp.key.equals(tmp.key)) {
                    V val = tmp.value;
                    tmp.value = temp.value;
                    return val;
                }
                prev = tmp;
                tmp = tmp.next;
            }

            prev.next = temp;
            temp.before = tail;
            tail.after = temp;
            tail = temp;
        }
        return null;
    }

    /***
     * Removes the key-value pair <b>if present</b> from the {@link CustomLinkedHashMap} instance.
     * @param key
     * @return {@code null} if no matching key found in the {@link CustomLinkedHashMap} instance. old value will be returned if key is matched and this
     * key-value pair will be removed from the {@link CustomLinkedHashMap} instance.
     */
    public V remove(K key) {
        return removeValue(hash(key), key);
    }

    /***
     * More specific {@code remove} method which is internal to {@link CustomLinkedHashMap} class
     * @param hash
     * @param key
     * @return {@code null} if no matching key found in the {@link CustomLinkedHashMap} instance. old value will be returned if key is matched and this
     * key-value pair will be removed from the {@link CustomLinkedHashMap} instance.
     */
    private V removeValue(int hash, K key) {

        int index = hash%capacity;

        Node tmp = table[index];
        Node prev = null;

        while(tmp != null) {
            if(key.equals(tmp.key)) {
                //check if its head
                if(tmp.equals(head)) {
                    V value = head.value;
                    head = head.after;
                    tmp = tmp.next;
                    return value;
                }
                else if(tmp.equals(tail)) {
                    //if its tail
                    V value = tail.value;
                    tail = tail.before;
                    tail.after = null;
                    prev.next = null;
                    return value;
                }
                else
                {
                    tmp.before.after = tmp.after;
                    tmp.after.before = tmp.before;
                    return tmp.value;
                }
            }
            prev = tmp;
            tmp = tmp.next;
        }
        return null;
    }

    /***
     * Used to calculate hash value of a key. It is guaranteed that it will always lies in 0 to capacity-1
     * @param key
     * @return hash index
     */
    private int hash(K key) {
        return this.capacity%key.hashCode();
    }
}
