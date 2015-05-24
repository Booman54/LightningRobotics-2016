package org.usfirst.frc.team5763.robot;

import org.usfirst.frc.team5763.robot.commands.Dance;
import org.usfirst.frc.team5763.robot.commands.DriveByJoystick;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot{
	public static OI oi;
	public Dance autoSequence=new Dance();
	public DriveByJoystick teleopCommand=new DriveByJoystick();
	
	public void robotInit(){
		oi=new OI();
	}
	public void autonomousInit(){
		teleopCommand.cancel();
		autoSequence.start();		
	}
	public void teleopInit(){
		autoSequence.cancel();
		teleopCommand.start();
	}
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
	}
	public void disabledInit(){
        Scheduler.getInstance().removeAll();
    }
}
