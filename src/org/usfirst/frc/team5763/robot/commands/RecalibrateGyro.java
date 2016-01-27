package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.RobotNavigation;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Rohit
 *Drives the drivetrain using the drivetrain's driveJoystick method.
 *
 */
public class RecalibrateGyro extends Command{
	RobotNavigation navi;
	Drivetrain drivetrain;
	boolean started;
	public RecalibrateGyro(){
		navi=RobotNavigation.getInstance();
		drivetrain=Drivetrain.getInstance();
		requires(drivetrain);
	}
	@Override
	protected void initialize() {}
	@Override
	protected void execute() {
		if(!started){
			navi.recalibrateGyro();
		}
		started=true;
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
		started=false;
	}
	@Override
	protected void interrupted() {
		end();
	}

}
