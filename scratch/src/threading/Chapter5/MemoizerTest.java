package threading.Chapter5;

import generics.Maps.HashMap;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MemoizerTest {
	
	public interface Computable<A,V> {
		public V compute(A arg);
	}
	
	public class ExpensiveFunction implements Computable<String, BigInteger> {
		public BigInteger compute(String str) {
			return new BigInteger(str);
		}
	}
	
	public class Memoizer1<A,V> implements Computable<A,V> {

		private final Computable<A,V> c;
		private final HashMap<A, V> cache = new HashMap<A, V>();
		
		public Memoizer1(Computable<A,V> c) {
			this.c = c;
			 
		}
		
		public synchronized V compute(A arg) {
			V result = cache.get(arg);
			if (result != null)
				return result;
			result =  c.compute(arg);
			cache.put(arg, result);
			return result;
		}
	}
	
	public class Memoizer2<A,V> implements Computable<A,V> {
		
		private final Computable<A,V> c;
		private final Map<A,V> cacheMap;
		public Memoizer2(Computable<A,V> computable) {
			c = computable;
			cacheMap = new ConcurrentHashMap<A,V>();
		}

		@Override
		public V compute(A arg) {
			V result = cacheMap.get(arg);
			if (result != null)
				return result;
			result = c.compute(arg);
			cacheMap.put(arg, result);
			return result;
		}
	}
	
	public class Memoizer3<A,V> implements Computable<A,V> {
		private final Computable<A,V> compute;
		private final ConcurrentHashMap<A,Future<V>> cacheMap = new ConcurrentHashMap<A,Future<V>>();
		public Memoizer3(Computable<A,V> compute) {
			this.compute = compute;
		}
		
		public V compute(final A arg) {
			while (true) {
				Future<V> f = cacheMap.get(arg);
				if (f == null) {
					Callable<V> eval = new Callable<V>() {
						public V call() throws InterruptedException {
							return compute.compute(arg);
						}
					};
					FutureTask<V> ft = new FutureTask<V>(eval);
					f = cacheMap.putIfAbsent(arg, ft);
					if (f == null) { f = ft; ft.run(); }
				}
				try {
					return f.get();
				} catch (CancellationException e) {
					cacheMap.remove(arg, f);
				} catch (ExecutionException e) {

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 
		}
	}
}

