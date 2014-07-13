package com.applenick.Fullbright;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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
			Fullbright.get().saveConfig();
			Alog.console(ChatColor.GREEN + p.getName() + "has beed added to the Fullbright SaveList");
		}
		else if(FullbrightSaveManager.isActivated(p)){
			PotionUtil.applyNightVison(p);
		}
		
		else{
			return;
		}
	}
	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent eat){
		Player p = eat.getPlayer();
		boolean active = FullbrightSaveManager.isActivated(p);
		if(eat.getItem().equals(new ItemStack(Material.MILK_BUCKET))){
			if(active){
				PotionUtil.applyNightVison(p);
			}
			
		}
	}
	
	@EventHandler
	public void onDeath(PlayerRespawnEvent respawnEvent){			
			final Player p = respawnEvent.getPlayer();
			boolean active = FullbrightSaveManager.isActivated(p);

			if(active){
				new BukkitRunnable(){
					@Override public void run(){
						PotionUtil.applyNightVison(p);
					}
				}.runTaskLater(Fullbright.get(), 4L);
				
			}
			
		
	}
	
}
