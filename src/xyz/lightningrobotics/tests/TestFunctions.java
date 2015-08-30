package xyz.lightningrobotics.tests;

public class TestFunctions {
	public static boolean isWithinTolerance(double value, double target, double tolerance){
		double highBound=tolerance+1;
		double lowBound=1-tolerance;
		if(target/Math.abs(target)!=value/Math.abs(value)){
			return false;
		}else{
			target=Math.abs(target);
			value=Math.abs(value);
		}
		return value > lowBound*target && value < highBound*target;
	}

}
