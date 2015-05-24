package org.usfirst.frc.team5763.robot;

public class OI {
	public LightningJoystick controlStick;
	OI(){
		controlStick=new LightningJoystick(RobotMap.controlStick);
	}
}
