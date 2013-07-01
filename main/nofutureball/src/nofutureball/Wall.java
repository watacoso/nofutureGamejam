package nofutureball;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Wall extends Entity {

	Graphics g;
	private int type;
	private final int padding = 15;
	private final float height = 80;
	private float length;

	public Wall(float x, float y, int type, float lenght) { // 1:horizzontalSection
															// 2:vertical
															// (3:horizzontalFull)
		super(x, y);
		this.type = type;
		this.length = lenght;

		switch (type) {
			case 1:
				size.x = length;
				size.y = padding;
				break;
			case 2:
				size.y = length;
				size.x = padding;
				break;
		}

		g = new Graphics();

	}

	public void render(Camera camera) {
		
		Vector2f screenPos = getScreenPos(camera);
		// _ means scaled, ok?
		float _padding = padding * camera.getZoom();
		float _height = height * camera.getZoom();
		float _length = length * camera.getZoom();
		
		switch (type) {
			case 1:
				g.setColor(g.getBackground());
				g.fillRect((int) screenPos.x, (int) (screenPos.y - _height), _length, _padding);
				g.setColor(Color.decode("#45495F"));
				g.fillRect((int) screenPos.x, (int) (screenPos.y - _height), _length, _height);
				g.setColor(Color.decode("#9AA0B6"));
				g.fillRect((int) (screenPos.x), (int) (screenPos.y - _height), _length, _padding);
	
				break;
			case 2:
				g.setColor(Color.decode("#45495F"));
				g.fillRect((int) (screenPos.x), (int) (screenPos.y - _height), _padding, _length + _padding + _height);
				g.setColor(Color.decode("#9AA0B6"));
				g.fillRect((int) (screenPos.x), (int) (screenPos.y - _height), _padding, _length + _padding * 2);
				break;
		}
	}

}
