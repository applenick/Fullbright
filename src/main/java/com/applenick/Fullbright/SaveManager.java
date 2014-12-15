package com.applenick.Fullbright;

import java.util.UUID;


public class SaveManager {
	
	
	public static void addPlayer(UUID uuid){
		Fullbright.get().getConfig().addDefault(uuid.toString(), false);
		Fullbright.get().saveConfig();
	}
	
	public static void updatePlayer(UUID uuid, boolean setting){
		Fullbright.get().getConfig().addDefault(uuid.toString(), setting);
		Fullbright.get().saveConfig();
	}
	
	public static boolean getSavedSetting(String uuidString){
		return Fullbright.get().getConfig().getBoolean(uuidString);
	}
	
	public static boolean hasSetting(String uuidString){
		if(Fullbright.get().getConfig().contains(uuidString)){
			return true;
		}else{
			return false;
		}
	}

	
	

}
