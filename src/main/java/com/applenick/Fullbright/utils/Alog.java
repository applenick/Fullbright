package com.applenick.Fullbright.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.util.ChatPaginator;

import com.applenick.Fullbright.Fullbright;
import com.google.common.base.Strings;

public class Alog {
		
	public static void console(String msg){
		ConsoleCommandSender CS = Fullbright.get().getServer().getConsoleSender();
		CS.sendMessage(msg);
	}
	
	public static String dashedChatMessage(String message, String c, ChatColor color) {
        message = " " + message + " ";
        String dashes = Strings.repeat(c, (ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH - ChatColor.stripColor(message).length() - 2) / 2);
        return color + dashes + message + color + dashes;
    }

}
