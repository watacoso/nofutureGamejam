package nofutureball;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity implements Comparable<Entity> {

	public Vector2f position;
	public Vector2f pivot;
	public Vector2f size;

	public Container parent = null;
	private Graphics g = new Graphics();

	public Entity(float x, float y) {
		position = new Vector2f(x, y);
		size = new Vector2f(0, 0);
		pivot = new Vector2f(0, 0);
	}

	public Entity(float x, float y, float width, float height) {
		position = new Vector2f(x, y);
		size = new Vector2f(width, height);
		pivot = new Vector2f(0, 0);
	}

	public Entity(float x, float y, float width, float height, float pivotX, float pivotY) {
		position = new Vector2f(x, y);
		size = new Vector2f(width, height);
		pivot = new Vector2f(pivotX, pivotY);
	}

	// update()
	
	public void update() {

	}

	public void update(Vector2f offset) {

	}
	
	public void render(Camera cam) {
		Vector2f screenPos = getScreenPos(cam);
		g.drawRect(screenPos.x, screenPos.y, getScaledWidth(cam), getScaledHeight(cam));
	}
	/*public void render(Vector2f screenPosThatsAlreadyBeenDerivedFromCam, float zoom)
	{
		g.drawRect(screenPosThatsAlreadyBeenDerivedFromCam.x, screenPosThatsAlreadyBeenDerivedFromCam.y, getScaledWidth(zoom), getScaledHeight(zoom));
	}*/
	

	public int compareTo(Entity o) {
		if (o.position.y - 10 < this.position.y)
			return 1;
		if (o.position.y - 10 > this.position.y)
			return -1;
		return -1;
	}
	
	// I don't think I'll need these, unless we make containers with non-null position (not (0, 0))
	/*public Vector2f getAbsolutePos()
	{
		if (parent != null) {
			return parent.getAbsolutePos().add(position);
		} else {
			return position;
		}
	}
	public Vector2f getPosRelativeTo(Entity p)
	{
		if (this == p) {
			return position;
		} else if (parent != null && this != p) {
			return parent.getPosRelativeTo(p).add(position);
		} else {
			System.out.println("Entity.getPosRelativeTo: parent specified is not one of my parents");
			return new Vector2f();
		}
	}*/

	// Feel free to make getScreenPos(Vector2f offset), if you need that. (just replace camera.position with offset)
	/**
	 * Call this whenever you render to get the actual position on screen to render to!
	 */
	protected Vector2f getScreenPos(Camera camera)
	{
		return new Vector2f(NoFutureBall.WIDTH / 2 + (position.x - camera.position.x) * camera.getZoom(),
							NoFutureBall.HEIGHT / 2 + (position.y - camera.position.y) * camera.getZoom());
	}
	protected float getScaledWidth(Camera camera)
	{
		return size.x * camera.getZoom();
	}
	protected float getScaledHeight(Camera camera)
	{
		return size.y * camera.getZoom();
	}
	
	/*protected float getScaledWidth(float zoom)
	{
		return size.x * zoom;
	}
	protected float getScaledHeight(float zoom)
	{
		return size.y * zoom;
	}*/
}
