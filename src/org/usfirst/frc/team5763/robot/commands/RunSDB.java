package org.usfirst.frc.team5763.robot.commands;

import org.usfirst.frc.team5763.robot.OI;
import org.usfirst.frc.team5763.robot.Robot;
import org.usfirst.frc.team5763.robot.RobotMap;
import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.Battery;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.RobotNavigation;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunSDB extends Command{
	Drivetrain drivetrain;
	PowerDistributionPanel pdp;
	RobotNavigation navi;
	Battery batt;
	
	long index;
	public RunSDB(){
		pdp=new PowerDistributionPanel();
		drivetrain=Drivetrain.getInstance();
		navi=RobotNavigation.getInstance();
		batt=Battery.getInstance();
	}
	@Override
	protected void execute() {
		index++;
		SmartDashboard.putNumber("Drivetrain Utilization", drivetrain.getUtilization());
		SmartDashboard.putNumber("Control Stick Throttle", OI.controlStick.getThrottle());
		SmartDashboard.putNumber("Gyroscope Angle", navi.getYaw());
		SmartDashboard.putNumber("Estimated OCV", batt.getEstimatedOCV());
		if(index%25==0){
			SmartDashboard.putNumber("Power System Temperature", pdp.getTemperature()*1.8+32);
			double mCurrent=0;
			for(int port:RobotMap.motorPorts){
				mCurrent=Math.max(mCurrent, Math.abs(pdp.getCurrent(port)));
			}
			SmartDashboard.putNumber("Top Motor Current", mCurrent);
			SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage());
			SmartDashboard.putNumber("Estimated Battery Capacity", batt.getEstimatedCapacity());
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
