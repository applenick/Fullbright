package com.applenick.Fullbright.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.applenick.Fullbright.Fullbright;
import com.applenick.Fullbright.SaveManager;
import com.applenick.Fullbright.utils.AppleUtils;
import com.applenick.Fullbright.utils.PotionUtil;
import com.sk89q.minecraft.util.commands.ChatColor;

public class FullbrightListener implements Listener {


	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		String uuid = p.getUniqueId().toString();
		if(!(Fullbright.get().getConfig().contains(uuid))){
			Fullbright.get().getConfig().addDefault(uuid, false);
			Fullbright.get().saveConfig();
			AppleUtils.console(ChatColor.GREEN + p.getName() + "has beed added to the Fullbright SaveList");
		}
		else if(SaveManager.isActivated(p)){
			PotionUtil.applyNightVison(p);
		}
		else{
			return;
		}
	}

	@EventHandler
	public void onConsume(PlayerItemConsumeEvent event){
		final Player p = event.getPlayer();
		boolean active = SaveManager.isActivated(p);
		if(event.getItem().equals(new ItemStack(Material.MILK_BUCKET))){
			if(active){
				new BukkitRunnable(){
					@Override public void run(){
						PotionUtil.applyNightVison(p);
					}
				}.runTaskLater(Fullbright.get(), 2L);			
			}
		}
	}

	@EventHandler
	public void onDeath(PlayerRespawnEvent event){			
		final Player p = event.getPlayer();
		boolean active = SaveManager.isActivated(p);

		if(active){
			new BukkitRunnable(){
				@Override public void run(){
					PotionUtil.applyNightVison(p);
				}
			}.runTaskLater(Fullbright.get(), 4L);
		}
	}

}
