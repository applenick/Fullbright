package com.applenick.Fullbright.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.applenick.Fullbright.utils.Alog;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class FullbrightCommand {
	
	@Command(
			aliases = {"fullbright" , "fb"},
			desc = "Activated Fullbright Command",
			max = 1,
			flags = "d"
			)
	@CommandPermissions("ice.fullbright")
	public static void fullBCMD(final CommandContext args, final CommandSender sender) throws CommandException {
		Player p = (Player) sender;
		
		if (args.argsLength() <= 0){
			p.sendMessage(Alog.dashedChatMessage(ChatColor.WHITE + "Fullbright", "-", ChatColor.AQUA));
			p.sendMessage(ChatColor.AQUA + "Commands:");
			p.sendMessage(ChatColor.DARK_GREEN + "/fullbright" + ChatColor.WHITE + "- " + ChatColor.DARK_AQUA + "Activates Fullbrightness");
			p.playSound(p.getLocation(), Sound.WITHER_DEATH, 5, 5);
		}
	}

}
