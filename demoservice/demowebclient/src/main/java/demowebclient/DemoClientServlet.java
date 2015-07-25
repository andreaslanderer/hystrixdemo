package demowebclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/uuidclient")
public class DemoClientServlet {

	@GET
	public String getUuid() {
		return new UuidCommand().execute();
	}
}
