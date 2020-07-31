package vendormachine.users;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testReport {

	public static void assertReport(ExtentTest test, Object expected, Object result) {
		if(expected.equals(result)) {
			test.log(LogStatus.PASS, "Comparison passed: " + expected.toString() + ", Result: " + result.toString());
		}else {
			test.log(LogStatus.FAIL, "Comparison passed: " + expected.toString() + ", Result: " + result.toString());
		}
		
	}
	
}
