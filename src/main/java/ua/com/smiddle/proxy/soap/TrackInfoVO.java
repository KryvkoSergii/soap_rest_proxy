//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.19 at 04:22:04 PM EET 
//


package ua.com.smiddle.proxy.soap;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="trackNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "trackNumber"
})
@XmlRootElement(name = "TrackInfoVO")
public class TrackInfoVO {

    @XmlElement(required = true)
    protected BigInteger trackNumber;

    /**
     * Gets the value of the trackNumber property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getTrackNumber() {
        return trackNumber;
    }

    /**
     * Sets the value of the trackNumber property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setTrackNumber(BigInteger value) {
        this.trackNumber = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TrackInfoVO{");
        sb.append("trackNumber=").append(trackNumber);
        sb.append('}');
        return sb.toString();
    }
}
