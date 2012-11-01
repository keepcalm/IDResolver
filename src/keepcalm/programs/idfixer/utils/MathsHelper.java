package keepcalm.programs.idfixer.utils;

public class MathsHelper {
	public static boolean isIntInRange(int origInt, int range, int intToTest) {
		for (int i = 1; i <= range; i++) {
			if (origInt + i == intToTest || origInt - i == intToTest) {
				return true;
			}
		}
		
		return false;
	}
}
