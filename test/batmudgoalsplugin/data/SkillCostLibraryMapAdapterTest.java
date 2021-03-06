package batmudgoalsplugin.data;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SkillCostLibraryMapAdapterTest {

	@Test
	public void testAdaptSkillCosts() throws Exception {
		SkillCostLibraryMapAdapter adapter = new SkillCostLibraryMapAdapter();
		Map<String, Map<Integer, Integer>> map = new HashMap<String, Map<Integer, Integer>>();
		map.put("attack", new HashMap<Integer, Integer>());
		for (int i = 0; i < 10; i++) {
			map.get("attack").put(i, i);
		}
		AdaptedSkillCostList adapted = adapter.marshal(map);
		adapted.list.contains(new AdaptedSkillCostEntry("attack",
				1, 1));

	}

}
