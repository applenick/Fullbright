package com.applenick.Fullbright.brightness;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

/************************************************
			Created By AppleNick
Copyright © 2014 , AppleNick, All rights reserved.
			http://applenick.com
 *************************************************/

public class BrightnessManager {

	private HashMap<UUID,Boolean> players;
	
	public BrightnessManager(){
		this.players = Maps.newHashMap();
	}
	
	public HashMap<UUID,Boolean> getPlayers(){
		return players;
	}
	
	public void addPlayer(Player player){
		this.players.put(player.getUniqueId(), false);
	}
	
	public void setPlayer(Player player, boolean setting){
		this.players.put(player.getUniqueId(), setting);
	}
}


