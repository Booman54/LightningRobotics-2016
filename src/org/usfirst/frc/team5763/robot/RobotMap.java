package org.usfirst.frc.team5763.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // Hardware data output ports
    public static final int leftFrontMotor = 0; //PWM Port for the left motors
    public static final int leftRearMotor = 1; //PWM Port for the left motors
    public static final int rightFrontMotor = 2; //PWM Port for the right motors
    public static final int rightRearMotor = 3; //PWM Port for the right motors
    public static final int pneumaticControlModule=0; //CAN Bus ID for the PCM

    // Motor Encoder Ports
    public static final int leftFrontMotorEncoderA = 0; //DIO Port A for the left motors
    public static final int leftFrontMotorEncoderB = 1; //DIO Port B for the left motors
    public static final int rightFrontMotorEncoderA = 2; //DIO Port A for the right motors
    public static final int rightFrontMotorEncoderB = 3; //DIO Port B for the right motors
    public static final int leftRearMotorEncoderA = 4; //DIO Port A for the left motors
    public static final int leftRearMotorEncoderB = 5; //DIO Port B for the left motors
    public static final int rightRearMotorEncoderA = 6; //DIO Port A for the right motors
    public static final int rightRearMotorEncoderB = 7; //DIO Port B for the right motors
    
    
    // Hardware data input ports
    public static final int[] motorPorts = new int[]{1,2,3,4,12,13,14,15};
    public static final int gyroPort = 0;
    public static final int loadSwitch = 8;
    
    // OI information
    public static final int controlStick = 0; //Joystick number
    public static final int resetGyroButton = 11; //Button to reset gyroscope
    public static final int recalibrateGyroButton = 12; //Button to calibrate gyroscope (long reset)

    // Hardware constants (using SI/ISU)
    public static final double wheelRadius=0.0508; //The radius of the wheel.  Meters is preferred here because metric is cooler.
    public static final double wheelToWheelDistance=.50165; //The wheel-to-wheel distance.  This number is currently inexact.
    public static final int encoderTicksPerRevolution=1000; //The number of ticks the encoder will read in a full revolution.
    public static final int schedulerTicksPerSecond=50; //The number of times a command executes per second.
}

