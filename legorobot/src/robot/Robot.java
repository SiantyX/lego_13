package robot;
import lejos.nxt.*;

public class Robot {

	private static final MotorPort LEFT_MOTOR_PORT = MotorPort.A;
	private static final MotorPort RIGHT_MOTOR_PORT = MotorPort.C;
	private static final SensorPort LIGHT_SENSOR_PORT = SensorPort.S1;
	private static final SensorPort MOTION_SENSOR_PORT= SensorPort.S2;
	
	public static void main(String[] args) throws InterruptedException {
		
		Robot robot = new Robot();
		robot.initRobot();
		
		//robot.handledning2();
		//robot.handledning3();
		robot.standardRun();
		//robot.complicatedRun();
	}
	
	//--------------------------------------------------------------------
	
	
	NXTRegulatedMotor engineA;
	NXTRegulatedMotor engineC;
	
	Engines nxtEngines;
	
	LightSensor lightSensor;
	
	UltrasonicSensor motionSensor;
	
	
	public void initRobot() {
		engineA = new NXTRegulatedMotor(LEFT_MOTOR_PORT);
		engineC = new NXTRegulatedMotor(RIGHT_MOTOR_PORT);
		
		nxtEngines = new Engines(engineA, engineC);
		
		lightSensor = new LightSensor(LIGHT_SENSOR_PORT);
		
		motionSensor = new UltrasonicSensor(MOTION_SENSOR_PORT);
		
		System.out.println("Press any button to WIN!.");
		Button.waitForAnyPress();
	}
	
	/**
	 * Spin until robot finds a target then go forward and try to push it out.
	 * @throws InterruptedException 
	 */
	public void standardRun() throws InterruptedException {
		lightSensor.setFloodlight(true);
		motionSensor.continuous();
		
		int range = 999;
		while(true) {
			// check for black line
			if(lightSensor.readValue() > 630) {
				engineA.rotate(180, false);
				nxtEngines.forward();
				Thread.sleep(1000);
				continue;
			}
			
			// get distance to object in sight
			range = motionSensor.getDistance();
			
			// if no object or too far away, ie off the table, rotate
			if(range > 120) {
				engineA.rotate(360, true);
			}
			
			// if object in range go forward
			else if(range < 120) {
				nxtEngines.forward();
			}
		}
		
	}
	
	/**
	 * TBA
	 */
	public void complicatedRun() {
		// code here
	}
	
	/**
	 * Moves robot forward until it reaches a black line and then stops.
	 */
	public void handledning3() {
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
	}
	
	/**
	 * Moves robot forward, backwards and then spins.
	 * @throws InterruptedException
	 */
	public void handledning2() throws InterruptedException {
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
	}
}