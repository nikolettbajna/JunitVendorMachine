package vendormachine.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import vendormachine.users.util.Wallet;

// Example 100% Person Test Coverage
// add @Ignore above class to help WalletTest.java
public class PersonTEST {
	
	// Resources
	private float credit = 10.0f;
	private static Wallet testWallet = new Wallet();
	
	// Keep track of tests
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	
	// Reports
	private static ExtentReports report;
	private ExtentTest test;
	
	
	@BeforeClass
	public static void testINITIAL() {
		
		report = new ExtentReports(
				"C:\\Users\\MorickClive\\Desktop\\QA\\TRAINER\\Content Generation\\Demo Projects\\vendormachine\\target\\reports\\PersonReport.html",
				true);
				
	}
	
	
	@Before
	public void beforeTest() {
		
		// Set Default test Wallet credit
		testWallet.setCredit(credit);
		
		// Start of console test format
		sBuilder.setLength(0);
		sBuilder
		.append("\tTest ").append(activeTest).append("\n")
		.append(div)
		.append("Console:")
		.append("Starting Credit: ").append(testWallet.getAllCredit()).append("\n");
		
		System.out.println(sBuilder.toString());
		activeTest++;
		// End of console test format
	}
	
	@Test
	public void test_Constructor() {
		test = report.startTest("test_Constructor");
		
		// Constructor 1
		Person allan = new Person("Allan");
		testReport.assertReport(test, true, allan instanceof Person);
		assertTrue(allan instanceof Person);
		
		// Constructor 2 overload
		allan = new Person("Allan", testWallet);
		testReport.assertReport(test, true, allan instanceof Person);
		assertTrue(allan instanceof Person);
	}
	
	@Test
	public void test_setgetName() {
		test = report.startTest("test_setgetName");
		
		Person alan = new Person("Allan");
		alan.setName("Alan");
		
		testReport.assertReport(test, "Alan", alan.getName());
		assertEquals("Alan", alan.getName());
	}
	
	@Test
	public void test_setgetWallet() {
		test = report.startTest("test_setgetWallet");
		Person alan = new Person("Allan");
		
		alan.setWallet(testWallet);
		
		testReport.assertReport(test, true, alan.getWallet() instanceof Wallet);
		assertTrue(alan.getWallet() instanceof Wallet);
	}
	
	@Test
	public void test_getCredit() {
		test = report.startTest("test_getCredit");
		Person alyx = new Person("Alyx", testWallet);
		float result = alyx.getCredit(5.0f);
		
		testReport.assertReport(test, 5.0f, result);
		assertEquals(5.0f, result, 0.1f);
	}
	
	@Test
	public void test_addCredit() {
		test = report.startTest("test_addCredit");
		Person alyx = new Person("Alyx", testWallet);
		alyx.addCredit(5.0f);
		
		
		float result = alyx.getCredit(15.0f);
		testReport.assertReport(test, 15.0f, result);
		assertEquals(15.0f,result, 0.1f);
	}
	
	@Test
	public void test_noWallet() {
		test = report.startTest("test_noWallet");
		Person alyx = new Person("Alyx");
		
		alyx.addCredit(10.0f);
		
		
		float resultCredit = alyx.getCredit(5.0f);
		
		testReport.assertReport(test, true, alyx.getWallet() == null);
		testReport.assertReport(test, 0f, resultCredit);
		
		assertTrue(alyx.getWallet() == null);
		assertEquals(0, resultCredit, 0.1f);
	}
	
	@Test
	public void test_insufficentWalletFunds() {
		test = report.startTest("test_insufficentWalletFunds");
		Person alyx = new Person("Alyx", testWallet);
		
		float result = alyx.getCredit(20.0f);
		
		testReport.assertReport(test, 0f, result);
		assertEquals(0f, alyx.getCredit(20.0f), 0.1f);
	}
	
	@After
	public void afterTest() {
		System.out.println(div);
		report.endTest(test);
	}
	
	@AfterClass
	public static void tearDown() {		
		report.flush();
		report.close();
	}

}
