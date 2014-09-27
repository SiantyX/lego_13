package robot;

import java.util.ArrayList;

import lejos.nxt.Battery;
import lejos.nxt.NXTRegulatedMotor;

public class Engines {
	ArrayList<Engine> motors;

	/**
	 * Add only two engines with left and right wheels
	 * @param left
	 * @param right
	 */
	public Engines(Engine left, Engine right) {
		motors = new ArrayList<Engine>();
		motors.add(left);
		motors.add(right);
	}
	
	/**
	 * Add left, right and middle wheels.
	 * @param left
	 * @param right
	 * @param middle
	 */
	public Engines(Engine left, Engine right, Engine middle) {
		this(left, right);
		motors.add(middle);
	}
	
	public void stop() {
		for(Engine motor : motors) {
			motor.stop(true);
		}
	}
	
	/**
	 * All engines forward
	 */
	public void forward() {
		for(Engine motor : motors) {
			motor.forward();
		}
	}
	
	/**
	 * All engines forward and will sleep for milliseconds
	 * @param milliseconds
	 * @throws InterruptedException
	 */
	public void forward(int milliseconds) throws InterruptedException {
		for(Engine motor : motors) {
			motor.forward();
		}
		
		Thread.sleep(milliseconds);
	}
	
	/**
	 * All engines backwards
	 */
	public void backward() {
		for(Engine motor : motors) {
			motor.backward();
		}
	}
	
	/**
	 * All engines backwards and will sleep for milliseconds
	 * @param milliseconds
	 * @throws InterruptedException
	 */
	public void backward(int milliseconds) throws InterruptedException {
		for(Engine motor : motors) {
			motor.backward();
		}
		
		Thread.sleep(milliseconds);
	}
	
	/**
	 * Set speed 0% to 100% of current voltage
	 * @param speed 0-100
	 */
	public void setSpeed(float speed) {
		if(speed > 100)
			speed = 100;
		else if(speed < 0)
			speed = 0;
		
		float volt = Battery.getVoltage();
		for(Engine motor : motors) {
			motor.setSpeed(volt * speed);
		}
	}
	
	public void setAcceleration(int accel) {
		for(Engine motor : motors) {
			motor.setAcceleration(accel);
		}
	}
	
	/**
	 * Will rotate degrees with or without waiting for full rotation depending on imReturn. Will rotate other way with clockWise.
	 * @param degrees
	 * @param imReturn
	 * @param clockWise
	 */
	public void rotate(int degrees, boolean imReturn, boolean clockWise) {
		if (clockWise) {
			motors.get(0).rotate(degrees*2, true);
			motors.get(1).rotate(-degrees*2, imReturn);
		}
		else {
			motors.get(1).rotate(degrees*2, true);
			motors.get(0).rotate(-degrees*2, imReturn);
		}
		
		if(motors.size() > 2)
			motors.get(2).backward();
	}
	
	public void rotate(boolean clockWise) {
		if (clockWise) {
			motors.get(0).forward();
			motors.get(1).backward();
		}
		else {
			motors.get(0).backward();
			motors.get(1).forward();
		}
		
		if(motors.size() > 2)
			motors.get(2).backward();
	}
}
