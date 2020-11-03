package net.lineblock.data.data.base;

import java.util.List;

import net.lineblock.data.data.table.ModerationTable;
import net.lineblock.data.data.table.Table;

public class LineBase extends Base {

	private ModerationTable users;
	
	public LineBase() {
		super("LineDB");
	}
	
	public List<Table> initTables(List<Table> empty) {
		empty.add(this.users = new ModerationTable(this));
		return empty;
	}
	
	public ModerationTable moderationTable() {
		return this.users;
	}
	
}
