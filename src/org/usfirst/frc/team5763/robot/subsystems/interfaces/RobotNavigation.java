package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import org.usfirst.frc.team5763.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class RobotNavigation {
	AnalogGyro gyro;
	BuiltInAccelerometer accel;
	double angle;
	boolean lockedHeading;
	static RobotNavigation instance;
	
	public static RobotNavigation getInstance(){
		if(instance==null){
			instance=new RobotNavigation();
		}
		return instance;
	}
	
	private RobotNavigation(){
		gyro=new AnalogGyro(RobotMap.gyroPort);
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
	public void resetGyro(){
		gyro.reset();
		System.out.println("Reset Gyroscope");
	}
	public void recalibrateGyro(){
		gyro.reset();
		gyro.calibrate();
		System.out.println("Reset Gyroscope.  Waiting to complete calibration.");
	}
	public void lockYaw(){
		lockedHeading=true;
	}
	public void unlockYaw(){
		lockedHeading=false;
		gyro.free();
	}

}
