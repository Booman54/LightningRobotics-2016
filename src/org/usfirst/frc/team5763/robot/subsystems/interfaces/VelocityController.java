package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * @author Rohit
 *A class that allows for controlling a motor based on a desired velocity, given an encoder and SpeedController.
 *The motor response is not instantaneous.
 *
 *This class is sort of worthless now.
 */
public class VelocityController implements PIDOutput, PIDSource{
	
	double kP=.05;
	double kD=kP/2;
	double kI=0;
	
	PIDController pid;
	SpeedController motor;
	Encoder encoder;
	
	double current=0;
	
	/**
	 * Creates a VelocityController using the encoder and motor given.
	 * @param encoder	the encoder that will be used for closed-loop feedback
	 * @param motor	the motor that will be spun to the target speed
	 */
	public VelocityController(Encoder encoder, SpeedController motor){
		this.motor=motor;
		this.encoder=encoder;
		pid=new PIDController(kP,kI,kD,this,this);
		pid.setOutputRange(-1, 1);
		pid.setPercentTolerance(1);
	}
	/**
	 * Gets the integrated PID Controller that the VelocityController is tapped into for tuning.
	 * @return	the integrated PID Controller
	 */
	public PIDController getRootController(){
		return pid;
	}
	/**
	 * Changes the target speed for the controller to reach.
	 * @param target	the desired motor speed
	 */
	public void set(double target){
		if(target==0){
			pid.disable();
			motor.set(0);
		}else{
			pid.setSetpoint(target);
			pid.enable();
		}
	}
	/**
	 * Immediately halts the motor and resets the controller.
	 */
	public void halt(){
		pid.reset();
		motor.set(0);
		pid.setSetpoint(0);
	}
	@Override
	public void pidWrite(double output) {
		current+=output;
		motor.set(output);
	}
	@Override
	public double pidGet() {
		return encoder.getRate();
	}

}
