package com.applenick.Fullbright;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.applenick.Fullbright.commands.FullbrightCommand;
import com.applenick.Fullbright.utils.Alog;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;

public class Fullbright extends JavaPlugin {

	public static String prefix = ChatColor.WHITE + "[" + ChatColor.AQUA + "Fullbright" + ChatColor.WHITE + "] ";


	private static Fullbright fullbright = null;
	public static Fullbright get(){
		return fullbright;
	}


	public void onEnable(){
		fullbright = this;

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.reloadConfig();

		setup();
	}

	public void setup(){
		Alog.console(Alog.dashedChatMessage(ChatColor.AQUA + "FullBright", "#", ChatColor.YELLOW));
		Alog.console(ChatColor.GOLD +"A Plugin Created by " + ChatColor.DARK_RED +"AppleNick");
		Alog.console(ChatColor.GRAY + "View more of my Projects at " + ChatColor.BLUE + "http://applenick.com");
		PluginManager PM = this.getServer().getPluginManager();
		PM.registerEvents(new FullbrightListener(), this);
		setupCommands();
		Alog.console(ChatColor.GREEN + "Fullbright Listeners have been Registered");
		Alog.console(ChatColor.GOLD + "Fullbright has been setup.");
	}

	private CommandsManager<CommandSender> commands;

	private void setupCommands() {
		this.commands = new CommandsManager<CommandSender>() {
			@Override public boolean hasPermission(CommandSender sender, String perm) {
				return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
			}
		};

		CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);
		cmdRegister.register(FullbrightCommand.class);
		Alog.console(ChatColor.GREEN + "Fullbright Commands have been setup.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			this.commands.execute(cmd.getName(), args, sender, sender);
		} catch (CommandPermissionsException e) {
			sender.sendMessage(ChatColor.RED + "You don't have permission.");
		} catch (MissingNestedCommandException e) {
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (CommandUsageException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (WrappedCommandException e) {
			if (e.getCause() instanceof NumberFormatException) {
				sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
			} else {
				sender.sendMessage(ChatColor.RED + "An error has occurred. See console.");
				e.printStackTrace();
			}
		} catch (CommandException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
		}

		return true;
	}
}