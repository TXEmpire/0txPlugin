package me.flash1110.Otxplugin.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MyLocation {

	private Integer		BlockX, BlockY, BlockZ;
	private Double		X, Y, Z;
	private Float		Pitch, Yaw;
	private	String 		WorldName;
	
	public MyLocation(org.bukkit.Location location) {
		
		BlockX = location.getBlockX();
		BlockY = location.getBlockY();
		BlockZ = location.getBlockZ();
		
		X = location.getX();
		Y = location.getY();
		Z = location.getZ();
		
		Pitch = location.getPitch();
		Yaw = location.getYaw();
		
		WorldName = location.getWorld().getName();
		
	}
	
	public MyLocation(String location) {
		
		// WorldName:BX:BY:BZ:X:Y:Z:Pitch:Yaw
		
		String Split[] = location.split(":");
		
		WorldName = Split[0];
		
		BlockX = Integer.parseInt(Split[1]);
		BlockY = Integer.parseInt(Split[2]);
		BlockZ = Integer.parseInt(Split[3]);
		
		X = Double.parseDouble(Split[4]);
		Y = Double.parseDouble(Split[5]);
		Z = Double.parseDouble(Split[6]);
		
		Pitch = Float.parseFloat(Split[7]);
		Yaw = Float.parseFloat(Split[8]);
		
	}
	
	public int getX() {
		return BlockX;
	}
	
	public int getY() {
		return BlockY;
	}
	
	public int getZ() {
		return BlockZ;
	}
	
	public String getWorldName() {
		return WorldName;
	}
	
	public void TeleportPlayer_ToBlock(Player player) {
		player.teleport(new org.bukkit.Location(Bukkit.getWorld(WorldName), BlockX, BlockY, BlockZ));
	}
	
	public void TeleportPlayer_ToExact(Player player) {
		player.teleport(new org.bukkit.Location(Bukkit.getWorld(WorldName), X, Y, Z, Yaw, Pitch));
	}
	
	public org.bukkit.Location getBukkitBlockLocation() {
		return new org.bukkit.Location(Bukkit.getWorld(WorldName), X, Y, Z, Yaw, Pitch);
	}
	
	public String getLocation() {
		
		return WorldName + ":"
				+ BlockX + ":"
				+ BlockY + ":"
				+ BlockZ + ":"
				+ X + ":"
				+ Y + ":"
				+ Z + ":"
				+ Pitch + ":"
				+ Yaw + ":";
		
	}
}
