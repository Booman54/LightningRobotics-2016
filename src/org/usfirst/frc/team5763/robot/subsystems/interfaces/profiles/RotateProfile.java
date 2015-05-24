package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

import org.usfirst.frc.team5763.robot.RobotMap;

public class RotateProfile extends DualMotorProfile{
	/**
	 * Builds a Dual Motor Profile to rotate the robot given an angle in degrees
	 * @param angle	the angle (in degrees) to rotate
	 */
	public RotateProfile(double angle){
		angle=Math.toRadians(angle);
		double inv=angle<0 ? -1 : 1;
		TrapezoidProfile leftProfile=new TrapezoidProfile(angle*RobotMap.wheelToWheelDistance);
		TrapezoidProfile rightProfile=new TrapezoidProfile(angle*RobotMap.wheelToWheelDistance);
		if(angle<0){
			leftProfile.invertProfile();
		}else{
			rightProfile.invertProfile();
		}
		buildProfile(leftProfile,rightProfile,0);
	}
}
