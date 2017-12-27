
package introsde.assignment3.soap.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "evaluatePersonPreference", namespace = "http://soap.assignment3.introsde/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evaluatePersonPreference", namespace = "http://soap.assignment3.introsde/", propOrder = {
    "arg0",
    "arg1",
    "arg2"
})
public class EvaluatePersonPreference {

    @XmlElement(name = "arg0", namespace = "")
    private Integer arg0;
    @XmlElement(name = "arg1", namespace = "")
    private unitn.dallatorre.entities.Activity arg1;
    @XmlElement(name = "arg2", namespace = "")
    private Integer arg2;

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Integer arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns Activity
     */
    public unitn.dallatorre.entities.Activity getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(unitn.dallatorre.entities.Activity arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(Integer arg2) {
        this.arg2 = arg2;
    }

}
