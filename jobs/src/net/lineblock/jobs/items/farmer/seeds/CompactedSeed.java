package net.lineblock.jobs.items.farmer.seeds;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.lineblock.jobs.LineJobs;
import net.lineblock.jobs.data.JobsPlayerData;
import net.lineblock.jobs.data.JobsPlayerDataManager;
import net.lineblock.jobs.items.CustomItem;
import net.lineblock.jobs.other.JobsTypes;
import net.lineblock.utils.enums.ColorCode;

public class CompactedSeed extends CustomItem implements Listener {

	private static CompactedSeed instance;
	
	public CompactedSeed() {
		super(Material.WHEAT_SEEDS, ColorCode.GREEN.getChatCode() + "Compacted Seed");
		instance = this;
		LineJobs.getPluginManager().registerEvents(this, LineJobs.getInstance());
	}
	
	@EventHandler
	public void craft(PrepareItemCraftEvent e) {
		boolean is = false;
		for(ItemStack it : e.getInventory().getMatrix())
			if(!it.getItemMeta().getDisplayName().equals(new ItemStack(Material.WHEAT_SEEDS).getItemMeta().getDisplayName()))
				is = false;
		if(e.getInventory().getMatrix().length != 9)
			is = false;
		if(is)
			e.getInventory().setResult(DoubleCompactedSeed.getInstance().getItem());
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e) {
		JobsPlayerData data = JobsPlayerDataManager.getBoxPlayerData(e.getWhoClicked().getUniqueId());
		if(!data.isBlocked())
			data.addJobXp(JobsTypes.FARMER, 5*9);
	}
	
	@EventHandler
	public void onPlant(PlayerInteractEvent e) {
		if(e.getClickedBlock().getType().equals(Material.FARMLAND) && 
				e.getItem().getItemMeta().getDisplayName().contentEquals(this.getItem().getItemMeta().getDisplayName()))
			e.setCancelled(true);
	}
	
	public ItemStack getItem() {return this.build();}
	public static CompactedSeed getInstance() {return instance;}

	@Override
	public double getSellPrice() {
		return 250;
	}

	@Override
	public double getBuyPrice() {
		return 350;
	}

}
