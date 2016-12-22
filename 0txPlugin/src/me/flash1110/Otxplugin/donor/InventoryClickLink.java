package me.flash1110.Otxplugin.donor;

public class InventoryClickLink {
	
	private	Integer		SlotClicked;
	private	DInventory	LinkTo;
	
	private	Integer		Extra;
	
	public InventoryClickLink(Integer slot, DInventory inventory) {
		SlotClicked = slot;
		LinkTo = inventory;
		Extra = 0;
	}
	
	public InventoryClickLink(Integer slot, DInventory inventory, int extra) {
		SlotClicked = slot;
		LinkTo = inventory;
		Extra = extra;
	}
	
	public Integer getSlot() {
		return SlotClicked;
	}
		
	public DInventory getInventory() {
		return LinkTo;
	}
	
	public Integer getExtra() {
		return Extra;
	}
	
}
