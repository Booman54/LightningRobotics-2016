package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team5763.robot.LightningJoystick;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ForceFeedbackJoystick extends LightningJoystick{
	NetworkTable forceTable;
	Map<String,double[]> forces;
	public ForceFeedbackJoystick(int port){
		super(port);
		forces=new HashMap<String,double[]>();
		forceTable=NetworkTable.getTable("FFS");
		forceTable.putNumber("fX", 0);
		forceTable.putNumber("fY", 0);	
	}
	public void addForce(String source,double fX,double fY){
		forces.put(source, new double[] {fX,fY});
		forceOut();
	}
	public void updateForce(String source,double fX,double fY){
		addForce(source,fX,fY);
	}
	public void removeForce(String source){
		forces.remove(source);
	}
	private void forceOut(){
		double fX = 0;
		double fY = 0;
		for(double[] force:forces.values()){
			fX+=force[0];
			fY+=force[1];
		}
		forceTable.putNumber("fX", fX);
		forceTable.putNumber("fY", fY);
	}
}
