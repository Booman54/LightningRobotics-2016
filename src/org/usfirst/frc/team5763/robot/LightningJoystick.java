package org.usfirst.frc.team5763.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Rohit
 *A joystick that actually isn't brain-dead and won't throw you under the bus with weird outputs.
 */
public class LightningJoystick {
	private Joystick joystick;
	public LightningJoystick(int port){
		joystick=new Joystick(port);
	}	
	public double getY(){
		return -joystick.getY();
	}
	public double getX(){
		return joystick.getX();
	}
	public double getTwist(){
		return joystick.getZ();
	}
	public double getThrottle(){
		return 1-(joystick.getThrottle()+1)/2;
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
