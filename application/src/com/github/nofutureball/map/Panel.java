package com.github.nofutureball.map;

import org.newdawn.slick.GameContainer;

import com.github.nofutureball.control.LevelManager;
import com.github.nofutureball.entity.GameObject;
import com.github.nofutureball.entity.player.Player;


/**
 * Panel class
 * The screens on the wall in each room
 * @author hollowspecter
 *
 */
public class Panel extends GameObject {

	public Panel(Room room, float x,String type) {
		super(room, x, -40, 48, 70, false);
		setCollision(false);
		setAnimation("PANEL","TEMP");
	}
	
	public void update(GameContainer game){
		super.update(game);
		
		for(int i=0;i<LevelManager.players.size();i++){
			Player p=(Player) LevelManager.players.get(i);
			if(getDistance(p)<30 && p.room==room){
				System.out.println("TEST");
			}
		}
	}
}
