package me;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import cmd.setspawn;
import cmd.spawn;


public class TP extends JavaPlugin implements Listener{

	private static TP instance;

	public static ArrayList<Player> intp = new ArrayList<>();
	
	
	
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("setspawn").setExecutor(new setspawn());
		getCommand("spawn").setExecutor(new spawn());
		this.saveDefaultConfig();

	}
	public void onDisable() {
	}
	
	
	public static TP getinstance() {
		return instance;
	}
	@EventHandler
	public void onquit(PlayerQuitEvent e) {
		if(intp.contains(e.getPlayer())) {
			intp.remove(e.getPlayer());
		}
	}
	@EventHandler
	public void onTpMoveEvent(PlayerMoveEvent e) {
		if(intp.contains(e.getPlayer())) {
			if(e.getPlayer().hasPermission("spawn.tp.move.exempt"))return;
			 if (e.getFrom().getZ() != e.getTo().getZ() && e.getFrom().getX() != e.getTo().getX()) {
				  e.getPlayer().sendMessage(getConfig().getString("teleport-cancel-message").replace('&', '§'));
				  intp.remove(e.getPlayer());
		            // player moved, cancel teleport
		        }
			 }
	}
	
	
}
