package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.listeners.ChatColorListener;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Event_GivePotionEffect extends SpendEvent {
	
	Integer slot;
	
	Integer Duration;
	Integer Level;
	
	PotionEffectType Type;
	
	String Name;
	String PotionDuration;
	
	public Event_GivePotionEffect(Integer slot, Integer cost, PotionEffectType pet, Integer duration, Integer level) {
		super (slot, cost);
		Duration = duration;
		Level = level;
		Type = pet;
		Name = getName();
		setPotionDuration();
	}
	
	@Override
	public void run(Player player) {
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			if (p.hasPotionEffect(Type)) {
				
				for (PotionEffect pe : p.getActivePotionEffects()) {
					
					if (pe.getType().getName().equals(Type.getName())) {
						
						if ((Level-1) >= pe.getAmplifier()) {
							p.removePotionEffect(Type);
							p.addPotionEffect( new PotionEffect( Type, (Duration * 20), (Level-1) ) );
						}
						
					}
					
				}

			} else {
				p.addPotionEffect( new PotionEffect( Type, (Duration * 20), (Level-1) ) );
			}
			
			
		}
		for (Player P : Bukkit.getOnlinePlayers()) 
			P.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GREEN + "" + ChatColor.BOLD + " has donated for a " + ChatColor.translateAlternateColorCodes('&', "&a&lSERVER BOOST!"));
		
		OtxPlugin.BroadcastMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.YELLOW + " gives everyone " + ChatColor.GREEN + Name + ChatColor.GREEN + (Level > 1 ? (" " + Level + " ") : " ") + ChatColor.GRAY + PotionDuration );
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ChatColorListener.plugin, new Runnable() {
			int time = 6000;
			public void run() {
				time = time - 20;
				
				switch (time) {
				case 3000:
					OtxPlugin.BroadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aThe server boost " + "&d" + WordUtils.capitalizeFully(Type.getName().replace("_", " ")) + " &ahas &e2m30s &aleft!"));
					break;
				case 1200:
					OtxPlugin.BroadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aThe server boost " + "&d" + WordUtils.capitalizeFully(Type.getName().replace("_", " ")) + " &ahas &e1m &aleft!"));
					
					break;
				case 600:
					OtxPlugin.BroadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aThe server boost  " + "&d" + WordUtils.capitalizeFully(Type.getName().replace("_", " ")) + " &ais about to expire!"));
					break;
				default:
					break;
				}	
			}
		}, 20, 20);
		
	}
	
	@Override
	public Boolean check(Player player) {
		
		if (Name == null) {
			player.sendMessage(ChatColor.RED + "This item is incorrectly set up");
			return false;
		}
		
		return true;
		
	}
	
	private void setPotionDuration() {
		
		Integer duration = Duration;
		
		Integer mins = 0;
		
		while (duration >= 60) {
			duration-= 60;
			mins++;
		}
		
		Integer second = duration;
		String seconds = second > 9 ? ("" + second) : ("0" + second);
		
		String finaltime = mins + ":" + seconds;
		
		PotionDuration = "(" + finaltime + ")";
		
	}
	
	private String getName() {
		
		if (Type.getName().equals("ABSORPTION"))
			return "Absorption";
		if (Type.getName().equals("BLINDNESS"))
			return "Blindness";
		if (Type.getName().equals("CONFUSION"))
			return "Confusion";
		if (Type.getName().equals("DAMAGE_RESISTANCE"))
			return "Resistance"; // ?
		if (Type.getName().equals("FAST_DIGGING"))
			return "Haste";
		if (Type.getName().equals("FIRE_RESISTANCE"))
			return "Fire Resistance";
		if (Type.getName().equals("HEALTH_BOOST"))
			return "Health Boost";
		if (Type.getName().equals("HUNGER"))
			return "Hunger";
		if (Type.getName().equals("INCREASE_DAMAGE"))
			return "Strength";
		if (Type.getName().equals("INVISIBILITY"))
			return "Invisibility";
		if (Type.getName().equals("JUMP"))
			return "Leaping";
		if (Type.getName().equals("NIGHT_VISION"))
			return "Night Vision";
		if (Type.getName().equals("Poison"))
			return "Poison";
		if (Type.getName().equals("REGENERATION"))
			return "Regeneration";
		if (Type.getName().equals("SATURATION"))
			return "Saturation";
		if (Type.getName().equals("SLOW"))
			return "Slowness";
		if (Type.getName().equals("SLOW_DIGGING"))
			return "Dull";
		if (Type.getName().equals("SPEED"))
			return "Swiftness";
		if (Type.getName().equals("WATER_BREATHING"))
			return "Water Breathing";
		if (Type.getName().equals("WEAKNESS"))
			return "Weakness";
		if (Type.getName().equals("WITHER"))
			return "Decay";
		if (Type.getName().equals("LUCK"))
			return "Luck";
		
		return null;
		
	}

}
