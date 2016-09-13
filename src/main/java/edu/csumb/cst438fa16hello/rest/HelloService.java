package edu.csumb.cst438fa16hello.rest;

import edu.csumb.cst438fa16hello.DayOfWeek;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST service that greets requests.
 *
 * This is a "root resource class" as explained in
 * https://jersey.java.net/documentation/latest/jaxrs-resources.html
 */
@Path("/")
public class HelloService {
	QuestionsAnswers qa;
	
	public HelloService() {
		qa = new QuestionsAnswers();
		
		qa.put("What is the color of the sky?","Blue");
		qa.put("Who is the president of the USA (last name)?","Obama");
		qa.put("What is 1+1?","2");
	}
	
    @GET
    @Path("/today")
    public String today() {
	return DayOfWeek.today();
    }

    @GET
    @Path("/hello")
    public Response hello(@QueryParam("name") String name) {
        if (name == null || name.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok("hello " + name).build();
        }
    }
	
	@GET
	@Path("/randomquestion")
	public Response getRandQuestion() {
		return Response.ok(qa.getRandomQuestion()).build();
	}
	
	@GET
	@Path("/testanswer")
	public Response checkQA(@QueryParam("question") String question, @QueryParam("answer") String answer) {
		return Response.ok(qa.testAnswer(question,answer)).build();
	}
}
