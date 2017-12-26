package introsde.assignment3.soap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import introsde.assignment3.soap.InitializerWebService;
import unitn.dallatorre.entities.Activity;
import unitn.dallatorre.entities.ActivityType;
import unitn.dallatorre.entities.Person;

//Service Implementation
@WebService(endpointInterface = "introsde.assignment3.soap.InitializerWebService")
public class InitializerImpl implements InitializerWebService {

	@Override
	public List<Person> initializeDB() {
		
		ActivityType t1 = generateAndSaveActivityType("Sport");		// generate 3 TYPES
		ActivityType t2 = generateAndSaveActivityType("Social");
		ActivityType t3 = generateAndSaveActivityType("Dota");
																	// Generate 5 Activities
		Activity a1 = generateAndSaveActivity(t1, "Moving on your own power", "Running", "Albere");
		Activity a2 = generateAndSaveActivity(t1, "Going on 2 wheels", "Cycling", "Lung  Adige");
		Activity a3 = generateAndSaveActivity(t2, "Moving randomly", "Dancing", "Papafico");
		Activity a4 = generateAndSaveActivity(t2, "Getting drunk", "Drinking", "H-Acca");
		Activity a5 = generateAndSaveActivity(t3, "DOTA is Life", "DOTA", "col Tonno");
																	// Generate 5 persons
		Person p1 = generateAndSavePerson(a1, "Mario", "Rossi");
		Person p2 = generateAndSavePerson(a2, "Giulio", "Dallatorre");
		Person p3 = generateAndSavePerson(a3, "Stefano", "Tavonatti");
		Person p4 = generateAndSavePerson(a4, "Pinco", "Pallino");
		Person p5 = generateAndSavePerson(a5, "Manuel", "Dezulian");
																	// Generate the People wrapper object
		List<Person> personList = new ArrayList<Person>();
		personList.add(p1);
		personList.add(p2);
		personList.add(p3);
		personList.add(p4);
		personList.add(p5);
		
		return personList;
	}
	
	// Method that creates a person and saves it in teh persistence unit
		private Person generateAndSavePerson(Activity activity, String firstname, String lastname) {
			Person p = new Person();
			p.setFirstname(firstname);
			p.setLastname(lastname);
			p.setBirthdate(new Date(System.currentTimeMillis()));
			List<Activity> activitypreference = new ArrayList<Activity>();
			activitypreference.add(activity);
			p.setActivitypreference(activitypreference);
			return Person.updatePerson(p);
		}
		// Method that creates an activity and saves it in teh persistence unit
		private Activity generateAndSaveActivity(ActivityType activityType, String description, String name, String place) {
			Activity a = new Activity();
			a.setDescription(description);
			a.setName(name);
			a.setPlace(place);
			a.setStartdate(new Date(System.currentTimeMillis()));
			a.setType(activityType);
			a.setPreference(3);
			a=Activity.updateActivity(a);
			return a;
		}
		// Method that creates a type and saves it in teh persistence unit
		private ActivityType generateAndSaveActivityType(String activityTypeName) {
			ActivityType t1 = new ActivityType();
			t1.setType(activityTypeName);
			t1=ActivityType.updateActivityType(t1);
			return t1;
		}

}
