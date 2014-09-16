package robot;
import lejos.nxt.*;

public class Robot {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Press any button to go run test.");
		Button.waitForAnyPress();
		NXTRegulatedMotor engineA = new NXTRegulatedMotor(MotorPort.A);
		NXTRegulatedMotor engineC = new NXTRegulatedMotor(MotorPort.C);
		engineA.forward();
		engineC.forward();
		Thread.sleep(2000);
		engineA.stop();
		engineC.stop();
		Thread.sleep(1000);
		engineA.backward();
		engineC.backward();
		Thread.sleep(2000);
		engineA.stop();
		engineC.stop();
		Thread.sleep(1000);
		engineA.forward();
		engineC.backward();
		Thread.sleep(2000);
		engineA.stop();
		engineC.stop();
		System.out.println("Test complete.\nPress any button to exit program.");
		Button.waitForAnyPress();
		
		//Test 2 
	}
}