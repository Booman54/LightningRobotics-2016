package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

/**
 * @author Rohit
 *A base class to allow two motors to be synchronized in time. 
 */
public class DualMotorProfile {
	MotionProfile leftProfile;
	MotionProfile rightProfile;
	double offset;
	boolean leftFirst;
	protected void buildProfile(MotionProfile leftProfile, MotionProfile rightProfile, double rightOffset){
		this.leftProfile=leftProfile;
		this.rightProfile=rightProfile;
		offset=rightOffset;
		if(offset>0){
			leftFirst=true;
		}else{
			leftFirst=false;
		}
	}
	public double getRightTarget(double time){
		if(leftFirst && time-offset>=0){
			return rightProfile.getTargetVelocity(time-offset);
		}else if (!leftFirst){
			return rightProfile.getTargetVelocity(time);
		}
		return 0;
	}
	public double getLeftTarget(double time){
		if(!leftFirst && time-offset>=0){
			return leftProfile.getTargetVelocity(time-offset);
		}else if (leftFirst){
			return leftProfile.getTargetVelocity(time);
		}
		return 0;
	}
	public boolean isDone(double t){
		return t > 0 && getLeftTarget(t)+getRightTarget(t)==0 ? true : false;
	}
}
