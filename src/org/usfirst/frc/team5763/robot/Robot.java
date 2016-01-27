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
	
	public static Robot instance;
	
	public static OI oi;
	public Dance autoSequence;
	public DriveByJoystick teleopCommand;
	public RunSDB sdbCommand;
	public JoystickFeedback forceStick;
	
	public Drivetrain drivetrain;
	
	public Robot(){
		instance=this;
		oi=new OI();
		sdbCommand=new RunSDB();
		autoSequence=new Dance();
		teleopCommand=new DriveByJoystick();
		forceStick=new JoystickFeedback();
		
	}
	public void robotInit(){
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
