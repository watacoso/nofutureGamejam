package nofutureball;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Sprite extends Entity{
	
	public Sprite(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Sprite(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Sprite(float x, float y, float width, float height,
			float pivotX, float pivotY) {
		super(x, y, width, height, pivotX, pivotY);
		// TODO Auto-generated constructor stub
	}

	private static HashMap<String, HashMap<String,Animation>> animationList;
	private static HashMap<String, HashMap<String,Image>> spriteList;
	//private static HashMap<String, SpriteSheet> spriteSheetList;
	private static SpriteSheet ss;
	private Animation currentAnimation;
	private Image currentImage;
	private boolean usingAnimation=false;
	public Vector2f spriteRelativePosition=new Vector2f(0,0);
	
	public static void init(){
		
		//GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		//GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		//GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		
		animationList=new HashMap<String, HashMap<String,Animation>>();
		spriteList=new HashMap<String, HashMap<String,Image>>();
		
		//PLAYER TMP//
		
		setSpriteSheet("assets/sprites/Player.png",128,256);
		addAnimation("PLAYER","IDLE_LEFT",buildAnimation(0,4,100),false);
		addAnimation("PLAYER","IDLE_RIGHT",buildAnimation(0,4,100),true);
		addAnimation("PLAYER","WALKING_LEFT",buildAnimation(4,4,100),false);
		addAnimation("PLAYER","WALKING_RIGHT",buildAnimation(4,4,100),true);
		
		///ENEMY TMP//
		
		setSpriteSheet("assets/sprites/Enemy.png",128,256);
		addAnimation("ENEMY","IDLE_LEFT",buildAnimation(0,4,100),false);
		addAnimation("ENEMY","IDLE_RIGHT",buildAnimation(0,4,100),true);
		addAnimation("ENEMY","WALKING_LEFT",buildAnimation(4,4,100),false);
		addAnimation("ENEMY","WALKING_RIGHT",buildAnimation(4,4,100),true);
		
		//BULLETS//
		
		setSpriteSheet("assets/sprites/BULLETS.png",20, 20);
		addAnimation("BULLETS","STANDARD",buildAnimation(0,3,100),false);
		
		//FLOOR//
		
		setSpriteSheet("assets/sprites/FloorTiles.png",256, 256);
		addSprite("FLOOR","R1",0,0,false);
		addSprite("FLOOR","R2",1,0,false);
		addSprite("FLOOR","R3",2,0,false);
		addSprite("FLOOR","R4",3,0,false);
		
		//WALL//
		

		
		//DOOR//
		setSpriteSheet("assets/sprites/doorV.png",64,712);
		//addSprite("DOORV","OPEN","assets/sprites/doorV.png",false);
		addSprite("DOORV","OPEN",0,0,false);
		addSprite("DOORV","CLOSE",3,0,false);
		
		setSpriteSheet("assets/sprites/doorH.png",512,264);
		addSprite("DOORH","OPEN",0,0,false);
		addSprite("DOORH","CLOSE",0,3,false);
		
		//addSprite("DOORH","OPEN","assets/sprites/doorH.png",false);
		
		addSprite("DOOR","H","assets/sprites/HDoor.png",false);
		
		addSprite("DOOR","V","assets/sprites/VDoor.png",false);
		//addSprite("FLOOR","TEST","assets/sprites/tileTest.png",false);
		
		

		
	}
	
	private static void setSpriteSheet(String ref,int tw,int th){
		try {
			ss=new SpriteSheet(ref,tw,th);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	private static Animation buildAnimation(int index,int length,int speed){
		int a,b,x,y;
		
		a=index/ss.getHorizontalCount();
		b=index%ss.getHorizontalCount();
		
		x=(index+length-1)%ss.getHorizontalCount();
		y=(index+length-1)/ss.getHorizontalCount();
		

		//System.out.println(a+" "+b+" : "+x+" "+y);
	
		return new Animation(ss,a,b,x,y,true,speed,true);
	}
	
	private static void addAnimation(String owner,String name,Animation anim,boolean flipped){
		
		Animation rev=new Animation();
		for(int i=0;i<anim.getFrameCount();i++){
			Image t=anim.getImage(i);
			t.bind();
			//t.setFilter(Image.FILTER_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			
			
			if(flipped){
				t=t.getFlippedCopy(true, false);
			}
			rev.addFrame(t, anim.getDuration(i));		
		}
		anim=rev;
		if(!animationList.containsKey(owner))
			animationList.put(owner, new HashMap<String,Animation>());
		
		animationList.get(owner).put(name, anim);
	}
	
	private static void addSprite(String owner,String name,String ref,boolean flipped){
		Image img=null;
		try {
			img = new Image(ref);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		img.bind();
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

		
		if(flipped)
			img=img.getFlippedCopy(true, false);
		
		if(!spriteList.containsKey(owner))
			spriteList.put(owner, new HashMap<String,Image>());
		
		spriteList.get(owner).put(name, img);
	}
	
	private static void addSprite(String owner,String name,int x,int y,boolean flipped){
		Image img=ss.getSprite(x, y);
		img.bind();
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		
		if(flipped)
			img=img.getFlippedCopy(true, false);
		
		if(!spriteList.containsKey(owner))
			spriteList.put(owner, new HashMap<String,Image>());
		
		spriteList.get(owner).put(name, img);
	}
	
	
	
	public void setAnimation(String owner,String name){
		if(currentAnimation!=animationList.get(owner).get(name)){
			currentAnimation=animationList.get(owner).get(name);
			usingAnimation=true;
		}
	}
	
	public void setImage(String owner, String name){
		if(currentImage!=spriteList.get(owner).get(name)){
			currentImage=spriteList.get(owner).get(name);
			usingAnimation=false;
		}
	}
	
	public static Image getSprite(String owner, String name){
			return spriteList.get(owner).get(name);
	}
	
	public float getSpriteScaledWidth(Camera cam){
		if(usingAnimation && currentAnimation!=null)
			return currentAnimation.getWidth()*cam.getZoom();
		if(currentImage!=null)
			return currentImage.getWidth()*cam.getZoom();
		return 0;
	}
	
	public float getSpriteScaledHeight(Camera cam){
		if(usingAnimation && currentAnimation!=null)
			return currentAnimation.getHeight()*cam.getZoom();
		if(currentImage!=null)
			return currentImage.getHeight()*cam.getZoom();
		return 0;
	}
	
	
	public  void render(Camera cam){
		if(currentImage!=null && !usingAnimation){
			Vector2f screenPos = getScreenPos(cam);
			currentImage.draw(screenPos.x+spriteRelativePosition.x*cam.getZoom(), screenPos.y+spriteRelativePosition.y*cam.getZoom(), getSpriteScaledWidth(cam), getSpriteScaledHeight(cam));
		}
		else
		if (currentAnimation != null) {
			Vector2f screenPos = getScreenPos(cam);
			currentAnimation.draw(screenPos.x+spriteRelativePosition.x*cam.getZoom(), screenPos.y+spriteRelativePosition.y*cam.getZoom(), getSpriteScaledWidth(cam), getSpriteScaledHeight(cam));
		}
		//super.render(cam);
	}
	
	
	
	

}
