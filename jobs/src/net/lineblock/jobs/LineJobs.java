package net.lineblock.jobs;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.lineblock.data.data.DataManager;
import net.lineblock.jobs.api.LineJobsAPI;
import net.lineblock.jobs.data.JobsTable;
import net.lineblock.jobs.items.farmer.dandelion.CompactedDandelion;
import net.lineblock.jobs.items.farmer.dandelion.DoubleCompactedDandelion;
import net.lineblock.jobs.items.farmer.dandelion.TripleCompactedDandelion;
import net.lineblock.jobs.items.farmer.seeds.CompactedSeed;
import net.lineblock.jobs.items.farmer.seeds.DoubleCompactedSeed;
import net.lineblock.jobs.items.farmer.seeds.TripleCompactedSeed;
import net.lineblock.jobs.listener.JobsListener;

/**
 * @author Poucy113
 * @contributor LineDev
 * @version 1.0
 */
public class LineJobs extends JavaPlugin {

	private static LineJobs instance;
	private static JobsTable jobsTable;
	private static LineJobsAPI api;
	
	private static PluginManager pm;
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		
		jobsTable = new JobsTable(DataManager.getInstance().getLineBase());
		api = new LineJobsAPI();
		pm = getServer().getPluginManager();
		
		items();
		
		getPluginManager().registerEvents(new JobsListener(), this);
	}
	
	private void items() {
		new CompactedSeed();
		new DoubleCompactedSeed();
		new TripleCompactedSeed();
		
		new CompactedDandelion();
		new DoubleCompactedDandelion();
		new TripleCompactedDandelion();
	}
	
	public static LineJobs getInstance() {return instance;}
	public static JobsTable getJobsTable() {return jobsTable;}
	public static LineJobsAPI getLineJobsAPI() {return api;}
	public static PluginManager getPluginManager() {return pm;}
	
}
