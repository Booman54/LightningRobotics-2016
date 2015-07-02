package org.usfirst.frc.team5763.robot.subsystems;

import org.usfirst.frc.team5763.robot.Robot;
import org.usfirst.frc.team5763.robot.RobotMap;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.VelocityController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * @author Rohit
 *The drivetrain class.  This directly handles low-level robot movement, including drive-by-velocity and joystick math.
 */
public class Drivetrain extends Subsystem{
	static Drivetrain instance;
	
	private static double maxV=1;
	private static double maxA=1;
	
	private boolean diagnosticMode=false;
	
	Encoder leftEncoder;
	Encoder rightEncoder;
	Talon leftMotor;
	Talon rightMotor;
	VirtualWheel leftWheel;
	VirtualWheel rightWheel;
	
	private Drivetrain(){
		leftMotor=new Talon(RobotMap.leftMotor);
		rightMotor=new Talon(RobotMap.rightMotor);
		leftEncoder=new Encoder(RobotMap.leftMotorEncoderA,RobotMap.leftMotorEncoderB);
		rightEncoder=new Encoder(RobotMap.rightMotorEncoderA,RobotMap.rightMotorEncoderB);
		leftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		leftWheel=new VirtualWheel(leftMotor,leftEncoder);
		rightWheel=new VirtualWheel(rightMotor,rightEncoder);
	}
	/**
	 * Gets the current instance of the Drivetrain.  If there is none, creates one.
	 * @return the current drivetrain object
	 */
	public static Drivetrain getInstance(){
		if(instance==null){
			instance=new Drivetrain();
		}
		return instance;
	}
	/**
	 * Drives the robot using the current joystick status. 
	 */
	public void driveJoystick(){
		double y=Robot.oi.controlStick.getY();
		double x=Robot.oi.controlStick.getCombinedSteer();
		double leftVel=y+x;
		double rightVel=y-x;
		if (leftVel>1){
			leftVel=1;
		}else if(leftVel<-1){
			leftVel=-1;
		}
		if(rightVel>1){
			rightVel=1;
		}else if(rightVel<-1){
			rightVel=-1;
		}
		leftVel*=maxV;
		rightVel*=maxV;
		setVelocity(leftVel, rightVel);
	}
	/**
	 * Sets the raw velocities of each side.
	 * @param leftVelocity	the desired velocity for the left motor 
	 * @param rightVelocity	the desired velocity for the right motor
	 */
	public void setVelocity(double leftVelocity, double rightVelocity){
		leftWheel.setSetpoint(leftVelocity);
		rightWheel.setSetpoint(rightVelocity);
	}
	/**
	 * Stops the robot's movement, overriding any previous movements.
	 */
	public void halt(){
		rightWheel.halt();
		leftWheel.halt();
	}
	@Override
	protected void initDefaultCommand() {}	
	/**
	 * Gets the maximum velocity of the robot.
	 * @return the maximum velocity of the robot
	 */
	public static double getMaxVel() {
		return maxV;
	}
	/**
	 * Gets the maximum acceleration of the robot.
	 * @return the maximum acceleration of the robot
	 */
	public static double getMaxAccel() {
		return maxA;
	}
	public double getUtilization(){
		return (Math.abs(leftMotor.get())+Math.abs(rightMotor.get()))/2;
	}
	public void initTestMode(){
		if(!diagnosticMode){
			//LiveWindow.addActuator("Drivetrain", "Right Wheel", rightWheel);
			//LiveWindow.addActuator("Drivetrain", "Left Wheel", leftWheel);
			diagnosticMode=true;
		}
	}
}

