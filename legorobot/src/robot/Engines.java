package robot;

import java.util.ArrayList;

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
}
