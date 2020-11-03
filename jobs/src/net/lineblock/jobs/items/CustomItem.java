package net.lineblock.jobs.items;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.lineblock.jobs.LineJobs;
import net.lineblock.utils.items.ItemBuilder;

public abstract class CustomItem extends ItemBuilder implements Listener {

	private boolean unmoveable = false;
	
	public CustomItem(ItemStack item) {super(item);register();}
	public CustomItem(Material item) {super(item);register();}
	@SuppressWarnings("deprecation") public CustomItem(ItemBuilder item) {super(item);register();}
	public CustomItem(FileConfiguration cfg, String path) {super(cfg, path);register();}
	public CustomItem(Material mat, int am) {super(mat, am);register();}
	public CustomItem(Material mat, String dis) {super(mat, dis);register();}
	public CustomItem(Material mat, int am, String dis) {super(mat, am, dis);register();}
	
	public boolean isUnmoveable() {return unmoveable;}
	public void setUnmoveable(boolean unmoveable) {this.unmoveable = unmoveable;}
	
	private void register() {LineJobs.getPluginManager().registerEvents(this, LineJobs.getInstance());}
	
	public abstract double getSellPrice();
	public abstract double getBuyPrice();

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if(isUnmoveable())
			e.setCancelled(true);
		switch (e.getClick()) {
		case CONTROL_DROP:
			ControlDrop(e);
			break;
		case CREATIVE:
			Creative(e);
			break;
		case DOUBLE_CLICK:
			DoubleClick(e);
			break;
		case DROP:
			Drop(e);
			break;
		case LEFT:
			InventoryLeftClick(e);
			break;
		case MIDDLE:
			InventoryMiddleClick(e);
			break;
		case NUMBER_KEY:
			NumberKey(e);
			break;
		case RIGHT:
			InventoryRightClick(e);
			break;
		case SHIFT_LEFT:
			ShiftLeft(e);
			break;
		case SHIFT_RIGHT:
			ShiftLeft(e);
			break;
		case UNKNOWN:
			Unknown(e);
			break;
		case WINDOW_BORDER_LEFT:
			WindowBorderLeft(e);
			break;
		case WINDOW_BORDER_RIGHT:
			WindowBorderRight(e);
			break;
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(isUnmoveable())
			e.setCancelled(true);
		switch (e.getAction()) {
		case LEFT_CLICK_AIR:
			LeftClickInteract(e);
			break;
		case LEFT_CLICK_BLOCK:
			LeftClickInteract(e);
			break;
		case PHYSICAL:
			PhysicalClickInteract(e);
			break;
		case RIGHT_CLICK_AIR:
			RightClickInteract(e);
			break;
		case RIGHT_CLICK_BLOCK:
			RightClickInteract(e);
			break;
		}
	}
	
	public void LeftClickInteract(PlayerInteractEvent e) {}
	public void RightClickInteract(PlayerInteractEvent e) {}
	public void PhysicalClickInteract(PlayerInteractEvent e) {}
	
	public void InventoryRightClick(InventoryClickEvent e) {}
	public void InventoryLeftClick(InventoryClickEvent e) {}
	public void InventoryMiddleClick(InventoryClickEvent e) {}
	public void Drop(InventoryClickEvent e) {}
	public void ControlDrop(InventoryClickEvent e) {}
	public void Creative(InventoryClickEvent e) {}
	public void DoubleClick(InventoryClickEvent e) {}
	public void NumberKey(InventoryClickEvent e) {}
	public void ShiftRight(InventoryClickEvent e) {}
	public void ShiftLeft(InventoryClickEvent e) {}
	public void Unknown(InventoryClickEvent e) {}
	public void WindowBorderLeft(InventoryClickEvent e) {}
	public void WindowBorderRight(InventoryClickEvent e) {}
	
}
