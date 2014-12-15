package com.applenick.Fullbright.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.applenick.Fullbright.Fullbright;
import com.applenick.Fullbright.SaveManager;
import com.applenick.Fullbright.utils.PotionUtil;

public class FullbrightListener implements Listener {


	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		if(Fullbright.get().getManager().hasPlayer(event.getPlayer())){
			if(Fullbright.get().getManager().isActive(event.getPlayer())){
				Fullbright.get().getManager().reApplyEffect(event.getPlayer());
				return;
			}
		}else{
			if(SaveManager.hasSetting(event.getPlayer().getUniqueId().toString())){
				Fullbright.get().getManager().setPlayer(event.getPlayer(), SaveManager.getSavedSetting(event.getPlayer().getUniqueId().toString()));
				Fullbright.get().getManager().reApplyEffect(event.getPlayer());
				return;
			}else{
				Fullbright.get().getManager().addPlayer(event.getPlayer());
				SaveManager.addPlayer(event.getPlayer().getUniqueId());
				return;
			}
		}		
	}

	@EventHandler
	public void onConsume(final PlayerItemConsumeEvent event){
		if(event.getItem().equals(new ItemStack(Material.MILK_BUCKET))){
			if(Fullbright.get().getManager().isActive(event.getPlayer())){
				new BukkitRunnable(){
					@Override public void run(){
						PotionUtil.applyNightVison(event.getPlayer());
					}
				}.runTaskLater(Fullbright.get(), 2L);			
			}
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event){
		if(Fullbright.get().getManager().isActive(event.getPlayer())){
			PotionUtil.applyNightVison(event.getPlayer());
		}else{
			return;
		}
	}

}
