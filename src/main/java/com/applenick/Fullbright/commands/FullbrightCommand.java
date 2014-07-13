package com.applenick.Fullbright.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.applenick.Fullbright.FullbrightSaveManager;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class FullbrightCommand {
		
	@Command(
			aliases = {"fullbright" , "fb"},
			desc = "Activated Fullbright Command",
			max = 0,
			flags = "d"
			)
	@CommandPermissions("ice.fullbright")
	public static void fullBCMD(final CommandContext args, final CommandSender sender) throws CommandException {
		Player p = (Player) sender;
		FullbrightSaveManager.toggleCommand(p);
	}
}
