package language;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class HashMapTest {
	
	//²âÊÔ´æ´¢Ë³Ğò
	@Test
	public void testShunxu() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("1", "a");
		map.put("2", "c");
		map.put("3", "b");
		map.put("4", "d");
		map.put("beijin", "ÇçÌì");
		map.put("ceijin", "ÇçÌì");
		
//		Set entrySet = map.entrySet();
		for (Entry<String, Object> entry : map.entrySet()) {
			
			System.out.println("key="+ entry.getKey() + "   value="+entry.getValue());
			
		}
		
		
	}

}
