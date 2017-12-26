package introsde.assignment3.soap;

import java.util.List;

import javax.jws.WebService;

import introsde.assignment3.soap.PersonWebService;
import javassist.NotFoundException;
import unitn.dallatorre.entities.Activity;
import unitn.dallatorre.entities.ActivityType;
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
	public Person getPerson(Long id) {
		Person databasePerson = Person.getPersonById(id.intValue());
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
			throw new IllegalArgumentException("Id Cannot be choosen before creation.");
		}
		if (person.getActivitypreference() != null) {
			List<Activity> activityPreference = person.getActivitypreference();
			for (Activity activity : activityPreference) {
				if(activity.getId() != null) {
					new IllegalArgumentException("cannot generate an activity with a given ID");
				}
				if(activity.getType() == null) {
					new IllegalArgumentException("an activity should have a type associated");
				}
				ActivityType activityType = ActivityType.getById(activity.getType().getType());
				if(activityType == null) {
					new IllegalArgumentException("activity type not recognized");
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
}