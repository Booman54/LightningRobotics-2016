package org.usfirst.frc.team5763.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // Hardware ports
    public static final int leftMotor = 0; //PWM Port for the left motors
    public static final int rightMotor = 1; //PWM Port for the right motors
    public static final int leftMotorEncoderA = 0; //DIO Port A for the left motors
    public static final int leftMotorEncoderB = 1; //DIO Port B for the left motors
    public static final int rightMotorEncoderA = 2; //DIO Port A for the right motors
    public static final int rightMotorEncoderB = 3; //DIO Port B for the right motors
    public static final int pneumaticControlModule=0; //CAN Bus ID for the PCM
    // OI information
    public static final int controlStick = 0; //Joystick number

    // Hardware constants (using US Customary)
    public static final double wheelRadius=2; //The radius of the wheel.  The units/second is the reported velocity.
    public static final double wheelToWheelDistance=30; //The wheel-to-wheel distance.
    public static final int encoderTicksPerRevolution=1000; //The number of ticks the encoder will read in a full revolution.
    public static final int schedulerTicksPerSecond=50; //The number of times a command executes per second.
}

