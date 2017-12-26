package introsde.assignment3.endpoint;

import javax.xml.ws.Endpoint;

import introsde.assignment3.soap.InitializerImpl;
import introsde.assignment3.soap.PersonImpl;

//Endpoint publisher
public class PersonPublisher{
    public static void main(String[] args) {
       Endpoint.publish("http://localhost:6901/soap/person", new PersonImpl());
       Endpoint.publish("http://localhost:6901/soap/initialize", new InitializerImpl());
    }
}