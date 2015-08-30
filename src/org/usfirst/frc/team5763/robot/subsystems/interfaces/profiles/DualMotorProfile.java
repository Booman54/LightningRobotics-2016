package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

/**
 * @author Rohit
 *A base class to allow two motors to be synchronized in time. 
 */
public class DualMotorProfile {
	MotionProfile leftProfile;
	MotionProfile rightProfile;
	protected void buildProfile(MotionProfile leftProfile, MotionProfile rightProfile){
		this.leftProfile=leftProfile;
		this.rightProfile=rightProfile;
	}
	public double getRightTarget(double time){
		return rightProfile.getTargetVelocity(time);
	}
	public double getLeftTarget(double time){
		return leftProfile.getTargetVelocity(time);
	}
	public boolean isDone(double t){
		return leftProfile.isDone(t) && rightProfile.isDone(t) ? true : false;
	}
}
