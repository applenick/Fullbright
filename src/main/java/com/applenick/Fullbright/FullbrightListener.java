package com.applenick.Fullbright;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.applenick.Fullbright.utils.Alog;
import com.applenick.Fullbright.utils.PotionUtil;
import com.sk89q.minecraft.util.commands.ChatColor;

public class FullbrightListener implements Listener {
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent joinEvent){
		Player p = joinEvent.getPlayer();
		String uuid = p.getUniqueId().toString();
		if(!(p.hasPlayedBefore())){
			Fullbright.get().getConfig().addDefault(uuid, false);
			Alog.console(ChatColor.GREEN + p.getName() + "has beedn added to the Fullbright SaveList");
		}
		else if(FullbrightSaveManager.isActivated(p)){
			PotionUtil.applyNightVison(p);
		}
		
		else{
			return;
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent deathEvent){
		if(deathEvent.getEntityType() == EntityType.PLAYER){
			Player p = deathEvent.getEntity();
			
			if(FullbrightSaveManager.isActivated(p)){
				PotionUtil.applyNightVison(p);
			}
		}
		
	}
	
}
