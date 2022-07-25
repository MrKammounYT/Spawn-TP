package cmd;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.TP;

public class setspawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {

		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("spawn.tp.set")) {
				locationAPI.setLocation(p.getLocation(), "spawn");
				p.sendMessage(TP.getinstance().getConfig().getString("teleport-setspawn-message").replace('&','§'));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0f, 4.0f);
				
				
			}
		}
		
		
		return false;
	}

}
