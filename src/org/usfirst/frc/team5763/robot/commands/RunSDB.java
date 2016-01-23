package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.OI;
import org.usfirst.frc.team5763.robot.Robot;
import org.usfirst.frc.team5763.robot.RobotMap;
import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunSDB extends Command{
	Drivetrain drivetrain;
	PowerDistributionPanel pdp;
	long index;
	public RunSDB(){
		pdp=new PowerDistributionPanel();
		drivetrain=Drivetrain.getInstance();
	}
	@Override
	protected void execute() {
		index++;
		SmartDashboard.putNumber("Drivetrain Utilization", drivetrain.getUtilization());
		SmartDashboard.putNumber("Control Stick Throttle", OI.controlStick.getThrottle());
		if(index%50==0){
			SmartDashboard.putNumber("Power System Temperature", pdp.getTemperature()*1.8+32);
			double mCurrent=0;
			for(int port:RobotMap.motorPorts){
				mCurrent=Math.max(mCurrent, pdp.getCurrent(port));
			}
			SmartDashboard.putNumber("Top Motor Current", mCurrent);
			SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage());
		}
	}
	@Override
	protected boolean isFinished() {
		return false;
	}
	protected void end() {}
	protected void interrupted() {}
	protected void initialize() {}
}
