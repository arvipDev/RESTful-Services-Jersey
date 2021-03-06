package com.pluralsight.repository;
import java.util.ArrayList;
import java.util.List;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.search.ActivitySearch;

public class ActivityRepositoryStub implements ActivityRepository
{
	
	@Override
	public List<Activity> findAllActivities () 
	{
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		
		activities.add(activity1);
		
		Activity activity2 = new Activity();
		
		activity2.setDescription("Cycling");
		activity2.setDuration(120);
		
		activities.add(activity2);
		
		return activities;		
	}

	@Override
	public Activity findActivity(String activityId) {
		
		if(activityId.equals("7777")){
			return null;
		}
		
		Activity activity1 = new Activity();
		activity1.setId("1234");
		activity1.setDescription("Swiming");
		activity1.setDuration(55);
		
		User user = new User();
		user.setId("54dfv");
		user.setName("Arvind");
		activity1.setUser(user);
				
		return activity1;
	}
	
	@Override
	public void create(Activity activity) 
	{
		//insert the activity to DB
	}

	@Override
	public Activity update(Activity activity) {
		return activity;
	}

	@Override
	public void delete(String activityId) {
		//delete from activity list DB
		
	}

	@Override
	public List<Activity> findByDescription(List<String> descriptions) {
		//select * from activities where description in (?,?,?)
		List<Activity> activities = new ArrayList<Activity>();
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("walking");
		activity.setDuration(125);
		
		activities.add(activity);
				
		return activities;
	}

	@Override
	public List<Activity> findByDescriptionAndRange(List<String> descriptions, int durationFrom, int durationTo) {
		//select * from activities where description in (?,?,?) and duration < ? and duration > ?
		List<Activity> activities = new ArrayList<Activity>();
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("walking");
		activity.setDuration(125);
		
		activities.add(activity);
				
		return activities;
	}

	@Override
	public List<Activity> findByConstraints(ActivitySearch search) {
		
		List<Activity> activities = new ArrayList<Activity>();
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("walking");
		activity.setDuration(125);
		
		activities.add(activity);
				
		return activities;
	}

}
