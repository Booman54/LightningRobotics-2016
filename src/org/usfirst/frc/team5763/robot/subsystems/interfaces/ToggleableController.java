package org.usfirst.frc.team5763.robot.subsystems.interfaces;

import edu.wpi.first.wpilibj.PIDController;

/**
 * @author Rohit
 * A class to allow PID Controllers to be switched on/off, transparently allowing control based on speed. Incomplete.
 */
public class ToggleableController{
	boolean pidMode;
	PIDController control;
	double max;
	double min;
	public ToggleableController(PIDController control){
		this.control=control;
		pidMode=true;
	}
	public ToggleableController(PIDController control, boolean pidMode){
		this.control=control;
		this.pidMode=pidMode;
	}
}
