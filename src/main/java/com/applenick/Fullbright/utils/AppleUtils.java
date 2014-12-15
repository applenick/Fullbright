package com.applenick.Fullbright.utils;

import org.bukkit.ChatColor;

import com.applenick.Fullbright.Fullbright;
import com.google.common.base.Strings;

public class AppleUtils {
		
	public static void console(String msg){
		Fullbright.get().getServer().getConsoleSender().sendMessage(msg);
	}
		
	public static String header(String message , String seperator){
		String header = " " + message + " ";
		String line = Strings.repeat(seperator, (55 - ChatColor.stripColor(message).length() - 2) / 2);
		return line + header + line;
	}
	
	public static String  footer(String message , String seperator){
		String line = Strings.repeat(seperator, (55 - ChatColor.stripColor(message).length() -2) / 2);
		String msg = Strings.repeat(seperator, message.length());
		return line + msg + line;
	}


}
