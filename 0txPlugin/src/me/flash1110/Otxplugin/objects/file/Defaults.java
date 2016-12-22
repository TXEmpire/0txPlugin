package me.flash1110.Otxplugin.objects.file;

import java.util.ArrayList;

public enum Defaults {

	PLAYERNAME(""),
	USEDIP(new ArrayList<String>()),
	IPADDRESS(""),
	DONNOR_POINTS(0),
	RANDOM_COLOR(false),
	BOUNTY(0);
	
	Object Default;
	
	Defaults(Object defaulttype) {
		Default = defaulttype;
	}
	
	public Object getDefault() {
		return Default;
	}
}
