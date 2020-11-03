package net.lineblock.jobs.api;

import java.util.UUID;

import net.lineblock.jobs.data.JobsPlayerData;
import net.lineblock.jobs.data.JobsPlayerDataManager;

public class LineJobsAPI {
	
	/**
	 * @param uuid => {@link UUID} of the player
	 * @return a unique {@link JobsPlayerData} for the player
	 */
	public final JobsPlayerData getDataForPlayer(UUID uuid) {
		return JobsPlayerDataManager.getBoxPlayerData(uuid);
	}
	/**
	 * @param uuid => {@link String} uuid of the player
	 * @return a unique {@link JobsPlayerData} for the player
	 */
	public final JobsPlayerData getDataForPlayer(String uuid) {
		return getDataForPlayer(UUID.fromString(uuid));
	}
	
}
