package org.usfirst.frc.team5763.robot.subsystems;

import org.usfirst.frc.team5763.robot.Robot;
import org.usfirst.frc.team5763.robot.RobotMap;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.VelocityController;

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

	
	Encoder leftEncoder;
	Encoder rightEncoder;
	Talon leftMotor;
	Talon rightMotor;
	VelocityController leftControl;
	VelocityController rightControl;
	
	private Drivetrain(){
		leftMotor=new Talon(RobotMap.leftMotor);
		rightMotor=new Talon(RobotMap.rightMotor);
		leftEncoder=new Encoder(RobotMap.leftMotorEncoderA,RobotMap.leftMotorEncoderB);
		rightEncoder=new Encoder(RobotMap.rightMotorEncoderA,RobotMap.rightMotorEncoderB);
		leftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		rightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
		leftControl=new VelocityController(leftEncoder,leftMotor);
		rightControl=new VelocityController(rightEncoder,rightMotor);
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
		leftControl.set(leftVelocity);
		rightControl.set(rightVelocity);
	}
	/**
	 * Reboots the motor controllers and stops the robot's movement.
	 */
	public void halt(){
		rightControl.halt();
		leftControl.halt();
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
