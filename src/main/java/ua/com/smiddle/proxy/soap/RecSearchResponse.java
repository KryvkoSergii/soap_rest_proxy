//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.19 at 04:22:04 PM EET 
//


package ua.com.smiddle.proxy.soap;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://proxy.smiddle.com.ua/soap}SessionInfoList" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sessionInfoList"
})
@XmlRootElement(name = "RecSearchResponse")
public class RecSearchResponse {

    @XmlElement(name = "SessionInfoList", required = true)
    protected List<SessionInfoList> sessionInfoList;

    /**
     * Gets the value of the sessionInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sessionInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSessionInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SessionInfoList }
     * 
     * 
     */
    public List<SessionInfoList> getSessionInfoList() {
        if (sessionInfoList == null) {
            sessionInfoList = new ArrayList<SessionInfoList>();
        }
        return this.sessionInfoList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RecSearchResponse{");
        sb.append("sessionInfoList=").append(sessionInfoList);
        sb.append('}');
        return sb.toString();
    }
}
