package me.flash1110.Otxplugin.objects;

import java.util.List;
import java.util.UUID;

import me.flash1110.Otxplugin.Others;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CombatPlayer {

	private UUID Uuid;
	private String Name, CurrentIP, DisplayName;
	private int CombatTime;
	private Integer TaskID, DonorPoints, Bounty;
	private Long LastEnderPearlThrow, BanTime, MuteTime, MysteryBox, ColorDelay, LastBounty;
	private boolean building, watcher, macro, staff, Kill, RandomColor;
	private String LastBanMessage;
	private	List<String> Bans, UsedIPs;
	
	
	public CombatPlayer(String displayname, UUID id, String IP, List<String> UsedIPs, String name, Integer bounty, boolean randomcolor, Integer points) {
		DisplayName = displayname;
		Uuid = id;
		Name = name;
		this.CurrentIP = IP;
		this.UsedIPs = UsedIPs;
		Bounty = bounty;
		RandomColor = randomcolor;
		TaskID = null;
		building = false;
		watcher = false;
		macro = true;
		staff = false;
		LastBounty = System.currentTimeMillis();
		MysteryBox = (long) 0;
		ColorDelay = (long) 0;
		
		DonorPoints = points;
		
		if (displayname != null) {
			String displaynamecheck = new String(displayname);
			if (Others.stripColour(displaynamecheck).equals(Name))
				DisplayName = displayname; 
			else
				DisplayName = null;
		} else
			DisplayName = null;
	}
	
	public Long getColor() {
		return ColorDelay;
	}
	
	public void setColor(long delay) {
		ColorDelay = delay;
	}
	
	public Boolean isRandomColor() {
		return RandomColor;
	}
	
	public void setRandomColor(boolean random) {
		RandomColor = random;
	}
	
	public Boolean getKillOnLogin() {
		return Kill;
	}
	
	public Long getLastBountyPlace() {
		return LastBounty;
	}
	
	public void setLastBountyPlace() {
		LastBounty = System.currentTimeMillis();
	}
	
	public void setKill(boolean kill) {
		Kill = kill;
	}
	
	public Long getMysteryBox() {
		return MysteryBox;
	}
	
	public String getIP() {
		return CurrentIP;
	}
	
	public List<String> getUsedIPs() {
		return UsedIPs;
	}
	
	public void setMysteryBox() {
		MysteryBox = System.currentTimeMillis();
	}
	
	public Integer getBounty() {
		return Bounty;
	}
	
	public void addToBounty(Integer addto) {
		Bounty+= addto;
	}
	
	public void setBounty(Integer bounty) {
		Bounty = bounty;
	}
	
	public void resetBounty() {
		Bounty = 0;
	}
	
	public boolean getStaffChat() {
		return staff;
	}
	
	public void setStaffChat(boolean staff) {
		this.staff = staff;
	}
	
	public UUID getID() {
		return Uuid;
	}
	
	public String getName() {
		return Name;
	}
	
	public boolean isMacro() {
		return macro;
	}
	
	public void setMacro(boolean macro) {
		this.macro = macro;
	}
	
	public boolean isBuilding() {
		return building;
	}
	
	public void setBuilding(boolean build) {
		building = build;
	}
	
	public void setWatcherMode(Boolean watcher) {
		this.watcher = watcher;
	}
	
	public Boolean getWatcherMode() {
		return watcher;
	}
	
	public void resetCombatTime() {
		CombatTime = 10;
	}
	
	public Integer getCombatTime() {
		return CombatTime;
	}
	
	public void setCombatTime(Integer time) {
		CombatTime = time;
	}
	
	/*public void startCombatTimer() {
		
		if (Bukkit.getPlayer(getID()) == null) {
			return;
		}
		
		Player player = Bukkit.getPlayer(getID());
		
		if (TaskID != null)
			Bukkit.getServer().getScheduler().cancelTask(TaskID);
		
		resetCombatTime();
		
		if (player.isSprinting()) {
			ActionBar.sendAnnouncement(player, "&4Combat time frozen while &aSprinting");
		} else {
			ActionBar.sendAnnouncement(player, "&6Combat time &d" + getCombatTime());
		}
		
		CombatTimer();
		
	//	CombatBlocks.sendCombatBlocks(player);
	}
	
	private void CombatTimer() {
		
		if (TaskID != null)
			Bukkit.getServer().getScheduler().cancelTask(TaskID);
		
		TaskID = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(EmpirePlugin.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
								
				TaskID = null;
				
				if (Bukkit.getPlayer(getID()) == null) {
					return;
				}
				
				Player player = Bukkit.getPlayer(getID());
				
				if (player.isFlying() || player.getAllowFlight()) {
					player.setFlying(false);
					player.setAllowFlight(false);
					player.sendMessage(ChatColor.RED + "No flying in combat");
				}
				
				if (getCombatTime() <= 0) {
		//			CombatBlocks.removeCombatBlocks(player);
					setCombatTime(0);
					ActionBar.sendAnnouncement(player, "");
					return;
				}
				
				if (player.isDead()) {
		//			CombatBlocks.removeCombatBlocks(player);
					setCombatTime(0);
					ActionBar.sendAnnouncement(player, "");
					return;
				}
				
				if (player.isSprinting()) {
			//		CombatBlocks.sendCombatBlocks(player);
					resetCombatTime();
					ActionBar.sendAnnouncement(player, "&4Combat time frozen while &aSprinting");
					CombatTimer();
					return;
				}
				
				int id = player.getLocation().getBlock().getTypeId();
				
				if (id == 8 || id == 9) {
		//			CombatBlocks.sendCombatBlocks(player);
					resetCombatTime();
					ActionBar.sendAnnouncement(player, "&4Combat time frozen while &bSwimming");
					CombatTimer();
					return;
				}
				
				CombatTime--;
				
				if (CombatTime <= 0) {
		//			CombatBlocks.removeCombatBlocks(player);
					ActionBar.sendAnnouncement(player, "&aIt is safe to logout");
					return;
				}
				
		//		CombatBlocks.sendCombatBlocks(player);
				
				ActionBar.sendAnnouncement(player, "&6Combat time &d" + getCombatTime());
				CombatTimer();
								
			}
		}, 20);
		
	}
	 */
	public Long getLastThrewEnderPearl() {
		return LastEnderPearlThrow;
	}
	
	public void setLastThrewEnderPearl() {
		LastEnderPearlThrow = System.currentTimeMillis();
	}
	
	public Integer getDonnorPointBalance() {
		return DonorPoints;
	}

	public void removeDonnorPointBalance(Integer cost) {
		DonorPoints-= cost;
	}
	
	public void addDonnorPointBalance(Integer cost) {
		DonorPoints+= cost;
	}
	
	public void setDonnorPointBalance(Integer cost) {
		DonorPoints = cost;
	}
	
public void setDisplayName(String displayname) {
		
		DisplayName = displayname;
		
		Player player = Bukkit.getPlayer(getID());
		
		if (player != null && player.isOnline())
			player.setDisplayName(getDisplayName());
		
	}
	
	public void refreshDisplayName() {
		
		Player player = Bukkit.getPlayer(getID());
		
		if (player != null && player.isOnline()) {
			
			if (player.hasPermission("0txplugin.nick"))
				player.setDisplayName(getDisplayName());
			else
				player.setDisplayName(null);
			
		}
		
	}
	
	public String getDisplayName() {
		return DisplayName;
	}
	
	
	public enum Rank {
		SOLDIER("empireplugin.soldier", ChatColor.YELLOW),
		KNIGHT("empireplugin.knight", ChatColor.GREEN),
		GENERAL("empireplugin.general", ChatColor.AQUA),
		KING("empireplugin.king", ChatColor.GOLD),
		EMPEROR("empireplugin.emperor", ChatColor.RED),
		END("empireplugin.end", ChatColor.DARK_RED);
		
		String perm;
		ChatColor color;
		
		Rank(String permission, ChatColor chat) {
			perm = permission;
			color = chat;
		}
		
		public ChatColor getColor() {
			return color;
		}
		
		public String getPerm() {
			return perm;
		}
	}
}
