package org.usfirst.frc.team5763.robot.subsystems;

import org.usfirst.frc.team5763.robot.subsystems.interfaces.RelativeEncoder;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.RunningWeightedAverage;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * @author Rohit
 * A class that lets people control each wheel by settings speed.
 */
public class VirtualWheel extends PIDSubsystem{ 
	
	static double kP=.05;
	static double kD=kP/2;
	static double kI=0;
	
	
	
	SpeedController motor;
	Encoder encoder;
	
	RunningWeightedAverage pTuner;
	
	double current=0;

	public VirtualWheel(SpeedController control, Encoder encode) {
		super(kP, kI, kD);
		motor=control;
		getPIDController().setOutputRange(-1, 1);
		getPIDController().setPercentTolerance(1);
		getPIDController().setContinuous(false);
		setSetpoint(0);
		pTuner=new RunningWeightedAverage();
		enable();
	}

	@Override
	protected double returnPIDInput() {
		return encoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(output==0 && getSetpoint()!=0){
			pTuner.addDatum(current/getSetpoint());
			System.out.println("Setting V/E Ratio: "+pTuner.getAverage());
			this.getPIDController().setPID(pTuner.getAverage(), 0 , pTuner.getAverage()/2);
		}
		current+=output;
		motor.set(current);
	}
	public void setSetpoint(double setpoint){
		if(setpoint==0){
			motor.set(0);
			current=0;
		}
		super.setSetpoint(setpoint);
	}
	public RelativeEncoder getRelativeEncoder(){
		return new RelativeEncoder(encoder);
	}
	public void halt(){
		setSetpoint(0);
	}
	public double getDistance(){
		return encoder.getDistance();
	}
	protected void initDefaultCommand() {}
	public void toggleDistanceTracking(){
		if(this.getPIDController().getI()==0){
			this.getPIDController().setPID(pTuner.getAverage(), pTuner.getAverage()/2, pTuner.getAverage()/2);
		}else{
			this.getPIDController().setPID(pTuner.getAverage(), 0, pTuner.getAverage()/2);
		}
	}
}
