package org.usfirst.frc.team5763.robot.commands;



import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.StraightProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command{
	double distance;
	StraightProfile profile;
	Drivetrain drivetrain;
	Timer timer;
	
	/**
	 * Creates a command to drive the robot straight for a certain distance, using a TMP.
	 * @param distance	the distance to drive
	 */
	public DriveToDistance(double distance){
		this.distance=distance;
		this.drivetrain=drivetrain.getInstance();
		this.profile=new StraightProfile(distance);
	}
	@Override
	protected void initialize() {
		requires(drivetrain);
		timer.start();
	}

	@Override
	protected void execute() {
		drivetrain.setVelocity(profile.getLeftTarget(timer.get()), profile.getRightTarget(timer.get()));
	}

	@Override
	protected boolean isFinished() {
		return profile.isDone(timer.get()) ? true : false; 
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
