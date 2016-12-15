package com.pluralsight.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.pluralsight.model.Activity;

public class ActivityClientTest {

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
