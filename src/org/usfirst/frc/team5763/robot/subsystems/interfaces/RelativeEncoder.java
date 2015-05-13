package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.Encoder;

public class RelativeEncoder {
	private Encoder encoder;
	private int index;
	public RelativeEncoder(Encoder encoder){
		this.encoder=encoder;
	}
	public int get(){
		return encoder.get()-index;
	}
	public void reset(){
		index=encoder.get();
	}
}
