package org.usfirst.frc.team5763.robot.subsystems;

import org.usfirst.frc.team5763.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Rohit
 *	The drivetrain class.  This directly handles low-level robot movement, including velocity tracking and 
 */
public class Drivetrain extends Subsystem{
	static Drivetrain instance;
	
	private static double maxV=1;
	private static double maxA=1;

	private double kP=1;
	private double kI=0;
	private double kD=.5;
	private double kF=1;
	
	private double rightMotorBias=1;
	
	Encoder leftEncoder;
	Encoder rightEncoder;
	Talon leftMotor;
	Talon rightMotor;
	PIDController leftControl;
	PIDController rightControl;
	
	private Drivetrain(){
		leftMotor=new Talon(RobotMap.leftMotor);
		rightMotor=new Talon(RobotMap.rightMotor);
		leftEncoder=new Encoder(RobotMap.leftMotorEncoderA,RobotMap.leftMotorEncoderB);
		rightEncoder=new Encoder(RobotMap.rightMotorEncoderA,RobotMap.rightMotorEncoderB);
		leftControl=new PIDController(kP, kI, kD, kF, leftEncoder, leftMotor);
		rightControl=new PIDController(rightMotorBias*kP, rightMotorBias*kI, rightMotorBias*kD, kF, rightEncoder, rightMotor);
		leftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		leftControl.setOutputRange(-1, 1);
		rightControl.setOutputRange(-1, 1);
		leftControl.setInputRange(-maxV, maxV);
		rightControl.setInputRange(-maxV, maxV);
	}
	/**
	 * Gets the current instance of the Drivetrain.  If there is none, creates one.
	 * @return the current drivetrain object
	 */
	public Drivetrain getInstance(){
		if(instance==null){
			instance=new Drivetrain();
		}
		return instance;
	}
	
	/**
	 * Sets the raw velocities of each side.
	 * @param leftVelocity	the desired velocity for the left motor 
	 * @param rightVelocity	the desired velocity for the right motor
	 */
	public void setVelocity(double leftVelocity, double rightVelocity){
		leftControl.setSetpoint(leftVelocity);
		rightControl.setSetpoint(rightVelocity);
	}
	/**
	 * Reboots the motor controllers and stops the robot's movement.
	 */
	public void halt(){
		leftControl.reset();
		rightControl.reset();
		leftControl.setSetpoint(0);
		rightControl.setSetpoint(0);
		setDirect(0,0);
		leftControl.enable();
		rightControl.enable();
	}
	
	
	
	private synchronized void setDirect(int leftPower, int rightPower){
		leftMotor.set(leftPower);
		rightMotor.set(rightPower);
	}
	
	
	
	
	
	
	@Override
	protected void initDefaultCommand() {}	
	/**
	 * Gets the registered maximum velocity of the robot.
	 * @return the maximum velocity of the robot
	 */
	public static double getMaxVel() {
		return maxV;
	}
	/**
	 * Gets the registered maximum acceleration of the robot.
	 * @return the maximum acceleration of the robot
	 */
	public static double getMaxAccel() {
		return maxA;
	}
}
