package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener extends TestNGListeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("** Test Started : " +result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("** Test success : " +result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		captureScreenshot(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("** Test skipped : " +result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("** Test completed : " +context.getName());
	}
	
//	public void onTestStart(ITestResult result) {
//		System.out.println("** Test Started : " +result.getName());
//	}
//	public void onTestSuccess(ITestResult result) {
//		System.out.println("** Test success : " +result.getName());
//	}
//	public void onTestFailure(ITestResult result) {
//		System.out.println("** Test failded : " +result.getName());
//	}
//	public void onTestSkipped(ITestResult result) {
//		System.out.println("** Test skipped : " +result.getName());
//	}
//	public void onTestFiledButWithinSuccessPercentage(ITestResult result) {
////		System.out.println("** Test skipped : " +result.getName());
//	}
//	public void onStart(ITestContext context) {
////		System.out.println("** Test Started : " +result.getName());
//	}
//	public void onFinish(ITestContext context) {
////		System.out.println("** Test Started : " +result.getName());
//	}
}