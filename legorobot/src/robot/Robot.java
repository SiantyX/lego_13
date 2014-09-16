package robot;
import lejos.nxt.*;

public class Robot {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Press any button to go run test.");
		Button.waitForAnyPress();
		
		NXTRegulatedMotor engineA = new NXTRegulatedMotor(MotorPort.A);
		NXTRegulatedMotor engineC = new NXTRegulatedMotor(MotorPort.C);
		
		Engines nxtEngines = new Engines(engineA, engineC);
		
		nxtEngines.forward();
		Thread.sleep(2000);
		nxtEngines.stop();
		Thread.sleep(1000);
		nxtEngines.backward();
		Thread.sleep(2000);
		nxtEngines.stop();
		Thread.sleep(1000);
		engineA.rotate(360);
		Thread.sleep(2000);
		nxtEngines.stop();
		System.out.println("Test complete.\nPress any button to exit program.");
		Button.waitForAnyPress();
		
		//Test 2 
	}
}