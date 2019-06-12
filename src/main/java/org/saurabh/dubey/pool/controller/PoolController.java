package org.saurabh.dubey.pool.controller;

import org.saurabh.dubey.pool.model.Terminal;
import org.saurabh.dubey.pool.model.TerminalID;
import org.saurabh.dubey.pool.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoolController {

	@Autowired
	PoolService service;
	
	@RequestMapping(value = "/getTerminalFromPool" ,method = RequestMethod.GET)
	public Terminal getTerminal() {	
		return service.getPoolFromTerminal();
	}
	
	@RequestMapping(value = "/releaseTerminalFromPool", method = RequestMethod.POST)
	public void releaseTerminal(@RequestBody TerminalID id) {
		service.releasePoolFromTerminal(id.getId());
	}
}
