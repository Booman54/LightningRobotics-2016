package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

public class StraightProfile extends DualMotorProfile{
	public StraightProfile(double distance) {
		TrapezoidProfile prof=new TrapezoidProfile(distance);
		buildProfile(prof, prof, 0);
	}
}
