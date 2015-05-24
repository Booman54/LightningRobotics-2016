package org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles;

/**
 * @author Rohit
 *A class that all motion profiles compatible with DualMotorProfile need to implement.
 */
public interface MotionProfile {
	public double getTargetVelocity(double t);
}
