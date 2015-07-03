package generics.Maps;
/**
 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/AbstractMap.java#AbstractMap.SimpleImmutableEntry.equals%28java.lang.Object%29
 */
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public abstract class AbstractMap<K,V> implements Map<K,V> {
	
	protected AbstractMap() {
		
	}
	
	 transient volatile Set<K> keySet;
	 transient volatile Collection<V> values;
	
	public abstract Set<Entry<K,V>> entrySet();
	
	public void clear()
	{
		entrySet().clear();
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		AbstractMap<K,V> result = (AbstractMap<K,V>)super.clone();
		keySet = null;
		values = null;
		return result;
	}
	
	public boolean containsKey(Object key) 
	{
		Iterator<Entry<K,V>> iter = entrySet().iterator();
		if (key == null) {
			while (iter.hasNext()) {
				Entry<K,V> entry = iter.next();
				if (entry.getKey() == null)
					return true;
			}
		} else {
			while (iter.hasNext()) {
				Entry<K,V> entry = iter.next();
				if (entry.getKey().equals(key))
					return true;
			}
		}
		return false;
	}
	
	public boolean containsValue(Object value) 
	{
		Iterator<Entry<K,V>> iter = entrySet().iterator();
		if (value == null) {
			while (iter.hasNext()) {
				Entry<K,V> entry = iter.next();
				if (entry.getValue() == null)
					return true;
			}
		} else {
			Entry<K,V> entry = iter.next();
			if (entry.getValue().equals(value))
				return true;
		}
		return false;
	}
	
	private static boolean eq(Object o1, Object o2) 
	{
		return (o1 == null ? o2 == null : o1.equals(o2));
	}
	
	public boolean equals(Object o) 
	{
		if (this == o)
			return true;
		if (!(o instanceof Map))
			return false;
		Map<K,V> m = (Map<K,V>)o;
		if (size() != m.size())
			return false;
		try {
			Iterator<Entry<K,V>> iter = entrySet().iterator();
			while (iter.hasNext()) {
				Entry<K,V> entry = iter.next();
				K key = entry.getKey();
				V value = entry.getValue();
				if (value == null) {
					if (!(m.get(key) == null && m.containsKey(key)))
						return false;
				} else {
					if (!value.equals(m.get(key)))
						return false;
				}
			}
		} catch (ClassCastException e) {
			
		}
		return true;
	}
	
	public V get(Object key)
	{
		Iterator<Entry<K,V>> iter = entrySet().iterator();
		if (key == null) {
			while (iter.hasNext()) {
				Entry<K,V> entry = iter.next();
				if (entry.getKey()==null)
					return entry.getValue();
			}
		} else {
			Entry<K,V> entry = iter.next();
			while (iter.hasNext()) {
				if (key.equals(entry.getKey()))
					return entry.getValue();
			}
		}
		return null;
	}
	
	public int hashCode()
	{
		int h = 0;
		Iterator<Entry<K,V>> iter = entrySet().iterator();
		while (iter.hasNext()) {
			Entry<K,V> entry = iter.next();
			h += entry.hashCode();
		}
		return h;
	}
	
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public Set<K> keySet() {
		if (keySet == null) {
			keySet = new AbstractSet<K> () {
				public Iterator<K> iterator() {
					return new Iterator<K>() {
						private Iterator<Entry<K,V>> iter = entrySet().iterator();
						public boolean hasNext() {
							return iter.hasNext();
						}
						
						public K next() {
							return iter.next().getKey();
						}
						
						public void remove() {
							iter.remove();
						}
					};
				}
				
				public int size() {
					return AbstractMap.this.size();
				}
			};
		}
		return keySet;
	}
	
	public V put(K key, V value) 
	{
		throw new UnsupportedOperationException();
	}
	public void putAll(Map<? extends K, ? extends V> map) {
		for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}
	
	public V remove(Object key) 
	{
		Iterator<Entry<K,V>> iter = entrySet().iterator();
		Entry<K,V> foundEntry = null;
		if (key == null) {
			while (iter.hasNext() && foundEntry==null) {
				Entry<K,V> entry = iter.next();
				if (entry.getKey()==null)
					foundEntry = entry;
			}
		} else {
			while (iter.hasNext() && foundEntry==null) {
				Entry<K,V> entry = iter.next();
				if (key.equals(entry.getKey())) 
					foundEntry = entry;
			}
		}
		V oldVal = null;
		if (foundEntry != null) {
			oldVal = foundEntry.getValue();
			iter.remove();
		}
		return oldVal;
	}
	
	public int size() {
		return entrySet().size();
	}
	
	public String toString()
	{
		Iterator<Entry<K,V>> iter = entrySet().iterator();
		if (!iter.hasNext())
			return "{}";
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (; ;   ) {
			Entry<K,V> entry = iter.next();
			K key = entry.getKey();
			V v = entry.getValue();
			sb.append(key == this ? "(this map)" : key);
			sb.append("=");
			sb.append(v == this ? "(this map)" : v);
			if (!iter.hasNext())
				return sb.append('}').toString();
			sb.append(", ");
		}
	}
	
	public Collection<V> values() {
		if (values == null) {
			values = new AbstractCollection<V> () {
				public Iterator<V> iterator() {
					return new Iterator<V>() {
						private Iterator<Entry<K,V>> iter = entrySet().iterator();
						
						public boolean hasNext() {
							return iter.hasNext();
						}
						
						public V next() {
							return iter.next().getValue();
						}
						
						public void remove() {
							iter.remove();
						}
					};
				}
				
				public int size() {
					return AbstractMap.this.size();
				}
				
				public boolean isEmpty() {
					return AbstractMap.this.isEmpty();
				}
				
				public void clear() {
					AbstractMap.this.clear();
				}
				
				public boolean contains(Object v) {
					return AbstractMap.this.containsKey(v);
				}
				
			};
		}
		return values;
	}
	
	public class SimpleEntry<K,V> implements Entry<K,V> {
		
		private final K key;
		private V value;
		public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
			this.key = entry.getKey();
			this.value = entry.getValue();
		}
		
		public SimpleEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public V setValue(V value)
		{
			V oldVal = this.value;
			this.value = value;
			return oldVal;
		}
		
		public boolean equals(Object o) {
			if (this == o) 
				return true;
			if (!(o instanceof Entry))
				return false;
			Map.Entry<?, ?> entry = (Map.Entry<?,?>)o;
			return (eq(getKey(),entry.getKey()) && (eq(getValue(), entry.getValue())));
		}
		
		public int hashCode()
		{
			return (key == null ? 0 : key.hashCode())^(value == null ? 0 : value.hashCode());
		}
	}
}
