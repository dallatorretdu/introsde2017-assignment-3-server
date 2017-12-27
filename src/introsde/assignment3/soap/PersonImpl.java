package introsde.assignment3.soap;

import java.util.List;

import javax.jws.WebService;

import introsde.assignment3.soap.PersonWebService;
import unitn.dallatorre.entities.Activity;
import unitn.dallatorre.entities.ActivityType;
import unitn.dallatorre.entities.ActivityWrapper;
import unitn.dallatorre.entities.Person;
//Service Implementation
@WebService(endpointInterface = "introsde.assignment3.soap.PersonWebService")
public class PersonImpl implements PersonWebService{
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
    
    private void checkPersonExists(Person databasePerson) {
		if (databasePerson == null) {
			throw new IllegalArgumentException("Requested person not found");
		}
	}

	@Override
	public List<Person> getPersonList() {
		List<Person> personList = Person.getAllPersons();		// GET all the person in DB and create the people wrapper
		return personList;
	}

	@Override
	public Person getPerson(Integer id) {
		Person databasePerson = Person.getPersonById(id);
		checkPersonExists(databasePerson);
		return databasePerson;
	}

	@Override
	public Person updatePerson(Person person) {
		Person databasePerson = Person.getPersonById(person.getId());
		checkPersonExists(databasePerson);
		
		databasePerson.setFirstname(person.getFirstname());
		databasePerson.setLastname(person.getLastname());
		databasePerson.setBirthdate(person.getBirthdate());
		databasePerson = Person.updatePerson(databasePerson);
		return databasePerson;
	}
	
	@Override
	public Person createPerson(Person person) {
		if (person.getId() != null) {
			person.setId(null);
		}
		if (person.getActivitypreference() != null) {
			List<Activity> activityPreference = person.getActivitypreference();
			for (Activity activity : activityPreference) {
				if(activity.getId() != null) {
					activity.setId(null);
				}
				if(activity.getType() == null) {
					throw new IllegalArgumentException("an activity should have a type associated");
				}
				ActivityType activityType = ActivityType.getById(activity.getType().getType());
				if(activityType == null) {
					throw new IllegalArgumentException("activity type not recognized");
				}
				activity.setType(activityType);
			}
		}
		Person p = Person.updatePerson(person);
		return p;
	}

	@Override
	public void deletePerson(Person person) {
		Person databasePerson = Person.getPersonById(person.getId());
		checkPersonExists(databasePerson);
		Person.removePerson(person);
	}

	@Override
	public List<Activity> readPersonPreferences(Integer id, String type) {
		Person databasePerson = Person.getPersonById(id);
		checkPersonExists(databasePerson);
		
		ActivityWrapper activities = new ActivityWrapper();
		activities.setActivity(databasePerson.getActivitypreference());
		ActivityType activityType = new ActivityType();
		activityType.setType(type);
		activities.filterActivities(activityType);
		return activities.getActivity();
	}

	@Override
	public List<Activity> readPreferences() {
		return Activity.getAllActivities();
	}

	@Override
	public Activity readPersonPreference(Integer id, Integer activityId) {
		Person databasePerson = Person.getPersonById(id);
		checkPersonExists(databasePerson);
		
		ActivityWrapper activities = new ActivityWrapper();
		activities.setActivity(databasePerson.getActivitypreference());
		activities.filterActivities(activityId);
		return activities.getActivity().get(0);
	}

	@Override
	public void savePersonPreference(Integer id, Activity activity) {
		Person databasePerson = Person.getPersonById(id);
		checkPersonExists(databasePerson);
		ActivityType activityType = ActivityType.getById(activity.getType().getType());
		if(activityType == null) {
			throw new IllegalArgumentException("Activity Type not found");
		}
		if(activity.getId() != null) {
			activity.setId(null);
		}
		
		Activity newActivity = new Activity();
		newActivity.setType(activityType);
		newActivity.setName(activity.getName());
		newActivity.setPlace(activity.getPlace());
		newActivity.setStartdate(activity.getStartdate());
		newActivity.setPreference(activity.getPreference());
		
		databasePerson.getActivitypreference().add(newActivity);
		databasePerson = Person.updatePerson(databasePerson);
		
	}

	@Override
	public Activity updatePersonPreference(Integer id, Activity activity) {
		Person databasePerson = Person.getPersonById(id);
		checkPersonExists(databasePerson);
		ActivityType activityType = ActivityType.getById(activity.getType().getType());
		if (activityType == null) {
			throw new IllegalArgumentException("Non existant activity type");
		}
		Activity databaseActivity = Activity.getActivityById(activity.getId());
		if (databaseActivity == null) {
			throw new IllegalArgumentException("No activity with given ID");
		}
		
		int indexOf = databasePerson.getActivitypreference().indexOf(databaseActivity);
		databasePerson.getActivitypreference().get( indexOf ).setType(activityType);
		databasePerson.getActivitypreference().get( indexOf ).setDescription(activity.getDescription());
		databasePerson.getActivitypreference().get( indexOf ).setName(activity.getName());
		databasePerson.getActivitypreference().get( indexOf ).setPlace(activity.getPlace());
		databasePerson.getActivitypreference().get( indexOf ).setStartdate(activity.getStartdate());
		databasePerson.getActivitypreference().get( indexOf ).setPreference(activity.getPreference());
		databasePerson = Person.updatePerson(databasePerson);
		
		return databasePerson.getActivitypreference().get( indexOf );
	}

	@Override
	public Activity evaluatePersonPreference(Integer id, Activity activity, Integer value) {
		if( value<0 || value>10 ) {
			throw new IllegalArgumentException("Preference rating should be between 0 and 10");
		}
		activity.setPreference(value);
		return updatePersonPreference(id, activity);
	}

	@Override
	public List<Activity> getBestPersonPreferences(Integer id) {
		Person databasePerson = Person.getPersonById(id);
		checkPersonExists(databasePerson);
		
		ActivityWrapper activities = new ActivityWrapper();
		activities.setActivity(databasePerson.getActivitypreference());
		activities.filterBestActivities();
		return activities.getActivity();
	}
}