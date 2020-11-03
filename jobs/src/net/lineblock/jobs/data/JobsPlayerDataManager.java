package net.lineblock.jobs.data;

import java.util.HashMap;
import java.util.UUID;

public final class JobsPlayerDataManager {

	private static HashMap<UUID, JobsPlayerData> UB;
	static {UB = new HashMap<UUID, JobsPlayerData>();}
	
	/**
	 *  return <b>BoxPlayerData</b>
	 *  @param player {@link UUID} of the player
	 *  @return unique {@link JobsPlayerData}
	 */
	public static JobsPlayerData getBoxPlayerData(UUID player) {
		JobsPlayerData data;
		try {
			data = UB.get(player);
		}catch(Exception e) {
			data = new JobsPlayerData(player);
			UB.put(data.getUuid(), data);
		}
		return data;
	}

}
