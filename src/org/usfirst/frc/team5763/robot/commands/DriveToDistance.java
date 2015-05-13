package org.usfirst.frc.team5763.robot.commands;



import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.TrapezoidProfile;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command{
	double distance;
	TrapezoidProfile profile;
	Drivetrain drivetrain;
	Timer timer;
	
	/**
	 * Creates a command to drive the robot straight for a certain distance, using a TMP.
	 * @param distance	the distance to drive
	 */
	public DriveToDistance(double distance){
		this.distance=distance;
		this.drivetrain=drivetrain.getInstance();
		this.profile=new TrapezoidProfile(distance);
	}
	@Override
	protected void initialize() {
		requires(drivetrain);
		timer.start();
	}

	@Override
	protected void execute() {
		double vel=profile.getTargetVelocity(timer.get());
		drivetrain.setVelocity(vel, vel);
	}

	@Override
	protected boolean isFinished() {
		return profile.getTargetVelocity(timer.get())==0 ? true : false; 
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
