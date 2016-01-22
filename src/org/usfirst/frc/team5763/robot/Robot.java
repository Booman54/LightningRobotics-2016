package org.usfirst.frc.team5763.robot;

import org.usfirst.frc.team5763.robot.commands.Dance;
import org.usfirst.frc.team5763.robot.commands.DriveByJoystick;
import org.usfirst.frc.team5763.robot.commands.JoystickFeedback;
import org.usfirst.frc.team5763.robot.commands.RunSDB;
import org.usfirst.frc.team5763.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot{
	public static OI oi;
	public Dance autoSequence=new Dance();
	public DriveByJoystick teleopCommand=new DriveByJoystick();
	public RunSDB sdbCommand=new RunSDB();
	public JoystickFeedback forceStick=new JoystickFeedback();
	
	public Drivetrain drivetrain;
	
	
	public void robotInit(){
		oi=new OI();
		drivetrain=Drivetrain.getInstance();
		
	}
	public void autonomousInit(){
		teleopCommand.cancel();
		forceStick.cancel();
		autoSequence.start();
		sdbCommand.start();
	}
	public void teleopInit(){
		autoSequence.cancel();
		teleopCommand.start();
		forceStick.start();
		sdbCommand.start();
		
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
	public void testInit(){
		drivetrain.initTestMode();
	}
    public void testPeriodic() {
        LiveWindow.run();
    }
}
