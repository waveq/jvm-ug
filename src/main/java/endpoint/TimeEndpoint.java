package endpoint;

import threadHandler.Sleeper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TimeEndpoint {

	private static final int MULTIPLIER_TWO = 2;
	private static final int MULTIPLIER_ONE = 1;
	private static final String TIME_REQUIRED = "Time required: %s";

	private static final String FIRST_PATH = "time/{time:[0-9]*}";
	private static final String SECOND_PATH = "time2/{time:[0-9]*}";
	private static final String TIME = "time";

	@Path(FIRST_PATH)
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUrl(@PathParam(TIME) String time) throws InterruptedException {
		int response = Sleeper.sleepForParamTime(time, MULTIPLIER_ONE);
		return String.format(TIME_REQUIRED, response);
	}

	@Path(SECOND_PATH)
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUrlMultiplied(@PathParam(TIME) String time) throws InterruptedException {
		int response = Sleeper.sleepForParamTime(time, MULTIPLIER_TWO);
		return String.format(TIME_REQUIRED, response);
	}
}
