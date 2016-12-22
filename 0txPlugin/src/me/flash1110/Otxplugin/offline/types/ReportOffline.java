package me.flash1110.Otxplugin.offline.types;

import me.flash1110.Otxplugin.offline.OfflineAccessType;


public class ReportOffline /* extends OfflineAccessType */{

	
	/*public ReportOffline(UUID sender, String targetname) {
		super(sender, targetname);
	}
	
	@Override
	public void call() {
		
		CommandSender sender;
		
		if (getSenderID() != null)
			sender = Bukkit.getPlayer(getSenderID());
		else
			sender = Bukkit.getConsoleSender();
		
		if (getCTarget() == null) {
			if (sender != null)
				sender.sendMessage(ChatColor.RED + "Player " + ChatColor.GOLD + getTargetName() + ChatColor.RED + " has never joined");	
			return;
		}
		
		if (sender != null) {
			
			if (getCTarget().getMuted()) {
				
				String time = getCTarget().getMuteTime() == -1 ? ChatColor.RED + "with no expiry" : "for " + ChatColor.RED + Report.caculateBanLength(getCTarget().getMuteTime());
				sender.sendMessage(ChatColor.WHITE + "- " + ChatColor.GOLD + "Currently muted " + time);
				
			}
			
		}
		
		if (sender != null) {
			
			if (getCTarget().getBanned()) {
				
				String time = getCTarget().getBanTime() == -1 ? ChatColor.RED + "with no expiry" : "for " + ChatColor.RED + Report.caculateBanLength(getCTarget().getBanTime());
				
				sender.sendMessage(ChatColor.WHITE + "- " + ChatColor.GOLD + "Currently banned " + time);
				sender.sendMessage(ChatColor.WHITE + "- " + ChatColor.GOLD + "Ban reason: " + ChatColor.LIGHT_PURPLE + getCTarget().getLastBanMessage());
				
			}
			
			if (getCTarget().getBans().size() > 0) {
				
				sender.sendMessage(ChatColor.WHITE + "- " + ChatColor.GRAY + "Past Bans ");
				
				for (String s : getCTarget().getBans()) {
					
					String[] split = s.split("@");
					
					String date = split[0];
					String issuer = split[1];
					String length = split[2];
					
					if (length.equalsIgnoreCase("Permanently"))
						length = ChatColor.RED + length;
					else
						length = "for " + ChatColor.RED + length;
					
					String comment = "";
					
					if (split.length > 3) {
						int oneon1 = 3;
						while (oneon1 < split.length) {
							comment = comment + split[oneon1] + " ";
							oneon1++;
						}
					}
					
					sender.sendMessage(ChatColor.WHITE + "- " + ChatColor.AQUA + "On " + ChatColor.RED + date);
					sender.sendMessage(ChatColor.YELLOW + "> " + ChatColor.GOLD + "Banned " + length + ChatColor.GOLD + " by " + ChatColor.GREEN + issuer);
					sender.sendMessage(ChatColor.YELLOW + "> " + ChatColor.GOLD + "Reason: " + ChatColor.GRAY + comment);
					
				}
				
			}
			
		}
	}*/
} 
