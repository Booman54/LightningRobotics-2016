package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;

/**
 * @author Rohit
 *A class that will generate and represent a trapezoidal motion profile.
 *If the desired distance is too short for the robot to reach its maximum speed, a triangular profile will be used instead.
 */
public class TrapezoidProfile implements MotionProfile{
	double a,b,c,slope;
	double maxV;
	double maxA;
	double distance;
	boolean trapezoid;
	int i=1; //This is used to invert the profile
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
		double val;
		if(trapezoid){
			if(t<a){
				val=t*maxA;
			}else if(t>b){
				val=maxV-maxA*(t-b);
			}else if(t>c){
				val=0;
			}else{
				val=maxV;
			}
		}else{
			if(t<a){
				val=t*maxA;
			}else if(t>a && t<b){
				val=maxV-t*maxA;
			}else{
				val=0;
			}
		}
		return val*i;
	}
	public void invertProfile(){
		i*=-1;
	}
	
}
