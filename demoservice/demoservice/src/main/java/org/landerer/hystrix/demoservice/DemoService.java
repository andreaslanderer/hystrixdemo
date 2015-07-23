package org.landerer.hystrix.demoservice;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/uuid")
public class DemoService {

	@GET
	public String getUuid() {
		return UUID.randomUUID().toString();
	}
}
