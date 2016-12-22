package me.flash1110.Otxplugin.donor.events;

import me.flash1110.Otxplugin.OtxPlugin;
import me.flash1110.Otxplugin.objects.CombatPlayer;

import org.bukkit.entity.Player;

public class SpendEvent {
	
	// Figure out how to have the slot stored here, not in the actual event!
	
	private Integer			Slot;
	private	Integer			Cost;
	
	public SpendEvent(Integer slot, Integer cost) {
		Slot = slot;
		Cost = cost;
	}

	public void run(Player player) {
		
	}
	
	public Integer getSlot() {
		return Slot;
	}
	
	public Integer getCost() {
		return Cost;
	}
	
	public Boolean checkcost(Player player) {
		CombatPlayer cplayer = OtxPlugin.getPlayer(player);
		if (cplayer != null)
			if (cplayer.getDonnorPointBalance() >= Cost)
				return true;
		return false;
	}

	public Boolean check(Player player) {
		return true;
	}

}
