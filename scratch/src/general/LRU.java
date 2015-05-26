package general;

import java.util.HashMap;
import java.util.Map;
//http://codereview.stackexchange.com/questions/32849/design-lru-cache-interview-questions
public class LRU<K, V> {

    private final Map<K, Entry<K, V>> map;
    private Entry<K, V> eldest;
    private final int lruSize;

    public LRU (int lruSize) {
        if (lruSize <= 0) {
            throw new IllegalArgumentException("size should atleast be one");
        }
        map = new HashMap<K, Entry<K, V>>();
        this.lruSize = lruSize;
    }

    private static class Entry<K, V> {
        Entry<K, V> left;
        K key;
        V value;
        Entry<K, V> right;

        Entry (Entry<K, V> left, K key, V value, Entry<K, V> right) {
            this.left = left;
            this.key = key;
            this.value = value;
            this.right = right;
        }
        
        @Override
        public String toString()
        {
            return key.toString();
        }

    }

    /**
     * Head is first node of the linked list.
     * Similar to - convert tree to doubly linkedlist.
     */
    private void addFirst(Entry<K, V> entry) {
        remove(entry);
        if (eldest == null) {
            entry.left = entry.right = entry;
            eldest = entry;
        } else {
            // last node of the list. extra storage just for purpose of simplification
            Entry<K, V> tail = eldest.left;

            tail.right = entry;
            entry.left = tail;

            // now deal with circularing
            eldest.left = entry;
            entry.right = eldest;
        }
    }

    private void remove (Entry<K, V> entry) {
        assert entry != null;

        if (entry.left != null)
        {
            entry.left.right = entry.right;
        }
        if (entry.right != null){
            entry.right.left = entry.left;
        }
        if (entry == eldest) {
            eldest = entry.right;
        }
    }


    /**
     * Allowing null. Since, this is behavior of linkedhashmap.
     * Still adding synchronization as interviewer is bound to ask this question
     */
    public synchronized void put(K key, V value) {
        Entry<K, V> entry = new Entry<K, V>(null, key, value, null);
        map.put(key, entry);
        addFirst(entry);

        if (removeEldestEntry()) {
            remove(eldest.key);
        }
    }

    public synchronized V get (K key) {
        Entry<K, V> entry = map.get(key);
        if (entry != null) {
            addFirst(entry);
            return entry.value;
        }
        return null;
    }

    public synchronized void remove (K key) {
       Entry<K, V> entry = map.remove(key);
       if (entry != null) {
           remove(entry);
       }
    }

    private boolean removeEldestEntry() {
        return map.size() > lruSize;
    }
    
    public static void main(String[] args)
    {
        LRU<Integer, Integer> lrumap = new LRU<Integer, Integer>(5);
        for (int idx = 0; idx < 10; ++idx)
            lrumap.put(idx, idx);
        for (int idx = 0; idx < 10; ++idx)
            lrumap.put(idx, idx);
    }
}