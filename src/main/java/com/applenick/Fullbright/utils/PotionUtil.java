package com.applenick.Fullbright.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionUtil {
	
	public static void applyNightVison(Player player){
		player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , Integer.MAX_VALUE , 3));
	}

	public static void removeNightVision(Player player){
		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
	}
}
