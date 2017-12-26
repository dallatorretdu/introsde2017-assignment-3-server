package introsde.assignment3.soap;

import java.util.List;

import javax.jws.WebService;

import introsde.assignment3.soap.PersonWebService;
import unitn.dallatorre.entities.Person;
//Service Implementation
@WebService(endpointInterface = "introsde.assignment3.soap.PersonWebService")
public class PersonImpl implements PersonWebService{
    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }

	@Override
	public List<Person> getPersonList() {
		List<Person> personList = Person.getAllPersons();		// GET all the person in DB and create the people wrapper
		return personList;
	}

	@Override
	public Person getPerson(Long id) {
		return Person.getPersonById(id.intValue());
	}
}