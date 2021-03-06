package oppression;

import java.util.ArrayList;

public class LevelManager {

	public LevelGen levelGen;
	
	private  int level;
	private Container entities;
	public static ArrayList<Player> players;
	private int nPlayers=2;
	public static int nEnemies;
	private int spawnNumber=0;
	private int max_enemies=300;
	private static int timer;
	private int spawnTimestamp;
	public static Camera cam;
	
	public LevelManager(){
		this.entities=Game.entities;
		players=new ArrayList<Player>();
		levelGen=new LevelGen(Game.entities,Game.mapContainer);
		cam=new Camera(Game.gameContainer);
		timer=0;
		nEnemies=0;
		spawnTimestamp=0;
		
	}
	
	public static int getTime(){
		return timer;
	}
	
	public void setNumPlayers(int nPlayers){
		players.clear();
		if(nPlayers>=1){
			Player p = new Sharpshooter(levelGen.getStartRoom(),300, 300, KeySet.ONE);
			players.add(p);
			UserInterface.addProfile(0);
		}
		if(nPlayers>=2){
			Player p = new Sharpshooter(levelGen.getStartRoom(),300, 600, KeySet.TWO);
			players.add(p);
			UserInterface.addProfile(1);
		}
		//if(nPlayers>=3){
		//	Player p = new Sharpshooter(levelGen.getRandomRoom(),600, 300, KeySet.THREE);
		//	players.add(p);
		//UserInterface.addProfile(2);
		//}
		//if(nPlayers==4){
		//	Player p = new Sharpshooter(levelGen.getRandomRoom(),600, 600, KeySet.FOUR);
		//	players.add(p);
		//UserInterface.addProfile(3);
		//}
		
		this.nPlayers=nPlayers;
	}
	
	public void newGame(){
		
	}
	
	public void nextLevel(){
		
	}
	
	public void initLevel(){
		levelGen.generateMap(level);
		setNumPlayers(nPlayers);
		for (int i=0;i<players.size();i++) {
			entities.add(players.get(i));
			cam.addTarget(players.get(i));
		}
		
	}
	
	public void pause(boolean value){
		
	}
	
	public static void gameOver(){
		Game.status=Game.Status.GAMEOVER;
	}

	private void spawnPlayer(Player p,Room r, int x, int y){
		entities.remove(p);
		p.room=r;
		p.position.set(r.position.x+x,r.position.y+y);
		p.dead=false;
		p.health=p.maxHealth();
		entities.add(p);
		cam.addTarget(p);
	}
	
	public void update(int delta){
		timer+=delta;
		if(getTime()-spawnTimestamp>5000){
			spawnTimestamp=getTime();
			spawnNumber+=5;
			for(int i=0;i<1+Math.ceil(spawnNumber/10);i++){
				//System.out.println(nEnemies);
				if(nEnemies<max_enemies){
					RoboRifler e = new RoboRifler(levelGen.getRandomRoom(), 256, 256);
					entities.add(e);
				}
			}
			
		}
		
		for(int i=0;i<players.size();i++){
			if(players.get(i).dead){
				if(getTime()>players.get(i).deathTime+players.get(i).cooldown*1000){
					for(int j=0;j<players.size();j++)
						if(!players.get(j).dead)
							spawnPlayer(players.get(i),players.get(j).room,200,200);
				}
			}
		}
		
		cam.update();
	}
	
}
