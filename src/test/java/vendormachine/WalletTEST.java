package vendormachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import vendormachine.users.Person;
import vendormachine.users.testReport;
import vendormachine.users.util.Wallet;

public class WalletTEST {
	
	// Resources
	private float credit = 10.0f;
	private String brand = "";
	private static Wallet testWallet = new Wallet();
	
	// Keep track of tests
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	
	// Reports
	private static ExtentReports report;
	private ExtentTest test;
	
	@BeforeClass
	public static void testInit() {
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path filePath = Paths.get(root.toString(), "\\target\\reports\\WalletReport.html");
		
		System.out.print(filePath.toString());
		
		report = new ExtentReports(
				filePath.toString(),
				true);
	}
	
	@Before
	public void beforeTest() {
		testWallet.setCredit(credit);
		testWallet.setBrand(brand);
				
		sBuilder.setLength(0);
		sBuilder
		.append("\tTest ").append(activeTest).append("\n")
		.append(div)
		.append("Console:")
		.append("Brand: ").append(testWallet.getBrand()).append("\n")
		.append("Starting Credit: ").append(testWallet.getAllCredit()).append("\n");
				
		System.out.println(sBuilder.toString());
		activeTest++;
	}
	
	@Test
	public void test_Constructor() {
		test = report.startTest("test_Constructor");
		
		// Constructor 1
		Wallet tWallet = new Wallet(credit);
		testReport.assertReport(test, true, tWallet instanceof Wallet);
		assertTrue(tWallet instanceof Wallet);
		
		// Constructor 2 overload
		tWallet = new Wallet(brand, credit);
		testReport.assertReport(test, true, tWallet instanceof Wallet);
		assertTrue(tWallet instanceof Wallet);
	}
	
	@Test
	public void test_setgetCredit() {
		test = report.startTest("test_setgetCredit");
		
		Wallet newW = new Wallet();
		newW.setCredit(credit);
		float aCred = newW.getAllCredit();
		
		testReport.assertReport(test, credit, aCred);
		assertEquals(credit, aCred, 0.1f);
	}
	
	@Test
	public void test_setgetBrand() {
		test = report.startTest("test_setgetBrand");
		Wallet nWallet = new Wallet();
		nWallet.setBrand(brand);
		
		testReport.assertReport(test, brand, nWallet.getBrand());
		assertEquals(brand, nWallet.getBrand());
	}
	
	@Test
	public void test_addCredit() {
		test = report.startTest("test_addCredit");
		Wallet nWallet = new Wallet(brand, credit);
		nWallet.addCredit(5.0f);
		
		
		float result = nWallet.getCredit(15.0f);
		testReport.assertReport(test, 15.0f, result);
		assertEquals(15.0f,result, 0.1f);
	}
	
	@Test
	public void test_insufficentWalletFunds() {
		test = report.startTest("test_insufficentWalletFunds");
		Wallet nWallet = new Wallet(brand, credit);
		
		float result = nWallet.getCredit(20.0f);
		
		testReport.assertReport(test, 0f, result);
		assertEquals(0f, nWallet.getCredit(20.0f), 0.1f);
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
