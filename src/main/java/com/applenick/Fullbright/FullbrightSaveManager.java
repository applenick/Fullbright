package com.applenick.Fullbright;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.applenick.Fullbright.utils.PotionUtil;

public class FullbrightSaveManager {
	
	public static boolean isActivated(Player p){
		String uuid = p.getUniqueId().toString();
		if(Fullbright.get().getConfig().getBoolean(uuid)){
			return true;
		} else{
			return false;
		}
	}
	
	
	public static void saveSetting(Player p , boolean active){
		String uuid = p.getUniqueId().toString();
		Fullbright.get().getConfig().set(uuid, active);
		Fullbright.get().saveConfig();
	}
	
	
	public static void toggleCommand(Player p){
		boolean active = isActivated(p);
		if(active){
			PotionUtil.removeNightVision(p);
			p.sendMessage(Fullbright.prefix + ChatColor.DARK_AQUA + "Fullbright has been" + ChatColor.RED + " disabled");
			p.playSound(p.getLocation(), Sound.BLAZE_DEATH, 2, 2);
			saveSetting(p , false);
		}
		
		else{
			PotionUtil.applyNightVison(p);
			p.sendMessage(Fullbright.prefix + ChatColor.DARK_AQUA + "Fullbright has been" + ChatColor.GREEN + " activated");
			p.playSound(p.getLocation(), Sound.ITEM_BREAK, 2, 2);
			saveSetting(p , true);
		}
	}

}
