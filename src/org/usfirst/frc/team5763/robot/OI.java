package org.usfirst.frc.team5763.robot;

import org.usfirst.frc.team5763.robot.commands.RecalibrateGyro;
import org.usfirst.frc.team5763.robot.commands.ResetGyro;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.ForceFeedbackJoystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static ForceFeedbackJoystick controlStick;
	JoystickButton recalibrateGyro;
	JoystickButton resetGyro;
	public OI(){
		controlStick=new ForceFeedbackJoystick(RobotMap.controlStick);
		resetGyro = new JoystickButton(controlStick, RobotMap.resetGyroButton);
		resetGyro.whileActive(new ResetGyro());
		recalibrateGyro = new JoystickButton(controlStick, RobotMap.recalibrateGyroButton);
		recalibrateGyro.whileActive(new RecalibrateGyro());
	}
}
