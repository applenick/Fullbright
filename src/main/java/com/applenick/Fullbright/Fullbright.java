package com.applenick.Fullbright;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.applenick.Fullbright.brightness.BrightnessManager;
import com.applenick.Fullbright.commands.FullbrightCommand;
import com.applenick.Fullbright.listeners.FullbrightListener;
import com.applenick.Fullbright.utils.AppleUtils;
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
	
	private BrightnessManager bmanager = null;
	public BrightnessManager getManager(){
		return bmanager;
	}

	@Override
	public void onEnable(){
		fullbright = this;

		AppleUtils.header(ChatColor.GREEN + "Fullbright is starting up.", ChatColor.GOLD + "-");
		
		this.saveDefaultConfig();
		
		this.bmanager = new BrightnessManager();
		
		this.setupCommands();
		this.registerEvents();
		
		AppleUtils.footer(ChatColor.GREEN + "Fullbright is starting up.", ChatColor.GOLD + "-");
	}

	@Override
	public void onDisable(){
		this.saveConfig();
		AppleUtils.header(ChatColor.GREEN + "Fullbright has been disabled.", ChatColor.GOLD + "-");
	}
	
	private void registerEvents(){
		PluginManager PM = this.getServer().getPluginManager();
		PM.registerEvents(new FullbrightListener(), this);
		AppleUtils.console(ChatColor.GREEN + "Events have been setup.");
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
		AppleUtils.console(ChatColor.GREEN + "Commands have been setup.");
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