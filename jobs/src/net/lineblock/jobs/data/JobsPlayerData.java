package net.lineblock.jobs.data;

import java.util.HashMap;
import java.util.UUID;

import net.lineblock.jobs.LineJobs;
import net.lineblock.jobs.other.JobsTypes;

public class JobsPlayerData {

	private UUID uuid;
	
	private HashMap<JobsTypes, Long> JJ = new HashMap<>();
	private boolean blocked;
	
	/**
	 *  <b>Constructeur obsolète, n'utiliser en aucun cas !</b>
	 */
	public JobsPlayerData(UUID uuid) {
		this.uuid = uuid;
		
		load();
	}
	
	private void load() {LineJobs.getJobsTable().fetchData(this);}
	
	public long getJobXp(JobsTypes jt) {return JJ.get(jt);}
	public void setJobXp(JobsTypes jt, long it) {
		if(!JJ.containsKey(jt)) {
			JJ.put(jt, it);
		}else {
			JJ.remove(jt);
			setJobXp(jt, it);
		}
	}
	public void addJobXp(JobsTypes jt, long it) {setJobXp(JobsTypes.FARMER, getJobXp(JobsTypes.FARMER)+(it));}
	public void removeJobXp(JobsTypes jt, long it) {setJobXp(JobsTypes.FARMER, getJobXp(JobsTypes.FARMER)-(it));}
	
	public UUID getUuid() {return uuid;}

	public boolean isBlocked() {return blocked;}
	public void setBlocked(boolean blocked) {this.blocked = blocked;}

}
