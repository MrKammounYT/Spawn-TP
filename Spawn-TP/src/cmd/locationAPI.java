package cmd;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class locationAPI {
	  static File file = new File("plugins/SpawnTP", "Locations.yml");
	  
	  static FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
	  
	  public static void create() {
	    try {
	      file.createNewFile();
	      cfg.save(file);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	  }
	  
	  public static void setLocation(Location loc, String name) {
	    String world = loc.getWorld().getName();
	    double x = loc.getX();
	    double y = loc.getY();
	    double z = loc.getZ();
	    double yaw = loc.getYaw();
	    double pitch = loc.getPitch();
	    cfg.set(String.valueOf(name) + ".world", world);
	    cfg.set(String.valueOf(name) + ".x", Double.valueOf(x));
	    cfg.set(String.valueOf(name) + ".y", Double.valueOf(y));
	    cfg.set(String.valueOf(name) + ".z", Double.valueOf(z));
	    cfg.set(String.valueOf(name) + ".yaw", Double.valueOf(yaw));
	    cfg.set(String.valueOf(name) + ".pitch", Double.valueOf(pitch));
	    try {
	      cfg.save(file);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	  }
	  
	  public static Location getLocation(String name) {
	    String world = cfg.getString(String.valueOf(name) + ".world");
	    double x = cfg.getDouble(String.valueOf(name) + ".x");
	    double y = cfg.getDouble(String.valueOf(name) + ".y");
	    double z = cfg.getDouble(String.valueOf(name) + ".z");
	    double yaw = cfg.getDouble(String.valueOf(name) + ".yaw");
	    double pitch = cfg.getDouble(String.valueOf(name) + ".pitch");
	    Location loc = new Location(Bukkit.getWorld(world), x, y, z);
	    loc.setYaw((float)yaw);
	    loc.setPitch((float)pitch);
	    return loc;
	  }

}
