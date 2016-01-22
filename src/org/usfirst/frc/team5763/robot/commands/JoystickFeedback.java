package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.OI;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.ForceFeedbackJoystick;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;


/**
 * @author Rohit Singh
 * A command to update the FF Joystick with accelerometer feedback.
 */
public class JoystickFeedback extends Command{
	String sourceName="accelerometer";
	ForceFeedbackJoystick joystick=OI.controlStick;
	BuiltInAccelerometer accel=new BuiltInAccelerometer(Accelerometer.Range.k2G);
	double aX,aY;
	
	
	
	@Override
	protected void end() {
		joystick.removeForce(sourceName);
	}
	@Override
	protected void execute() {
		double x=accel.getX();
		double y=accel.getY();
		aX=5*aX/6+x/6;
		aY=5*aY/6+y/6;
		joystick.addForce(sourceName,aX,aY);
	}

	@Override
	protected void initialize() {
		joystick.addForce(sourceName,0,0);
	}

	@Override
	protected void interrupted() {
		end();
	}
	protected boolean isFinished() {
		return false;
	}
	
}
