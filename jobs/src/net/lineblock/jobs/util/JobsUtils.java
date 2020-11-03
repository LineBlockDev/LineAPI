package net.lineblock.jobs.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public final class JobsUtils {

	public static final int	MIN_LEVEL = 0;
	public static final int MAX_LEVEL = 12;
	
	public static final ItemStack getItemForXp(ItemStack it, long in) {
		List<String> lore = new ArrayList<String>();
		
		int level = xpToLevels(in);
		long min = getAllLevels().get(level).get(0);
		long max = getAllLevels().get(level).get(1);
		long diff = max - (min - 1);
		long percent = diff / 20;
		in -= min;
		boolean finish = false;
		
		char low = '▀';
		char normal = '█';
		char high = '▄';
		char ok = 10003;
		char no = 10008;
		
		if(in >= max)
			finish = true;
		
		String scale = "";
		for(int i = 1; i <= 10; i++) {
			long mn = (percent * i) - (percent / 2) - (i == 1 ? 50 : 0);
			long mx = (percent * i) + (percent / 2) - (i == 20 ? 50 : 0);
			if(!finish) {
				if((percent * i) <= in) {
					scale += "Ã‚Â§c"+low;
				}else if(in >= mn && in <= mx) {
					scale += "Ã‚Â§6"+normal;
				}else if((percent * i) >= in) {
					scale += "Ã‚Â§a"+high;
				}
			}else {
				scale += "Ã‚Â§c"+low;
			}
		}
		lore.add("Ã‚Â§cNiveau: Ã‚Â§b"+level+" Ã‚Â§c"+(finish ? ok : no));
		lore.add(scale);
		it.getItemMeta().setLore(lore);
		it.getItemMeta().setDisplayName(it.getItemMeta().getDisplayName().replaceAll("%LEVEL%", level+": "+(finish ? ok : no)));
		
		return it;
	}
	
	// Ã¢â€“â€žÃ¢â€“â€žÃ¢â€“â€žÃ¢â€“â€žÃ¢â€“Ë†Ã¢â€“â‚¬Ã¢â€“â‚¬Ã¢â€“â‚¬Ã¢â€“â‚¬
	// U+2584 , U+2588 , U+2580
	
	public static final int xpToLevels(long in) {
		ArrayList<ArrayList<Long>> aa = getAllLevels();
		int b = 0;
		
		for (int i = MAX_LEVEL; i >= MIN_LEVEL; i--) {
			if(in >= aa.get(i).get(0) && in <= aa.get(i).get(1)) {
				b = i;
			}
		}
		return b;
	}
	
	public static ArrayList<ArrayList<Long>> getAllLevels() {
		ArrayList<ArrayList<Long>> aa = new ArrayList<ArrayList<Long>>();
		long j = 0;
		
		for (int i = MIN_LEVEL; i <= MAX_LEVEL; i++){
			ArrayList<Long> a = new ArrayList<Long>();
			a.add(j == 0 ? j+0 : j+1);
			j *= 2;
			j = j + 500;
			a.add(j);
			aa.add(a);
		}
		
		return aa;
	}
	
}
