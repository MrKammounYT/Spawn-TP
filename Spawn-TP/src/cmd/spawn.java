package cmd;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


import me.TP;

public class spawn implements CommandExecutor {

	
	int seconds = TP.getinstance().getConfig().getInt("teleport-delay");

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("spawn.tp.cooldown.exempt")) {
				p.sendMessage(TP.getinstance().getConfig().getString("teleport-success-message").replace('&', '§'));
				try {
					p.teleport(locationAPI.getLocation("spawn"));
					}catch (Exception e) {
						p.sendMessage(TP.getinstance().getConfig().getString("teleport-nospawn-message").replace('&', '§'));
					}
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0f, 4.0f);

				return true;
			}
			TP.intp.add(p);
			p.sendMessage("§eTeleporting to Spawn in §c5 §eSeconds. Dont Move!");
			p.playSound(p.getLocation(), Sound.CLICK, 2.0f, 3.0f);
			Bukkit.getScheduler().runTaskLater(TP.getinstance(), new BukkitRunnable() {
				@Override
				public void run() {
					if(TP.intp.contains(p)){
					p.sendMessage(TP.getinstance().getConfig().getString("teleport-success-message").replace('&', '§'));
					try {
					p.teleport(locationAPI.getLocation("spawn"));
					}catch (Exception e) {
						p.sendMessage(TP.getinstance().getConfig().getString("teleport-nospawn-message").replace('&', '§'));
					}
					TP.intp.remove(p);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0f, 4.0f);

					}
				}
			},5*20);
			
			
			
			
		}
		
		
		
		return false;
	}
	
	

}
