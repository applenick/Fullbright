package com.applenick.Fullbright.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.applenick.Fullbright.FullbrightSaveManager;
import com.applenick.Fullbright.utils.Alog;
import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class FullbrightCommand {
		
	@Command(
			aliases = {"fullbright" , "fb"},
			desc = "Activates Fullbright Command",
			max = 0
			)
	@CommandPermissions("fullbright.use")
	public static void fullBCMD(final CommandContext args, final CommandSender sender) throws CommandException {
		if(!(sender instanceof Player)){
			Alog.console(ChatColor.RED + "Fullbright is only for players");
			return;
		}
		
		Player p = (Player) sender;
		FullbrightSaveManager.toggleCommand(p);
	}
}
