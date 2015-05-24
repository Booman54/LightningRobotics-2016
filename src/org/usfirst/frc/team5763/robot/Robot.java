package org.usfirst.frc.team5763.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot{
	public static OI oi;
	public void robotInit(){
		oi=new OI();
	}
	public void autonomousInit(){
		
	}
	public void teleopInit(){
		
	}
	
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
	}
}
