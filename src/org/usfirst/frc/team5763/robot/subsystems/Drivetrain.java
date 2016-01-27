package org.usfirst.frc.team5763.robot.subsystems;

import org.usfirst.frc.team5763.robot.OI;
import org.usfirst.frc.team5763.robot.Robot;
import org.usfirst.frc.team5763.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drivetrain class.  This directly handles low-level robot movement, including drive-by-velocity and joystick math.
 * @author Rohit
 */
public class Drivetrain extends Subsystem{
	static Drivetrain instance;
	
	private static double maxV=1;
	private static double maxA=1;
	
	private boolean diagnosticMode=false;
	private boolean adaptive=true;
	
	Encoder leftFrontEncoder;
	Encoder rightFrontEncoder;
	Talon leftFrontMotor;
	Talon rightFrontMotor;
	Talon leftRearMotor;
	Talon rightRearMotor;
	VirtualWheel leftFrontWheel;
	VirtualWheel rightFrontWheel;
	
	
	private Drivetrain(){
		setDefaultCommand(Robot.instance.teleopCommand);
		leftFrontMotor=new Talon(RobotMap.leftFrontMotor);
		rightFrontMotor=new Talon(RobotMap.rightFrontMotor);
		leftRearMotor=new Talon(RobotMap.leftRearMotor);
		rightRearMotor=new Talon(RobotMap.rightRearMotor);
		leftFrontEncoder=new Encoder(RobotMap.leftFrontMotorEncoderA,RobotMap.leftFrontMotorEncoderB);
		rightFrontEncoder=new Encoder(RobotMap.rightFrontMotorEncoderA,RobotMap.rightFrontMotorEncoderB);
		leftFrontEncoder.setPIDSourceType(PIDSourceType.kRate);
		rightFrontEncoder.setPIDSourceType(PIDSourceType.kRate);
		leftFrontWheel=new VirtualWheel(leftFrontMotor,leftFrontEncoder);
		rightFrontWheel=new VirtualWheel(rightFrontMotor,rightFrontEncoder);
		//leftFrontMotor.setInverted(true);
		//leftRearMotor.setInverted(true);
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
		double y=OI.controlStick.getRotatedVector()[0];
		double x=OI.controlStick.getRotatedVector()[1];
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
		
		leftVel*=OI.controlStick.getThrottle();
		rightVel*=OI.controlStick.getThrottle();
		if(adaptive){
			leftVel*=maxV;
			rightVel*=maxV;
			setVelocity(leftVel, rightVel);
		}else{
			setRaw(leftVel,rightVel);
		}
	}

	public void disableAdaptive(){
		leftFrontWheel.disable();
		rightFrontWheel.disable();
		adaptive=false;
	}
	
	public void setRaw(double leftVel,double rightVel){
			leftFrontMotor.set(leftVel);
			leftRearMotor.set(leftVel);
			rightFrontMotor.set(rightVel);
			rightRearMotor.set(rightVel);
	}
	/**
	 * Sets the raw velocities of each side.
	 * @param leftVelocity	the desired velocity for the left motor 
	 * @param rightVelocity	the desired velocity for the right motor
	 */
	public void setVelocity(double leftVelocity, double rightVelocity){
		leftFrontWheel.setSetpoint(leftVelocity);
		rightFrontWheel.setSetpoint(rightVelocity);
	}
	/**
	 * Stops the robot's movement, overriding any previous movements.
	 */
	public void halt(){
		rightFrontWheel.halt();
		leftFrontWheel.halt();
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
		return (Math.abs(leftFrontMotor.get())+Math.abs(rightFrontMotor.get()))/2;
	}
	public void initTestMode(){
		if(!diagnosticMode){
			//LiveWindow.addActuator("Drivetrain", "Right Wheel", rightWheel);
			//LiveWindow.addActuator("Drivetrain", "Left Wheel", leftWheel);
			diagnosticMode=true;
		}
	}
}

