package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.Robot;
import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunSDB extends Command{
	Drivetrain drivetrain;
	public RunSDB(){
		drivetrain=Drivetrain.getInstance();
	}
	@Override
	protected void execute() {
		SmartDashboard.putNumber("Drivetrain Utilization", drivetrain.getUtilization());
		SmartDashboard.putNumber("Control Stick Throttle", Robot.oi.controlStick.getThrottle());
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	protected void end() {}
	protected void interrupted() {}
	protected void initialize() {}
}
