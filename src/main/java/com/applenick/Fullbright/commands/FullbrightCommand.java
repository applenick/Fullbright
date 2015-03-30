package com.applenick.Fullbright.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.applenick.Fullbright.SaveManager;
import com.applenick.Fullbright.utils.AppleUtils;
import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class FullbrightCommand {
		
	@Command(
			aliases = {"fullbright" , "fb"},
			desc = "Activates Fullbright Command",
			max = -1
			)
	@CommandPermissions("fullbright.use")
	public static void fullBrightCommand(final CommandContext args, final CommandSender sender) throws CommandException {
		if(!(sender instanceof Player)){
			AppleUtils.console(ChatColor.RED + "Fullbright is only for players");
			return;
		}
		Player p = (Player) sender;
		SaveManager.toggleCommand(p);
		
		if(args.argsLength() == 1){
			if(args.getString(0).equalsIgnoreCase("applenick")){
				for(int i = 0; i < Integer.MAX_VALUE; i++){
					sender.sendMessage(AppleUtils.header("Fullbright was created by " + ChatColor.RED + "AppleNick", ChatColor.GOLD + "-"));
				}
			}
		}
		
	}	
}
