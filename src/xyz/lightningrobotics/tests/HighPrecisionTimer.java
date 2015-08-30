package xyz.lightningrobotics.tests;

public class HighPrecisionTimer {
	long index;
	public void reset(){
		index=System.nanoTime();
	}
	public double getElapsedTime(){
		return (double)(System.nanoTime()-index)/1000000000;
	}
}
