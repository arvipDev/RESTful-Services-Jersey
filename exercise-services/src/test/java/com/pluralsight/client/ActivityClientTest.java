package com.pluralsight.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;

public class ActivityClientTest {
	
	@Test
	public void testDelete(){
		ActivityClient client = new ActivityClient();
		client.delete("1234");
		
	}
	
	@Test
	public void testPut() {
		Activity activity = new Activity();
		activity.setDescription("Yoga");
		activity.setDuration(55);
		activity.setId("3456");
		
		ActivityClient client = new ActivityClient();
		activity = client.update(activity);
		assertNotNull(activity);
		
	}
	
	@Test
	public void testCreate(){
		Activity activity = new Activity();
		activity.setDescription("reading");
		activity.setDuration(55);
		
		ActivityClient client = new ActivityClient();
		activity = client.create(activity);
		
		System.out.println("create: " + activity);		
		assertNotNull(activity);
		
	}
	
	@Test
	public void testGetUser() {

		ActivityClient client = new ActivityClient();
		User user = client.getUser("1234");
		//String activity = client.get("1234");
		System.out.print(user.getName() + " " + user.getId());
		System.out.print(user);
		assertNotNull(user);		
	}

	@Test
	public void testGet() {

		ActivityClient client = new ActivityClient();
		Activity activity = client.get("1234");
		//String activity = client.get("1234");
		System.out.print(activity.getDescription() + " " + activity.getDuration());
		System.out.print(activity);
		assertNotNull(activity);		
	}
	
	@Test
	public void testGetAll(){
		
		ActivityClient client = new ActivityClient();
		List<Activity> list = client.getAll();
		System.out.print(list);
		assertNotNull(list);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithBadRequest(){
		ActivityClient client = new ActivityClient();
		client.get("12");
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithNotFound(){
		ActivityClient client = new ActivityClient();
		client.get("7777");
	}	

}
