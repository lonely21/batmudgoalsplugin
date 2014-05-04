package batmudgoalsplugin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import batmudgoalsplugin.data.BatMUDGoalsPluginData;

public class TrainCommandOutputProcessorTest {

	@Test
	public void testTrainCommandOutput() {
		BatMUDGoalsPluginData data = new BatMUDGoalsPluginData();
		TrainCommandOutputProcessor op = new TrainCommandOutputProcessor(data);
		op.receive("| Attack                        |   0 |  85 | 10  |       22015 |");
		op.receive("| Brawling 						|  15 |  85 | 10  |       (n/a) |");
		assertEquals(0, data.getCurrentSkillStatus("attack"));
		assertEquals(15, data.getCurrentSkillStatus("brawling"));
	}

	@Test
	public void testStudyCommandOutput() throws Exception {
		BatMUDGoalsPluginData data = new BatMUDGoalsPluginData();
		TrainCommandOutputProcessor op = new TrainCommandOutputProcessor(data);

		op.receive(",-------------------------------+-----------------+-------------|");
		op.receive("| Spells available at level 35  | Cur | Rac | Max | Exp         |");
		op.receive("|===============================|=====|=====|=====|=============|");
		op.receive("| Cure light wounds             |  85 | 101 |  95 |       16940 |");
		op.receive("| Detect alignment              |  50 | 101 | 100 |        2444 |");
		op.receive("| Paranoia                      |   1 | 101 | 100 |          25 |");

		assertEquals(85, data.getCurrentSkillStatus("cure light wounds"));
		assertEquals(50, data.getCurrentSkillStatus("detect alignment"));
		assertEquals(1, data.getCurrentSkillStatus("paranoia"));
	}

}
