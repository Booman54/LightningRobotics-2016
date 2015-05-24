package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

public class StraightProfile extends DualMotorProfile{
	/**
	 * Builds a dual-motor straight profile out of two TMP using a specified distance
	 * @param distance	the distance in the profile
	 */
	public StraightProfile(double distance) {
		TrapezoidProfile prof=new TrapezoidProfile(distance);
		buildProfile(prof, prof, 0);
	}
}
