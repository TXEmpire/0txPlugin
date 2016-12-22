package me.flash1110.Otxplugin.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.UUID;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.listeners.ChatColorListener;
import me.flash1110.Otxplugin.objects.file.Data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("empireplugin.reload")) {
			sender.sendMessage(ChatColor.RED + "You lack permission");
			return true;
		}
		
		try {
			Data.loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sender.sendMessage(ChatColor.GOLD + "Successfully reloaded Data.txt");
		
		for (Entry<UUID, ChatColor> entry : ChatColorListener.list.entrySet()) {
			OtxPlugin.p.getConfig().set("colors." + entry.getKey().toString(), entry.getValue().name());
			OtxPlugin.p.saveConfig();
		}
		
		sender.sendMessage(ChatColor.GOLD + "Successfully saved config.yml");
		
		OtxPlugin.p.getConfig().options().copyDefaults(true);
		OtxPlugin.p.saveConfig();
		
		if (OtxPlugin.p.getConfig().getConfigurationSection("colors") == null) {
			OtxPlugin.p.getConfig().createSection("colors");
			OtxPlugin.p.saveConfig();
		}
		
		if (OtxPlugin.p.getConfig().getConfigurationSection("commands") == null) {
			OtxPlugin.p.getConfig().createSection("commands");
			OtxPlugin.p.saveConfig();
		}
		
		for (String id : OtxPlugin.p.getConfig().getConfigurationSection("colors").getKeys(false)) {
			ChatColorListener.list.put(UUID.fromString(id), ChatColor.valueOf(OtxPlugin.p.getConfig().getString("colors." + id)));
		}
		
		ArrayList<String> defaults = new ArrayList<String>();
		defaults.add("message");
		defaults.add("msg");
		defaults.add("r");
		defaults.add("reply");

		if (OtxPlugin.p.getConfig().getStringList("commands") == null ||OtxPlugin.p. getConfig().getStringList("commands").isEmpty() || OtxPlugin.p.getConfig().getStringList("commands").size() == 0) {
			OtxPlugin.p.getConfig().set("commands", defaults);
			OtxPlugin.p.saveConfig();
		}
		
		sender.sendMessage(ChatColor.GOLD + "Successfully loaded config.yml");
		
		Bukkit.getScheduler().cancelTasks(OtxPlugin.p);
		sender.sendMessage(ChatColor.GOLD + "Successfully cancelled donation message announcement");
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(OtxPlugin.p, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(Data.getDonateMessage());
			}
		}, 20, 20 * Data.getSecondsRepeat());
		
		sender.sendMessage(ChatColor.GOLD + "Successfully started donation message announcement");
		
		return true;
	}

}
