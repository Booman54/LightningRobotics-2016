package org.usfirst.frc.team5763.robot;

import org.usfirst.frc.team5763.robot.subsystems.interfaces.ForceFeedbackJoystick;

public class OI {
	public static ForceFeedbackJoystick controlStick;
	public OI(){
		controlStick=new ForceFeedbackJoystick(RobotMap.controlStick);
	}
}
