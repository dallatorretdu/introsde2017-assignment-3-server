package introsde.assignment3.endpoint;

import javax.xml.ws.Endpoint;

import introsde.assignment3.soap.InitializerImpl;
import introsde.assignment3.soap.PersonImpl;

//Endpoint publisher
public class PersonPublisher{
    public static void main(String[] args) {
       //Endpoint.publish("http://localhost:6900/soap/person", new PersonImpl());
       //Endpoint.publish("http://localhost:6900/soap/initialize", new InitializerImpl());
       Endpoint.publish("https://introsde-assignment-3-dallator.herokuapp.com/soap/person", new PersonImpl());
       Endpoint.publish("https://introsde-assignment-3-dallator.herokuapp.com/soap/initialize", new InitializerImpl());
    }
}