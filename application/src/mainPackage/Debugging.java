package mainPackage;

import org.newdawn.slick.Input;

/**
 * Room for Debugging Options
 * @author watacoso
 *
 */

public class Debugging {

	private static Boolean busy = false;

	public static void update() {

		Input input = Window.getGameContainer().getInput();

		// 0 = Show Stats
		if (input.isKeyDown(Input.KEY_0) && busy == false) {
			showPlayerStats();
			busy = true;
		} else if (input.isKeyDown(Input.KEY_9) && busy == false) {
			// Player.STATS=(LinkedTreeMap) JsonManager.loadJson("player.json");
			busy = true;
		} else if (!input.isKeyDown(Input.KEY_9)
				&& !input.isKeyDown(Input.KEY_0))
			busy = false;

	}

	private static void showPlayerStats() {

		// System.out.println(Player.STATS);
	}

}
