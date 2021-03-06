package oppression;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Window {

	private static GameContainer gameContainer;

	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;
	//public final static int WIDTH = 1366;
	//public final static int HEIGHT = 768;
	public final static int FPS = 60;

	public static void main(String[] args) {
		


		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Game("Simple Slick Game"));
			
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(FPS);
			// appgc.setMinimumLogicUpdateInterval(16);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Window.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

	public static GameContainer getGameContainer() {
		return gameContainer;
	}

	public static void setGameContainer(GameContainer gc) {
		gameContainer = gc;
	}
}