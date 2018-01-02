package introsde.assignment3.test;

import static org.junit.Assert.*;

import unitn.dallatorre.entities.Activity;
import unitn.dallatorre.entities.ActivityType;
import unitn.dallatorre.entities.Person;

import org.junit.BeforeClass;
import org.junit.Test;

import introsde.assignment3.endpoint.PersonPublisher;
import introsde.assignment3.soap.PersonWebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class PersonClientTests {

	@BeforeClass
	public static void publishServer() {
		PersonPublisher.main(null);
	}
	
	@Test
	public void testHelloWorld() throws Exception {
        PersonWebService personWS = getPersonWebService();
        assertEquals("Hello World JAX-WS Maurizio", personWS.getHelloWorldAsString("Maurizio"));
    }

	@Test
	public void testReadPersonList() throws Exception {
        PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
    }

	@Test
	public void testReadPersonWithID() throws Exception {
        PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
        Integer id = personList.get(0).getId();
        
        Person person = personWS.getPerson(id);
        assertNotNull(person);
        assertEquals(id.intValue(), person.getId().intValue());
    }

	@Test
	public void testUpdatePersonWithID() throws Exception {
        PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
        Person person = personList.get(0);
        person.setFirstname(person.getFirstname() + "U");
        
        Person person2 = personWS.updatePerson(person);
        assertNotNull(person2);
        assertEquals(person.getFirstname(), person2.getFirstname());
    }
	
	@Test
	public void createNewPersonAndDeleteIt() throws Exception {
		Person person = generateNewPerson();
		
		PersonWebService personWS = getPersonWebService();
        Person person2 = personWS.createPerson(person);
        
        assertEquals(person.getFirstname(), person2.getFirstname());
        assertEquals(person.getLastname(), person2.getLastname());
        
        personWS.deletePerson(person2);
	}
	
	@Test
	public void testReadPersonPreferences() throws Exception {
		PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
        Integer id = personList.get(0).getId();
        ActivityType type = generateActivityType();
        
        List<Activity> Preferences = personWS.readPersonPreferences(id, type.getType());
	}
	
	@Test
	public void testListAllPreferences() throws Exception {
		PersonWebService personWS = getPersonWebService();
		List<Activity> activities = personWS.readPreferences();
		assertNotEquals(0, activities.size());
	}
	
	@Test
	public void testReadPersonPreference() throws Exception {
		PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
        Person person = personList.get(0);
        Activity activity = person.getActivitypreference().get(0);
        
        Activity activity1 = personWS.readPersonPreference(person.getId() , activity.getId());
        assertEquals(activity.getId(), activity1.getId());
	}
	
	@Test
	public void testSavePersonPreferenceAndUpdate() throws Exception {
		PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
        Person person = personList.get(0);
        Activity activity = generateActivity();
        
        personWS.savePersonPreference(person.getId(), activity);
        
        person = personWS.getPerson(person.getId());
        Activity returnedActivity = person.getActivitypreference().get(person.getActivitypreference().size()-1);
        assertEquals(activity.getStartdate(), returnedActivity.getStartdate());
        
        returnedActivity.setName(returnedActivity.getName()+"U");
        Activity updatedActivity = personWS.updatePersonPreference(person.getId(), returnedActivity);
        
        assertEquals(returnedActivity.getName(), updatedActivity.getName());
	}
	
	@Test
	public void testPersonPreferenceRating() throws Exception {
		PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
        Person person = personList.get(0);
        Activity activity = person.getActivitypreference().get(0);
        
        Activity updatedActivity = personWS.evaluatePersonPreference(person.getId(), activity, 5);
        assertEquals(new Integer(5), updatedActivity.getPreference());
	}
	
	@Test
	public void testPersonBestPreference() throws Exception {
		PersonWebService personWS = getPersonWebService();
        List<Person> personList = personWS.getPersonList();
        assertNotEquals(0, personList.size());
        Person person = personList.get(0);
        
        List<Activity> preferences = personWS.getBestPersonPreferences(person.getId());
        if( preferences!=null ) {
        	for (Activity a : preferences) {
        		assertTrue( person.getActivitypreference().contains(a) );
        	}
        }
        else {
        	for (Activity a : person.getActivitypreference()) {
        		assertTrue( a.getPreference() == null );
        	}
        }
	}

	private Person generateNewPerson() {
		Activity activity = generateActivity();
		Person person = new Person();												//Generate a new Person with that activity
		person.setActivitypreference(new ArrayList<Activity>());
		person.getActivitypreference().add(activity);
		person.setFirstname("Firstname");
		Random rand = new Random();
		person.setLastname("L+"+rand.nextInt(9999999));								//And a random lastname
		person.setBirthdate(new Date(System.currentTimeMillis()));
		return person;
	}

	private Activity generateActivity() {
		ActivityType type = generateActivityType();
		Activity activity = new Activity();											//Generate a new Activity with that type
		activity.setDescription("Automatically Generated");
		activity.setName("Dota col Tonno - generated");
		activity.setPlace("A casa del Dezu - generated");
		activity.setStartdate(new Date(System.currentTimeMillis()));
		activity.setType(type);
		return activity;
	}

	private ActivityType generateActivityType() {
		ActivityType type = new ActivityType();										//Generate a new Activity Type
		type.setType("Dota");
		return type;
	}

	private PersonWebService getPersonWebService() throws MalformedURLException {
		URL url = new URL("http://localhost:6901/soap/person?wsdl");
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.assignment3.introsde/", "PersonImplService");
        Service service = Service.create(url, qname);
        PersonWebService personWS = service.getPort(PersonWebService.class);
		return personWS;
	}

}
 