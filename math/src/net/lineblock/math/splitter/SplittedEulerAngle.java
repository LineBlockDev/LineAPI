package net.lineblock.math.splitter;

import java.util.ArrayList;

import org.bukkit.util.EulerAngle;

public class SplittedEulerAngle {

	private double x;
	private double y;
	private double z;
	
	private double split;
	
	private double xSplit;
	private double ySplit;
	private double zSplit;
	
	private EulerAngle e;
	
	private ArrayList<EulerAngle> Angles = new ArrayList<EulerAngle>();
	
	public SplittedEulerAngle(EulerAngle eA, double split) {
		e = eA;
		this.split = split;
		
		x = eA.getX();
		y = eA.getY();
		z = eA.getZ();
		
		xSplit = x / split;
		ySplit = y / split;
		zSplit = z / split;
		
		for(int i = 1; i <= 3; i++) {
			for(int a = 0; a < split; a++) {
				Angles.add(new EulerAngle(xSplit*a, ySplit*a, zSplit*a));
			}
		}
		
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getSplit() {
		return split;
	}

	public double getxSplit() {
		return xSplit;
	}

	public double getySplit() {
		return ySplit;
	}

	public double getzSplit() {
		return zSplit;
	}

	public EulerAngle getE() {
		return e;
	}

	public ArrayList<EulerAngle> getAngles() {
		return Angles;
	}

}
