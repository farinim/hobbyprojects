import java.util.LinkedHashMap;
import java.util.LinkedList;

public class LRUCache<K, V> {

    class Node {
        K next, prev;
        V value;
    }

    LinkedHashMap<K, Integer> internalMap;
    LinkedList<Node> keystore;
    int size;
    K head, tail;

    LRUCache(int size) {
        this.size = size;
        internalMap = new LinkedHashMap<K, Integer>(5);
        keystore = new LinkedList<Node>();
        head = null;
        tail = null;
    }
    public int size() {
        return internalMap.size();
    }

    void add( K key, V value ) {
        int index = keystore.size();
        if (index == size) {
            index = internalMap.get(tail);
            evict();
            Node tobeUpdated = keystore.get(index);
            tobeUpdated.value = value;
            tobeUpdated.next = null;
            tobeUpdated.prev = head;
            keystore.get(internalMap.get(head)).next = key;
            head =  key;
        }else{
            Node newNode = new Node();
            newNode.next= null;
            newNode.value = value;
            if(index == 0){
                newNode.prev = null;
                tail = key;
            }else{
                keystore.get(internalMap.get(head)).next = key;
                newNode.prev = head;
            }
            head = key;
            keystore.add( newNode);
        }
        internalMap.put(key, index);

    }

    void evict() {
        K newTail = keystore.get(internalMap.get(tail)).next;
        internalMap.remove(tail);
        tail = newTail;
    }

    void hit( K key ) {
        if(key.equals(head))
            return;
        Integer index = internalMap.get(key);
        if(index == null)
            return;
        Node accessedNode = keystore.get(index);
        K next = accessedNode.next;
        K prev = accessedNode.prev;
        if(null != next){
            keystore.get(internalMap.get(next)).next=key;
        }
        accessedNode.next = null;
        accessedNode.prev = head;
        if(prev != null)
            keystore.get(internalMap.get(prev)).next = next;
        keystore.get(internalMap.get(next)).prev = prev;
        head = key;
        if(tail != null && tail.equals(key)){
            if(keystore.size() == 1){
                return;
            }
            tail=next;
        }
    }

    V get( K key ) {
        hit(key);
        Integer index = internalMap.get(key);
        return index == null ?  null :  keystore.get(index).value;
    }
}
