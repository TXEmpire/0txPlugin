package me.flash1110.Otxplugin.offline;

import java.util.UUID;

import me.flash1110.Otxplugin.objects.CombatPlayer;

public class OfflineAccessType {
	
	private	UUID Sender;
	private String TargetName;
	private CombatPlayer CTarget;
	
	public OfflineAccessType(UUID sender, String targetname) {
		Sender = sender;
		TargetName = targetname;
		CTarget = null;
	}
	
	public UUID getSenderID() {
		return Sender;
	}
	
	public String getTargetName() {
		return TargetName;
	}
	
	public CombatPlayer getCTarget() {
		return CTarget;
	}
	
	public void setCTarget(CombatPlayer ctarget) {
		CTarget = ctarget;
	}
	
	public void call() {}

}
