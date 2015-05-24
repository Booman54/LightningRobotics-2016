package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

public class VelocityController implements PIDOutput, PIDSource{
	
	double kP=.05;
	double kD=kP/2;
	double kI=0;
	
	PIDController pid;
	SpeedController motor;
	Encoder encoder;
	
	double current=0;
	
	public VelocityController(Encoder encoder, SpeedController motor){
		this.motor=motor;
		this.encoder=encoder;
		pid=new PIDController(kP,kI,kD,this,this);
		pid.setOutputRange(-1, 1);
		pid.setPercentTolerance(5);
	}
	public void set(double target){
		if(target==0){
			pid.disable();
			motor.set(0);
		}else{
			pid.setSetpoint(target);
			pid.enable();
		}
	}
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
