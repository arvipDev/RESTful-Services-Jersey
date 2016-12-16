package com.pluralsight;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

@Path("activities") //http://localhost:8080/exercise-services/webapi/activities
public class ActivityResource 
{
	
	private ActivityRepository activityRepository = new ActivityRepositoryStub();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAllActivities()
	{		
		List<Activity> activities = activityRepository.findAllActivities();
		if(activities == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		GenericEntity<List<Activity>> list = new GenericEntity<List<Activity>>(activities){};
		return Response.ok().entity(list).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}") //http://localhost:8080/exercise-services/webapi/activities/1234
	public Response getActivity(@PathParam ("activityId") String activityId)
	{
		if(activityId == null || activityId.length() < 4 ){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Activity activity = activityRepository.findActivity(activityId);
		
		if(activity == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(activity).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}/user") //http://localhost:8080/exercise-services/webapi/activities/1234/user
	public Response getActivityUser(@PathParam ("activityId") String activityId)
	{
		if(activityId == null || activityId.length() < 4 ){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		User user = activityRepository.findActivity(activityId).getUser();
		
		if(user == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(user).build();
	}
	
	@POST
	@Path("activity") //http://localhost:8080/exercise-services/webapi/activities/activity
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Activity createActivityParams(MultivaluedMap<String, String> formParams)
	{
		System.out.println(formParams.getFirst("description"));
		System.out.println(formParams.getFirst("duration"));
		
		Activity activity = new Activity();
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
		activityRepository.create(activity);
		return activity;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("activity")
	public Activity createActivity(Activity activity)
	{
		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());
		activityRepository.create(activity);
		return activity;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{activityId}")
	public Response update(Activity activity) {
		System.out.println(activity.getId());
		activity = activityRepository.update(activity);
		return Response.ok().entity(activity).build();
	}

}
