package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Rohit
 *Drives the drivetrain using the drivetrain's driveJoystick method.
 *
 */
public class DriveByJoystick extends Command{
	Drivetrain drivetrain;
	public DriveByJoystick(){
		drivetrain=Drivetrain.getInstance();
		requires(drivetrain);
	}
	@Override
	protected void initialize() {
		
	}
	@Override
	protected void execute() {
		drivetrain.rawDriveJoystick();
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
		drivetrain.halt();
	}
	@Override
	protected void interrupted() {
		end();
	}

}
