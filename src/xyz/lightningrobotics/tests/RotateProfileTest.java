package xyz.lightningrobotics.tests;

import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team5763.robot.RobotMap;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles.RotateProfile;


public class RotateProfileTest {
	HighPrecisionTimer timer=new HighPrecisionTimer();
	@Repeat(times = 10)
	@Test
	public void test() {
		double tar=360-Math.random()*1440;
		System.out.print("Target Angle: "+tar);
		RotateProfile TPZ=new RotateProfile(tar);
		System.out.println(" (true target: "+TPZ.trueAngle+" moving "+ TPZ.debugDir + ")");
		double t=0;
		double oT=0;
		double distanceL=0;
		double distanceR=0;
		double baseRadius = RobotMap.wheelToWheelDistance/2;
		double angle;
		timer.reset();
		while(!TPZ.isDone(t)){
			oT=t;
			t=timer.getElapsedTime();
			distanceL+=(t-oT)*TPZ.getLeftTarget(t);
			distanceR+=(t-oT)*TPZ.getRightTarget(t);
		}
		angle=(Math.toDegrees(-distanceR/baseRadius)+Math.toDegrees(distanceL/baseRadius))/2;
		System.out.println("Time To Complete: "+ timer.getElapsedTime()*1000 + "ms");
		System.out.println("Actual Angle: " + angle);
		Assert.assertTrue(TestFunctions.isWithinTolerance(angle, TPZ.trueAngle, 0.01));
	}

}
