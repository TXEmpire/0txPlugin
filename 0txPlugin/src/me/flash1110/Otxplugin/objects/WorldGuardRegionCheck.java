package me.flash1110.Otxplugin.objects;

import java.util.Set;

import me.flash1110.Otxplugin.objects.file.Data;

import org.bukkit.Location;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class WorldGuardRegionCheck {

	public static Boolean getWithinAllowedRegion(Location location) {
		 
		ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionContainer().get(location.getWorld()).getApplicableRegions(location);
		
		Set<ProtectedRegion> regions = set.getRegions();
		
		for (ProtectedRegion region : regions) {
			
			if (Data.getRegions().contains(region.getId().toLowerCase()))
				return true;
			
		}
		
		return false;
		
		
	}
}
