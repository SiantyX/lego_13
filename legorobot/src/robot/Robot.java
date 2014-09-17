package robot;
import lejos.nxt.*;

public class Robot {

	private static final MotorPort LEFT_MOTOR_PORT = MotorPort.A;
	private static final MotorPort RIGHT_MOTOR_PORT = MotorPort.C;
	private static final SensorPort LIGHT_SENSOR_PORT = SensorPort.S1;

	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Press any button to go run test.");
		Button.waitForAnyPress();
		
		NXTRegulatedMotor engineA = new NXTRegulatedMotor(LEFT_MOTOR_PORT);
		NXTRegulatedMotor engineC = new NXTRegulatedMotor(RIGHT_MOTOR_PORT);
		
		Engines nxtEngines = new Engines(engineA, engineC);
		
		//handledning 3
		LightSensor lightSensor = new LightSensor(LIGHT_SENSOR_PORT);
		
		nxtEngines.setSpeed(30);
		
		nxtEngines.forward();
		
		int lightVal = 0;
		while(true) {
			lightVal = lightSensor.readValue();
			if(lightVal > 630) {
				nxtEngines.stop();
				break;
			}
		}
		
		// handledning 2
		nxtEngines.setSpeed(80);
		
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