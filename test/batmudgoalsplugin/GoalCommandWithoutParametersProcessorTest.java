package batmudgoalsplugin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import batmudgoalsplugin.data.BatMUDGoalsPluginData;

import com.mythicscape.batclient.interfaces.BatClientPlugin;
import com.mythicscape.batclient.interfaces.ClientGUI;

public class GoalCommandWithoutParametersProcessorTest {

	private ClientGUI verifyGoalOutput(BatMUDGoalsPluginData data) {
		ClientGUI mock = mock(ClientGUI.class);
		BatClientPlugin plugin = mock(BatClientPlugin.class);
		when(plugin.getClientGUI()).thenReturn(mock);
		new GoalCommandWithoutParametersProcessor(plugin, data).receive("goal");

		final ClientGUI verify = verify(mock);
		return verify;
	}

	public void testGoalCommandListsAvailableGoalSkills_noCommands() {
		verifyGoalOutput(new BatMUDGoalsPluginData()).printText("generic",
				"No data.\n");
	}

	@Test
	public void testGoalCommandListsAvailableGoalSkills() throws Exception {
		BatMUDGoalsPluginData data = new BatMUDGoalsPluginData();
		data.setSkillCost("attack", 1, 1);
		verifyGoalOutput(data).printText("generic", String.format("attack%n"));
	}

	@Test
	public void testGoalCommandListsMultipleAvailableGoalSkills()
			throws Exception {
		BatMUDGoalsPluginData data = new BatMUDGoalsPluginData();
		data.setSkillCost("attack", 1, 1);
		data.setSkillCost("brawling", 1, 1);
		verifyGoalOutput(data).printText("generic", String.format("attack%n"));
		verifyGoalOutput(data)
				.printText("generic", String.format("brawling%n"));
	}

	@Test
	public void testGoalCommandListsMultipleAvailableGoalSkillsAndMarksGoal()
			throws Exception {
		BatMUDGoalsPluginData data = new BatMUDGoalsPluginData();
		data.setSkillCost("attack", 1, 1);
		data.setSkillCost("brawling", 1, 1);
		data.setGoalSkill("brawling");
		verifyGoalOutput(data).printText("generic", String.format("attack%n"));
		verifyGoalOutput(data).printText("generic",
				String.format("brawling (*)%n"));
	}
}
