package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

import org.usfirst.frc.team5763.robot.RobotMap;

public class RotateProfile extends DualMotorProfile{
	public double trueAngle=0;
	public String debugDir;
	/**
	 * Builds a Dual Motor Profile to rotate the robot given an angle in degrees
	 * @param angle	the angle (in degrees) to rotate
	 */
	public RotateProfile(double angle){
		angle%=360;
		if(angle>180){
			angle+=-360;
		}else if(angle<-180){
			angle+=360;
		}
		trueAngle=angle;
		angle=Math.toRadians(angle);
		TrapezoidProfile leftProfile=new TrapezoidProfile(angle*RobotMap.wheelToWheelDistance/2);
		TrapezoidProfile rightProfile=new TrapezoidProfile(-angle*RobotMap.wheelToWheelDistance/2);
		if(leftProfile.i<0){
			debugDir="CCW";
		}else{
			debugDir="CW";
		}
		buildProfile(leftProfile,rightProfile);
	}
}
