package me.flash1110.Otxplugin.objects.macro;

import java.util.ArrayList;
import java.util.List;

public class ClickLog {

private List<Click> Clicks;
	
	private Long LastChecked;
	
	public ClickLog() {
		Clicks = new ArrayList<Click>();
		LastChecked = 0L;
	}
	
	public void addClick(Click click) {
		Clicks.add(click);
	}
	
	public void removeClick(Click click) {
		Clicks.remove(click);
	}
	
	public Integer getClickCount() {
		return Clicks.size();
	}
	
	public void updateLastChecked() {
		LastChecked = System.currentTimeMillis();
	}
	
	public Boolean checkAgain() {
		if ((LastChecked + 1000) > System.currentTimeMillis())
			return false;
		return true;
	}
	
	public void cleanseClicks() {
		
		List<Click> clicks = new ArrayList<Click>(Clicks);
		
		for (Click click : clicks)
			if (click.checkExpire())
				removeClick(click);
		
	}
}
