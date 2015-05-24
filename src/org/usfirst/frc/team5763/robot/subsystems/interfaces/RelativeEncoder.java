package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.Encoder;

/**
 * @author Rohit
 *An encoder that can be reset independently of all other encoders.
 */
public class RelativeEncoder {
	private Encoder encoder;
	private int index;
	/**
	 * Creates a RelativeEncoder with an Encoder, starting at zero.
	 * @param encoder	the Encoder to use
	 */
	public RelativeEncoder(Encoder encoder){
		this.encoder=encoder;
		index=encoder.get();
	}
	public int get(){
		return encoder.get()-index;
	}
	/**
	 * Sets the encoder's index to zero.
	 */
	public void reset(){
		index=encoder.get();
	}
}
