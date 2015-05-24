package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;

/**
 * @author Rohit
 *
 * A class that will generate and represent a trapezoidal motion profile.
 * If the desired distance is too short for the robot to reach its maximum speed, a triangular profile will be used instead.
 */
public class TrapezoidProfile implements MotionProfile{
	double a,b,c,slope;
	double maxV;
	double maxA;
	double distance;
	boolean trapezoid;
	/**
	 * Generates a TMP using the default maximum velocity and acceleration.
	 * @param distance	the distance to drive
	 */
	public TrapezoidProfile(double distance){
		this.distance=distance;
		this.maxV=Drivetrain.getMaxVel();
		this.maxA=Drivetrain.getMaxAccel();
		generateConstants();
	}
	/**
	 * @param distance	the distance to drive
	 * @param maxV	the maximum velocity that the profile will reach
	 * @param maxA	the maximum acceleration the profile will reach
	 */
	public TrapezoidProfile(double distance, double maxV, double maxA){
		this.distance=distance;
		this.maxV=maxV;
		this.maxA=maxA;
		generateConstants();
	}
	private void generateConstants(){
		trapezoid=true;
		a=maxV/maxA;
		b=distance/maxV;
		if(b<a){
			trapezoid=false;
			a=Math.sqrt(distance/maxA);
			b=2*a;
			maxV=distance/maxA;
		}else{
			c=b+a;
		}
	}
	/**
	 * Returns a velocity based on the current time.
	 * @param t	the time relative to the start of the profile
	 * @return the velocity associated with that time
	 */
	public double getTargetVelocity(double t){
		if(trapezoid){
			if(t<a){
				return t*maxA;
			}else if(t>b){
				return maxV-maxA*(t-b);
			}else if(t>c){
				return 0;
			}else{
				return maxV;
			}
		}else{
			if(t<a){
				return t*maxA;
			}else if(t>a && t<b){
				return maxV-t*maxA;
			}else{
				return 0;
			}
		}
	}
	
}
