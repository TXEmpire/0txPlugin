package me.flash1110.Otxplugin.objects.macro;

public class Click {

private Long Time;
	
	public Click(Long time) {
		Time = time;
	}
	
	public Boolean checkExpire() {
		if ((Time + 1000) > System.currentTimeMillis())
			return false;
		return true;
	}
}
