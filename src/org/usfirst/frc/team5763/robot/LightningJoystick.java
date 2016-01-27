package org.usfirst.frc.team5763.robot;

import org.usfirst.frc.team5763.robot.subsystems.interfaces.RobotNavigation;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Rohit
 *A joystick that actually isn't brain-dead and won't throw you under the bus with weird outputs.
 */
public class LightningJoystick extends Joystick{
	private double deadzone=.2;
	private RobotNavigation navigation;
	
	public LightningJoystick(int port){
		super(port);
		navigation=RobotNavigation.getInstance();
	}
	private double deadzone(double val){
		if(val < deadzone && val > -deadzone){
			return 0;
		}else{
			return val/Math.abs(val)*((Math.abs(val)-deadzone)/(1-deadzone));
		}
	}
	public double getRealY(){
		return -deadzone(super.getY());
	}
	public double getRealX(){
		return deadzone(super.getX());
	}
	public double getTwist(){
		return deadzone(this.getZ());
	}
	public double getThrottle(){
		return 1-(super.getThrottle()+1)/2;
	}
	public double[] getRotatedVector(){
		double angle=Math.toRadians(navigation.getYaw());
		double x=OI.controlStick.getRealX();
		double y=OI.controlStick.getRealY();
		double xN=x*Math.cos(angle)-y*Math.sin(angle);
		double yN=x*Math.sin(angle)+y*Math.cos(angle);
		xN+=getZ();
		return new double[]{xN,yN};
	}
	
	
	
	public double getCombinedSteer(){
		double steer=getZ()+getX();
		if(steer>1){
			steer=1;
		}else if(steer<-1){
			steer=-1;
		}
		return steer;
	}
}
