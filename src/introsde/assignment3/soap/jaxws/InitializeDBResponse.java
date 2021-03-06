
package introsde.assignment3.soap.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "initializeDBResponse", namespace = "http://soap.assignment3.introsde/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "initializeDBResponse", namespace = "http://soap.assignment3.introsde/")
public class InitializeDBResponse {

    @XmlElement(name = "return", namespace = "")
    private List<unitn.dallatorre.entities.Person> _return;

    /**
     * 
     * @return
     *     returns List<Person>
     */
    public List<unitn.dallatorre.entities.Person> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<unitn.dallatorre.entities.Person> _return) {
        this._return = _return;
    }

}
