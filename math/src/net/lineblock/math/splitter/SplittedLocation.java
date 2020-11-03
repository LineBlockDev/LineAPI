package net.lineblock.math.splitter;

import java.util.ArrayList;

import org.bukkit.Location;

public class SplittedLocation {

	private Location a;
	private Location b;
	
	private double split;
	
	private double xSplit;
	private double ySplit;
	private double zSplit;
	
	private ArrayList<Location> Locations = new ArrayList<Location>();
	
	public SplittedLocation(Location locA, Location locB, double split) {
		a = locA;
		b = locB;
		this.split = split;
		
		xSplit = (locA.getX()-locB.getBlockX()) / split;
		ySplit = (locA.getY()-locB.getBlockY()) / split;
		zSplit = (locA.getZ()-locB.getBlockZ()) / split;
		
		for(int i = 1; i <= 3; i++) {
			for(int a = 0; a < split; a++) {
				Locations.add(new Location(locA.getWorld(), locA.getX()+xSplit*a, locA.getY()+ySplit*a, locA.getZ()+zSplit*a));
			}
		}
		
	}

	public Location getA() {
		return a;
	}
	public Location getB() {
		return b;
	}
	public double getSplit() {
		return split;
	}
	public ArrayList<Location> getLocations() {
		return Locations;
	}

}
