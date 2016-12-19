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
import com.pluralsight.search.ActivitySearch;

public class ActivitySearchClient {
	
	private Client client;
	
	public ActivitySearchClient() {
		client = ClientBuilder.newClient();
	}
	
	public List<Activity> search(String param, List<String> searchValues){
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		List<Activity> response = target.path("search/activities")
				.queryParam(param, searchValues)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>(){});	
		
		System.out.println("search: " + response);
		
		return response;
		
		/* URI uri = UriBuilder.fromUri("http://localhost:8080/exercise-services/webapi/")
				.path("search/activities")
				.queryParam(param, searchValues)
				.build();
		
		WebTarget target = client.target(uri);
		
		List<Activity> response = target.request(MediaType.APPLICATION_JSON)
						.get(new GenericType<List<Activity>>(){});
		*/
	}

	public List<Activity> rangeSearch(String param, List<String> searchValues, String secondParam, int durationFrom,
			String thirdParam, int durationTo) {
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		List<Activity> response = target.path("search/activities/range/")
				.queryParam(param, searchValues)
				.queryParam(secondParam, durationFrom)
				.queryParam(thirdParam, durationTo)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>(){});
		
		return response;
	}

	public List<Activity> search(ActivitySearch search) {
		
		WebTarget target = client.target("http://localhost:8080/exercise-services/webapi/");
		
		Response response = target.path("search/activities/")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(search, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200)
		{
			throw new RuntimeException(response.getStatus() + ": error in server");
		
		}
		
		return response.readEntity(new GenericType<List<Activity>>(){});
	}

}
