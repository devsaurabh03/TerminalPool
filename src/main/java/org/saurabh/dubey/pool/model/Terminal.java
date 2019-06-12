package org.saurabh.dubey.pool.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Terminal {

	private int id;
	
	private AtomicInteger counter = new AtomicInteger();
	
	public Terminal(int id){
		this.id = id;
		counter.set(0);
	}
	
	public synchronized void updateCounter() {	
		int value =counter.incrementAndGet();
		if(value > 7) {
			counter.set(0);
		}
	}

	public int getId() {
		return id;
	}
	
	public int getCounter() {
		return counter.intValue();
	}
	
}
