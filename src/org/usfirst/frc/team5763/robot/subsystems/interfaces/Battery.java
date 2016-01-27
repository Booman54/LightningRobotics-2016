package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Battery {
	static Battery instance;
	PowerDistributionPanel pdp;
	
	public static Battery getInstance(){
		if(instance==null){
			instance=new Battery();
		}
		return instance;
	}
	
	
	private Battery(){
		pdp=new PowerDistributionPanel();
	}
	public double getEstimatedOCV(){
		return pdp.getVoltage()+0.0189*pdp.getTotalCurrent();
	}
	public double getEstimatedCapacity(){
		return (getEstimatedOCV()-10.5)/2.5;
	}
	
}
