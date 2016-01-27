package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class RobotNavigation {
	AnalogGyro gyro;
	BuiltInAccelerometer accel;
	double angle;
	boolean lockedHeading;
	
	
	public RobotNavigation(){
		gyro=new AnalogGyro(0);
		accel=new BuiltInAccelerometer();
	}
	public double getYaw(){
		if(!lockedHeading){
			angle=gyro.getAngle();
		}
		return angle;
	}
	public double getPitch(){
		double vDist=Math.sqrt(Math.pow(accel.getX(),2)+Math.pow(accel.getY(),2)+Math.pow(accel.getZ(),2));
		double angle=Math.toDegrees(Math.acos(accel.getZ()/vDist));
		return angle;
	}
	public void lockYaw(){
		lockedHeading=true;
	}
	public void unlockYaw(){
		lockedHeading=false;
		gyro.free();
	}

}
