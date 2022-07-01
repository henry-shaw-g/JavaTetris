package util;

import java.lang.System;

public class EventTimer {
	private long next;
	private long period;
	
	public EventTimer(double period) {
		this.next = 0;
		this.period = (long)(period * 1000);
	}
	
	public boolean check() {
		long now = System.currentTimeMillis();
		return now > next;
	}
	
	public void reset() {
		next = System.currentTimeMillis() + period;
	}
	
	public static void main(String[] args) throws InterruptedException {
		EventTimer timer = new EventTimer(1.0);
		timer.check();
		Thread.sleep(500);
		timer.check();
	}
}
