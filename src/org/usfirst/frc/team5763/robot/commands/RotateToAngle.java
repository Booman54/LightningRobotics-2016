package org.usfirst.frc.team5763.robot.commands;



import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles.RotateProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RotateToAngle extends Command{
	RotateProfile profile;
	Drivetrain drivetrain;
	Timer timer;
	
	/**
	 *Creates a command to rotate the robot to a certain angle, using a RotateProfile.
	 * @param angle	the angle (in degrees) to turn
	 */
	public RotateToAngle(double angle){
		this.drivetrain=Drivetrain.getInstance();
		this.profile=new RotateProfile(angle);
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
