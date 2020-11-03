package net.lineblock.math.splitter;

import java.util.ArrayList;

public class SplittedRotation {

	private float yaw;
	private int splitter;
	
	private double yawSplit;
	
	private ArrayList<Float> yawAngles = new ArrayList<Float>();
	
	public SplittedRotation(float yaw, double splitter) {
		this.yaw = yaw;
		
		this.yawSplit = yaw / splitter;
		
		for(int a = 0; a < splitter; a++) {
			yawAngles.add((float) (yawSplit*a));
		}
		
	}

	public float getYaw() {
		return yaw;
	}

	public int getSplitter() {
		return splitter;
	}

	public double getYawSplit() {
		return yawSplit;
	}

	public ArrayList<Float> getYawAngles() {
		return yawAngles;
	}

}
