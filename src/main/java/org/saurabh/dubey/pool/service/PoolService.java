package org.saurabh.dubey.pool.service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.saurabh.dubey.pool.model.Terminal;
import org.springframework.stereotype.Component;

@Component
public class PoolService {
	private  Set<Terminal> available = new CopyOnWriteArraySet<>();
	
	private  Map<Integer,Terminal> inUse = new ConcurrentHashMap<>();
	
	public PoolService() {
		Terminal t1 = new Terminal(1001);
		Terminal t2 = new Terminal(1002);
		Terminal t3 = new Terminal(1003);
		Terminal t4 = new Terminal(1004);
		Terminal t5 = new Terminal(1005);
		available.add(t1);
		available.add(t2);
		available.add(t3);
		available.add(t4);
		available.add(t5);
	}
	
	public Terminal getPoolFromTerminal() {
		synchronized (available) {
			while(available.size()<1) {
				try {
					available.wait();
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}
			Terminal t = available.iterator().next();
			t.updateCounter();
			available.remove(t);
			inUse.put(t.getId(),t);
			return t;
		}
		
	}
	
	public void releasePoolFromTerminal(Integer id) {
		available.add(inUse.get(id));
		inUse.remove(id);
		synchronized (available) {
			available.notifyAll();
		}		
	}
	
}
