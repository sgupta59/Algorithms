package generics.Maps;

public class HeapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String,String>();
		map.put(null, "null");
		map.put("FB", "abc");
		map.put("Ea", "efg");
		map.put("efg", "efg1");
		for (int i = 2; i < 12; ++i)
			map.put(Integer.toString(i), Integer.toString(i));
		map.entrySet();
	}

}
