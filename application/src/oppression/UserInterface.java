package oppression;



import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

public class UserInterface {

	//public static void init(Game game){
	//	game.ui.add(new PlayerProfile((Player) LevelManager.players.get(0),10,10));
	//	if(LevelManager.players.size()>=2)
	//	game.ui.add(new PlayerProfile((Player) LevelManager.players.get(1),Window.WIDTH-260,10));
	//}
	
	public static void addProfile(int index){
		switch (index){
		case 0:
			Game.ui.add(new PlayerProfile((Player) LevelManager.players.get(0),10,10));
			break;
		case 1:
			Game.ui.add(new PlayerProfile((Player) LevelManager.players.get(1),Window.WIDTH-260,10));
			break;
		case 2:
			Game.ui.add(new PlayerProfile((Player) LevelManager.players.get(2),10,Window.HEIGHT-140));
			break;
		case 3:
			Game.ui.add(new PlayerProfile((Player) LevelManager.players.get(3),Window.WIDTH-260,Window.HEIGHT-140));
			break;
		}
	}
	
	//public void update(Game game){
	//	
	//}
	
}


class PlayerProfile extends Entity{
	private Graphics g;
	private Player p;
	private TrueTypeFont font = new TrueTypeFont(new java.awt.Font("Verdana", Font.BOLD, 20), false);
	
	public PlayerProfile(Player p,int x,int y){
		super(x,y,250,80,0,0);
		this.p=p;
		g=new Graphics();
		
	}
	
	
	public void render(Camera cam){
		
		
		if(p.dead){
			g.setColor(Color.decode("#1A1E20"));
			g.fillRect( position.x,position.y,box.getSize().x,box.getSize().y);
			g.setColor(Color.decode("#0B0C0F"));
			g.fillRect( position.x+10,position.y+10,230,30);
			g.fillRect( position.x+10,position.y+50,230,20);
			
			if(Game.status!=Game.Status.GAMEOVER){
				Integer s=(p.cooldown*1000-LevelManager.getTime()+p.deathTime)/1000;
				font.drawString( position.x+115,position.y+30, s.toString(), Color.white);
			}
			
		}
		else{
			g.setColor(Color.decode("#3A3E50"));
			g.fillRect( position.x,position.y,box.getSize().x,box.getSize().y);
			g.setColor(Color.decode("#0B0C0F"));
			g.fillRect( position.x+10,position.y+10,230,30);
			g.fillRect( position.x+10,position.y+50,230,20);
			g.setColor(Color.decode("#32763D"));
			g.fillRect( position.x+10,position.y+10,(p.health/p.maxHealth())*230,30);
			g.setColor(Color.decode("#C8782D"));
			g.fillRect( position.x+10,position.y+50,230,20);
		}
	}	
}



