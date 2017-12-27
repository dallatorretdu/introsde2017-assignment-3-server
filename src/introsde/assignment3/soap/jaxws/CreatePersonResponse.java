
package introsde.assignment3.soap.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createPersonResponse", namespace = "http://soap.assignment3.introsde/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPersonResponse", namespace = "http://soap.assignment3.introsde/")
public class CreatePersonResponse {

    @XmlElement(name = "return", namespace = "")
    private unitn.dallatorre.entities.Person _return;

    /**
     * 
     * @return
     *     returns Person
     */
    public unitn.dallatorre.entities.Person getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(unitn.dallatorre.entities.Person _return) {
        this._return = _return;
    }

}
