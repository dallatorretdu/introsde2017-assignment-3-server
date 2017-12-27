
package introsde.assignment3.soap.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deletePerson", namespace = "http://soap.assignment3.introsde/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deletePerson", namespace = "http://soap.assignment3.introsde/")
public class DeletePerson {

    @XmlElement(name = "arg0", namespace = "")
    private unitn.dallatorre.entities.Person arg0;

    /**
     * 
     * @return
     *     returns Person
     */
    public unitn.dallatorre.entities.Person getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(unitn.dallatorre.entities.Person arg0) {
        this.arg0 = arg0;
    }

}
