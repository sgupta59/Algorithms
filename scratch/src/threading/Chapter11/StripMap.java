package threading.Chapter11;

/**
 * Reference http://www.cs.umd.edu/class/fall2013/cmsc433/examples/StripedMap.java
 * 
 * This is a lock striping example, prevents serialized access to the map
 * 
 * A small concurrent hashmap example.
 * Uses 16 locks to lock the buckets so say there are 4 locks and 24 buckets
 * bucket id: 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * Lock   id: 0 1 2 3 0 1 2 3 0 1 2   3  0  1  2  3  0
 * 
 * bucket index i.e. hash index = hashId%buckets.length
 * lock index = hash index % number of locks.
 * @author kg
 *
 * @param <K>
 * @param <V>
 */
public class StripMap<K,V> {

	private static final int NUM_LOCKS = 16;
	private final Node<K,V>[] buckets;
	private final Object[] locks;
	
	private static class Node<K,V> {
		K key;
		V value;
		Node<K,V> next;
		
		private Node(K key, V value, Node<K,V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	 
	public StripMap(int bucketSize) 
	{
		//buckets = (Node<K, V>[])new Object[bucketSize];
		buckets = (Node<K, V>[])new Node[bucketSize];
		locks = new Object[NUM_LOCKS];
		for (int i = 0; i < NUM_LOCKS;++i)
			locks[i] = new Object();
	}
	
	private int hash(K key)
	{
		return Math.abs(key.hashCode()%buckets.length);
	}
	
	public V get(K key)
	{
		// first get the bucket id
		int hashId = hash(key);
		// now lock based on the hash id.
		synchronized(locks[hashId%NUM_LOCKS]) {
			for (Node<K,V> m = buckets[hashId]; m != null; m = m.next) {
				if (m.key.equals(key)) {
					return m.value;
				}
			}
		}
		return null;
	}
	
	public V put(K key, V value) 
	{
		// get the bucket id
		int hashId = hash(key);
		// lock the specific lock
		synchronized(locks[hashId%NUM_LOCKS]) {
			for (Node<K,V> m = buckets[hashId]; m != null; m = m.next) {
				if (m.key.equals(key)) {
					m.value = value;
					return m.value;
				}
			}
			buckets[hashId] = new Node<K,V>(key, value, buckets[hashId]);
		}
		return null;
	}
	
	public void clear()
	{
		for (int i = 0; i < buckets.length; ++i) {
			synchronized(locks[i%NUM_LOCKS]) {
				buckets[i] = null;
			}
		}
	}
	
	public int size() 
	{
		int mc = 0;
		for (int i = 0; i < buckets.length; ++i) {
			synchronized(locks[i%NUM_LOCKS]) {
				for (Node<K,V> m = buckets[i]; m != null; m = m.next) {
					++mc;
				}
			}
		}
		return mc;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StripMap<Integer,String> map = new StripMap<Integer,String>(32);
		map.put(63, "abc");
		map.put(15, "efg");
		map.put(40, "efg");
		map.put(41, "test");
		map.put(80, "efg");
		String name =  map.get(20);
		map.size();
		map.clear();
	}

}
