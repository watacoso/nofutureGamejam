package entityPackage;

import mainPackage.Game;
import mainPackage.StatSet;
import mapElements.Wall;

import org.newdawn.slick.geom.Vector2f;

import playerPackage.Player;

/**
 * Bullet Object
 * @author hollowspecter
 *
 */

public class Bullet extends GameObject {
	
	//public Vector2f speed;
	public float life;
	public float size;
	public GameObject src;
	public boolean damageNPC=false;
	public boolean damagePlayer=false;
	
	/**
	 * Creates a new Bullet. Is used for any kind of ranged weapons
	 * @param src Source of the bullet
	 * @param size Size of the Bullet
	 * @param lastDirection Direction of the bullet
	 * @param speedModule Speed of the Bullet
	 */
	public Bullet(GameObject src, float size, Vector2f lastDirection,float speedModule) {
		super(src.room,src.spriteBox.getPosition().x -src.room.position.x, src.spriteBox.getPosition().y-src.room.position.y-100, size, size,false);
		//spritePivot.set(size/2,size/2);
		setAnimation("BULLETS","STANDARD");
		spriteBox.setSize(size, size);
		spriteBox.setPivot(size/2, size/2);
		//spritePivot.set(size/2,size/2);
		//spriteSize.set(size,size);
		collisionBox.setPivot(size/2, -100);
		speed=new Vector2f(lastDirection).scale(speedModule);	
		base = new StatSet(src);
		health = (int) (src.range()/speedModule);
		this.src = src;
		if(src instanceof Player)
			damageNPC=true;
		else if(src instanceof NPC)
			damagePlayer=true;
		else{
			damagePlayer=damageNPC=true;
		}
	}
	
	public void update (Game game){
		
		if(health<0) die();
		else
			health--;
		//System.out.println(life);
		
		super.update(game);		
	}
	
	protected void handleWallCollision(Wall wall,String direction){
		die();
	}
	
	protected void handleObjectCollision(GameObject object,String direction){
		/*if(object instanceof Player)
		switch (direction){
		case "left":
			speed.x=Math.abs(speed.x)/2;
			break;
		case "right":
			speed.x=-Math.abs(speed.x)/2;
			break;
		case "top":
			speed.y=Math.abs(speed.y)/2;
			break;
		case "bottom":
			speed.y=-Math.abs(speed.y)/2;
			break;
		}*/
		/*if(object!=src)
			die();*/
	}
}
