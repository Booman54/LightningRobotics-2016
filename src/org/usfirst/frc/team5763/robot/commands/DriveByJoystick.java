package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveByJoystick extends Command{
	Drivetrain drivetrain;
	public DriveByJoystick(){
		drivetrain=Drivetrain.getInstance();
	}
	@Override
	protected void initialize() {
		requires(drivetrain);
	}
	@Override
	protected void execute() {
		drivetrain.driveJoystick();
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
