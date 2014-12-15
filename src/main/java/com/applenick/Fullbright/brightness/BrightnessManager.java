package com.applenick.Fullbright.brightness;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.applenick.Fullbright.Fullbright;
import com.applenick.Fullbright.SaveManager;
import com.applenick.Fullbright.utils.PotionUtil;
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

	public boolean hasPlayer(Player player){
		if(players.containsKey(player.getUniqueId())){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isActive(Player player) {
		if(players.get(player.getUniqueId()).booleanValue()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void toggleBrightness(Player player){
		if(isActive(player)){
			setPlayer(player, false);
			PotionUtil.removeNightVision(player);
			player.sendMessage(Fullbright.prefix + ChatColor.DARK_AQUA + "Brightness has been" + ChatColor.RED + " disabled");
			player.playSound(player.getLocation(), Sound.BLAZE_DEATH, 10, 2);
			SaveManager.updatePlayer(player.getUniqueId(), false);
			return;
		}else{
			setPlayer(player, true);
			PotionUtil.applyNightVison(player);
			player.sendMessage(Fullbright.prefix + ChatColor.DARK_AQUA + "Brightness has been" + ChatColor.GREEN + " activated");
			player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 2);
			SaveManager.updatePlayer(player.getUniqueId(), true);
			return;
		}
	}

	public void reApplyEffect(Player player) {
		if(isActive(player)){
			PotionUtil.applyNightVison(player);
		}
	}
}


