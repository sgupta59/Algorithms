package generics.Maps;

import java.util.Iterator;
import java.util.Set;

public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String,String>();
		//map.put(null, "null");
		map.put("FB", "abc");
		map.put("Ea", "efg");
		map.remove("Ea");
		Set<Map.Entry<String,String>> entryset = map.entrySet();
		Iterator<Map.Entry<String,String>> iter = entryset.iterator();
		Map.Entry<String, String> entry1 = iter.next();
		Map.Entry<String, String> entry2 = iter.next();
		entry1.equals(entry2);
		map.put("efg", "efg1");
		for (int i = 2; i < 12; ++i)
			map.put(Integer.toString(i), Integer.toString(i));
		map.entrySet();
	}

}
