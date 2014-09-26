package robot;

import lejos.nxt.NXTRegulatedMotor;

public class Engine {
	NXTRegulatedMotor motor;
	boolean inverseMotion;

	public Engine(NXTRegulatedMotor motor, boolean inverseMotion) {
		this.motor = motor;
		this.inverseMotion = inverseMotion;
	}

	public void forward() {
		if(inverseMotion) {
			motor.backward();
		}
		else {
			motor.forward();
		}
	}
	
	public void backward() {
		if(inverseMotion) {
			motor.forward();
		}
		else {
			motor.backward();
		}
	}
	
	public void stop() {
		motor.stop();
	}
	
	public void stop(boolean imReturn) {
		motor.stop(imReturn);
	}
	
	public void setSpeed(float speed) {
		motor.setSpeed(speed);
	}
	
	public void setAcceleration(int accel) {
		motor.setAcceleration(accel);
	}
	
	public void rotate(int degrees, boolean imReturn) {
		motor.rotate(degrees, imReturn);
	}
	
	public boolean getInverseMotion() {
		return inverseMotion;
	}
}
