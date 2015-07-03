package generics.Maps;

import java.util.Collection;
import java.util.Set;
/**
 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/Map.java#Map.get%28java.lang.Object%29
 * @author kg
 *
 * @param <K>
 * @param <V>
 */
interface Map<K,V> {
	
	// return size of the map. Maximum value is Integer.MAX_INTEGER.
	int size();
	
	boolean isEmpty();
	
	// might throw uncheckedexceptions
	// @throws ClassCastException
	// @throws NullPointerException
	boolean containsKey(Object key);
	
	// returns true if value == null ? v == null : value.equals(v)
	// liner complexity.
	boolean containsValue(Object value);
	
	V put (K key, V value);
	
	V get(Object key);
	
	V remove(Object key);
	
	void putAll(Map<? extends K, ? extends V> m);
	
	// @throws UnsupportedOperationException (unchekced exception)
	public void clear();
	
	Set<K> keySet();
	
	Collection<V> values();
	
	Set<Map.Entry<K,V>> entrySet();
	
	
	interface Entry<K,V> {
		
		K getKey();
		
		V getValue();
		
		V setValue(V val);
		
		int hashCode();
		
		boolean equals(Object o);
	}
	
	
}