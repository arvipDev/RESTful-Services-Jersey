package com.pluralsight.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;

public class ActivityClient {
	
	private Client client;
	
	public ActivityClient() {
		
		client = ClientBuilder.newClient();
	}
	
	public Activity get(String id){
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		//Activity response = target.path("activities/" + id).request().get(Activity.class);
		//return response;
		Response response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(Response.class);
		if (response.getStatus() != 200){
			throw new RuntimeException(response.getStatus() + ": error");
		}
		return response.readEntity(Activity.class);
	}
	
	public List<Activity> getAll()
	{
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		Response response = target.path("activities/").request(MediaType.APPLICATION_JSON).get(Response.class);
		if(response.getStatus() != 200)	{
			throw new RuntimeException(response.getStatus() + ": error");
		}
		return response.readEntity(new GenericType<List<Activity>>(){});
	}
	
	public User getUser(String id){
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");

		Response response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON).get(Response.class);
		if (response.getStatus() != 200){
			throw new RuntimeException(response.getStatus() + ": error");
		}
		return response.readEntity(User.class);
	}

	public Activity create(Activity activity) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		Response response = target.path("activities/activity")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		if(response.getStatus() != 200)	{
			throw new RuntimeException(response.getStatus() + ": error");
		}
		
		return response.readEntity(Activity.class);
	}

	public Activity update( Activity activity) {
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		Response response = target.path("activities/" + activity.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		return response.readEntity(Activity.class);
	}

}
