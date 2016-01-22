package org.usfirst.frc.team5763.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Rohit
 *A joystick that actually isn't brain-dead and won't throw you under the bus with weird outputs.
 */
public class LightningJoystick extends Joystick{
	public LightningJoystick(int port){
		super(port);
	}	
	public double getRealY(){
		return -super.getY();
	}
	public double getRealX(){
		return super.getX();
	}
	public double getTwist(){
		return this.getZ();
	}
	public double getThrottle(){
		return 1-(this.getThrottle()+1)/2;
	}
	public double getCombinedSteer(){
		double steer=getTwist()+getThrottle();
		if(steer>1){
			steer=1;
		}else if(steer<-1){
			steer=-1;
		}
		return steer;
	}
}
