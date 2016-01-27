package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.subsystems.interfaces.RobotNavigation;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Rohit
 *Drives the drivetrain using the drivetrain's driveJoystick method.
 *
 */
public class ResetGyro extends Command{
	RobotNavigation navi;
	public ResetGyro(){
		navi=RobotNavigation.getInstance();
	}
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		navi.resetGyro();
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {}
	@Override
	protected void interrupted() {}

}
