package com.github.cubixcraft.Animagus;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main			instance;
	
	public static Logger		log			= Bukkit.getLogger();
	private PluginManager		pm;
	
	public static Permission	permission	= null;
	public static Economy		economy		= null;
	
	@Override
	public void onEnable() {
		this.instance = this;
		this.pm = getServer().getPluginManager();
		
		// Check for Vault
		Plugin checkplugin = this.pm.getPlugin("Vault");
		if (checkplugin == null) {
			this.log.severe("[Animagus] Can't load Vault. Get it here: http://dev.bukkit.org/server-mods/vault/");
			this.pm.disablePlugin(this);
			return;
		}
		
		// Setup Vault
		this.setupPermissions();
		this.setupEconomy();
	}
	
	@Override
	public void onDisable() {}
	
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer()
				.getServicesManager().getRegistration(
						net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		
		return (economy != null);
	}
}
