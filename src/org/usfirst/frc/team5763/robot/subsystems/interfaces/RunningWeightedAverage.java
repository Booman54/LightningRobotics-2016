package org.usfirst.frc.team5763.robot.subsystems.interfaces;

public class RunningWeightedAverage {
	private double weight=0.1;
	double cAvg=0;
	public void addDatum(double datum){
		cAvg=datum*weight+cAvg*(1-weight);
	}
	public double getAverage(){
		return cAvg;
	}
}
