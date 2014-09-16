package robot;

import java.util.ArrayList;

import lejos.nxt.Battery;
import lejos.nxt.NXTRegulatedMotor;

public class Engines {
	ArrayList<NXTRegulatedMotor> motors;

	public Engines(NXTRegulatedMotor a, NXTRegulatedMotor b) {
		motors = new ArrayList<NXTRegulatedMotor>();
		motors.add(a);
		motors.add(b);
	}
	
	public void stop() {
		for(NXTRegulatedMotor motor : motors) {
			motor.stop();
		}
	}
	
	public void forward() {
		for(NXTRegulatedMotor motor : motors) {
			motor.forward();
		}
	}
	
	public void backward() {
		for(NXTRegulatedMotor motor : motors) {
			motor.backward();
		}
	}
	
	/**
	 * @param speed 0-100
	 */
	public void setSpeed(float speed) {
		if(speed > 100)
			speed = 100;
		else if(speed < 0)
			speed = 0;
		
		float volt = Battery.getVoltage();
		for(NXTRegulatedMotor motor : motors) {
			motor.setSpeed(volt * speed);
		}
	}
}
