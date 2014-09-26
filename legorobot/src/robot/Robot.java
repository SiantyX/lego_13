package robot;
import lejos.nxt.*;

public class Robot {

	private static final MotorPort LEFT_MOTOR_PORT = MotorPort.A;
	private static final MotorPort RIGHT_MOTOR_PORT = MotorPort.C;
	private static final SensorPort LIGHT_SENSOR_PORT = SensorPort.S1;
	private static final SensorPort MOTION_SENSOR_PORT= SensorPort.S2;

	Engines engines;
	LightSensor lightSensor;
	UltrasonicSensor motionSensor;

	public static void main(String[] args) throws InterruptedException {
		Robot robot = new Robot();
		robot.initRobot();

		//robot.handledning2();
		//robot.handledning3();
		robot.standardRun();
		//robot.complicatedRun();
	}

	//--------------------------------------------------------------------

	public void initRobot() {
		engines = new Engines(new Engine(new NXTRegulatedMotor(LEFT_MOTOR_PORT), false), new Engine(new NXTRegulatedMotor(RIGHT_MOTOR_PORT), false));

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
		engines.setSpeed(60);

		int range = 999;
		while(true) {
			// check for black line, black value at around 300. Dark color gives lower value than bright color.
			if(lightSensor.readNormalizedValue() < 340) {
				engines.rotate(180, false, true);
				engines.forward(1000);
			}
			else {
				// get distance to object in sight
				range = motionSensor.getDistance();

				// if no object or too far away, ie off the table, rotate
				if(range > 120) {
					engines.rotate(360, true, true);
				}

				// if object in range go forward
				else if(range < 120) {
					engines.forward();
				}
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
		engines.setSpeed(30);

		engines.forward();

		int lightVal = 0;
		while(true) {
			lightVal = lightSensor.readNormalizedValue();
			if(lightVal < 340) {
				engines.stop();
				break;
			}
		}
	}

	/**
	 * Moves robot forward, backwards and then spins.
	 * @throws InterruptedException
	 */
	public void handledning2() throws InterruptedException {
		engines.setSpeed(80);

		engines.forward();
		Thread.sleep(2000);
		engines.stop();
		Thread.sleep(1000);
		engines.backward();
		Thread.sleep(2000);
		engines.stop();
		Thread.sleep(1000);
		engines.rotate(360, false, true);
		Thread.sleep(2000);
		engines.stop();
		System.out.println("Test complete.\nPress any button to exit program.");
		Button.waitForAnyPress();
	}
}