package xyz.lightningrobotics.tests;

import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team5763.robot.subsystems.interfaces.profiles.TrapezoidProfile;


public class TrapezoidProfileTest {
	HighPrecisionTimer timer=new HighPrecisionTimer();

	@Test
	public void test() {
		double tar=10-20*Math.random();
		System.out.println("Target: "+tar);
		TrapezoidProfile TPZ=new TrapezoidProfile(tar);
		double t=0;
		double oT=0;
		double distance=0;
		int i=0;
		timer.reset();
		while(!TPZ.isDone(t)){
			i++;
			oT=t;
			t=timer.getElapsedTime();
			distance+=(t-oT)*TPZ.getTargetVelocity(t);
		}
		System.out.println("Iterations: "+ i + " ticks");
		System.out.println("Time To Complete: "+ 1000*timer.getElapsedTime() + "ms");
		System.out.println("Actual: " + distance);
		Assert.assertTrue(TestFunctions.isWithinTolerance(distance, tar, 0.01));
	}

}
