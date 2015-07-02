package org.usfirst.frc.team5763.robot.subsystems.interfaces;

public class RunningAverage {
	double sum=0;
	int num=0;
	public void addDatum(double datum){
		sum+=datum;
		num++;
	}
	public double getAverage(){
		try{
			return sum/num;
		}catch(Exception e){
			return 0;
		}
	}
}
