package robot;
import lejos.nxt.*;

public class Robot {

	private static final MotorPort LEFT_MOTOR_PORT = MotorPort.A;
	private static final MotorPort RIGHT_MOTOR_PORT = MotorPort.C;
	private static final MotorPort MIDDLE_MOTOR_PORT = MotorPort.B;
	private static final SensorPort LIGHT_SENSOR_PORT = SensorPort.S4;
	private static final SensorPort MOTION_SENSOR_PORT= SensorPort.S1;

	Engines engines;
	LightSensor lightSensor;
	UltrasonicSensor motionSensor;

	public static void main(String[] args) throws InterruptedException {
		Robot robot = new Robot();
		robot.initRobot();

		//robot.handledning2();
		//robot.handledning3();
		//robot.standardRun();
		robot.complicatedRun();
		//robot.debugRun();
	}

	//--------------------------------------------------------------------

	public void initRobot() {
		engines = new Engines(new Engine(new NXTRegulatedMotor(LEFT_MOTOR_PORT), false), new Engine(new NXTRegulatedMotor(RIGHT_MOTOR_PORT), false), new Engine(new NXTRegulatedMotor(MIDDLE_MOTOR_PORT), false));

		lightSensor = new LightSensor(LIGHT_SENSOR_PORT);

		motionSensor = new UltrasonicSensor(MOTION_SENSOR_PORT);

		System.out.println("Press any button to WIN!.");
		Button.waitForAnyPress();
	}

	public void debugRun() throws InterruptedException {
		int light = 0;
		lightSensor.setFloodlight(true);

		while(true) {
			light = lightSensor.readNormalizedValue();
			System.out.println(light);
			Thread.sleep(1000);
		}
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
			if(lightSensor.readNormalizedValue() < 450) {
				engines.setSpeed(90);
				engines.backward(1000);
			}
			else {
				// get distance to object in sight
				range = motionSensor.getDistance();

				// if no object or too far away, ie off the table, rotate
				if(range > 120) {
					engines.setSpeed(60);
					engines.rotate(true);
				}

				// if object in range go forward
				else if(range < 120) {
					engines.setSpeed(100);
					engines.forward();
				}
			}
		}
	}

	/**
	 * Spins until it finds an enemy then goes forward. If it saw an enemy within the last 0.5 seconds then look both ways for the enemy because it can't be far away.
	 * 
	 * snurra andra hållet mot vad den vad mot när den hittade enemy : 			klar
	 * fortsätt backa under backing efter spin om enemy försvinner: 			klar
	 * 
	 * @throws InterruptedException 
	 */
	public void complicatedRun() throws InterruptedException {
		lightSensor.setFloodlight(true);
		motionSensor.continuous();
		engines.setSpeed(100);

		boolean rotatingClockwise = true;
		boolean rotatedClockwise = false;

		int range = 999;


		SXTimer backingTimer = new SXTimer();

		while(true) {
			// check for black line, black value at around 300. Dark color gives lower value than bright color.
			if(lightSensor.readNormalizedValue() < 500) {
				engines.setSpeed(100);
				engines.backward();
				backingTimer.reset(1000);
			}
			else {
				// get distance to object in sight
				range = motionSensor.getDistance();

				// if object in range go forward
				if(range < 100) {
					engines.setSpeed(100);
					engines.forward();
					backingTimer.pause();
					rotatedClockwise = rotatingClockwise;
				}
				else {
					backingTimer.unpause();
					if(backingTimer.check()) {
						engines.backward();
					}

					// if no object or too far away, ie off the table, and not backing away from black line then rotate
					else {
						rotatingClockwise = !rotatedClockwise;
						engines.rotate(rotatingClockwise);
					}
				}
			}
		}
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