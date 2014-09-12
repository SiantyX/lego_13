package robot;
import lejos.nxt.*;

public class Robot {

	public static void main(String[] args) {
		System.out.println("Test.\nPress any button to go forward.");
		Button.waitForAnyPress();
		NXTRegulatedMotor engineA = new NXTRegulatedMotor(MotorPort.A);
		NXTRegulatedMotor engineC = new NXTRegulatedMotor(MotorPort.C);
		engineA.forward();
		engineC.forward();
		Button.waitForAnyPress();
	}
}