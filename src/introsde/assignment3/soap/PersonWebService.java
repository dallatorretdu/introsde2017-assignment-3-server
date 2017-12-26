package introsde.assignment3.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import unitn.dallatorre.entities.Person;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface PersonWebService{
    @WebMethod String getHelloWorldAsString(String name);

	@WebMethod List<Person> getPersonList();
	@WebMethod Person getPerson(Long id);
	@WebMethod Person updatePerson(Person person);
	@WebMethod Person createPerson(Person person);
	@WebMethod void deletePerson(Person person);
}
