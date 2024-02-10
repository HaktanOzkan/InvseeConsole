package com.invsee;

import org.bukkit.plugin.java.JavaPlugin;

public class InvseeMainClass extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage("§a[INVSEECONSOLE] Thank you all for using my plugin. Please don't forget to give stars from spigotmc.org :)");
		getServer().getConsoleSender().sendMessage("§a[INVSEECONSOLE] InvseeConsole plugin is active and working!");
		getCommand("invseeconsole").setExecutor(new InvConsoleCommand());
		getCommand("shulkerview").setExecutor(new ShulkerViewCommand());
		getCommand("shulkerreset").setExecutor(new ShulkerResetCommand());
	}
}
