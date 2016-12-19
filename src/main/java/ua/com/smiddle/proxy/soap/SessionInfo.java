//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.19 at 01:52:29 PM EET 
//


package ua.com.smiddle.proxy.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for SessionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SessionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tracksCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="baseURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="track" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://proxy.smiddle.com.ua/soap}TrackInfoVO"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionInfo", propOrder = {
    "tracksCount",
    "baseURL",
    "track"
})
public class SessionInfo {

    protected int tracksCount;
    @XmlElement(required = true)
    protected String baseURL;
    @XmlElement(required = true)
    protected List<SessionInfo.Track> track;

    /**
     * Gets the value of the tracksCount property.
     * 
     */
    public int getTracksCount() {
        return tracksCount;
    }

    /**
     * Sets the value of the tracksCount property.
     * 
     */
    public void setTracksCount(int value) {
        this.tracksCount = value;
    }

    /**
     * Gets the value of the baseURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseURL() {
        return baseURL;
    }

    /**
     * Sets the value of the baseURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseURL(String value) {
        this.baseURL = value;
    }

    /**
     * Gets the value of the track property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the track property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrack().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SessionInfo.Track }
     * 
     * 
     */
    public List<SessionInfo.Track> getTrack() {
        if (track == null) {
            track = new ArrayList<SessionInfo.Track>();
        }
        return this.track;
    }


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
     *         &lt;element ref="{http://proxy.smiddle.com.ua/soap}TrackInfoVO"/>
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
        "trackInfoVO"
    })
    public static class Track {

        @XmlElement(name = "TrackInfoVO", required = true)
        protected TrackInfoVO trackInfoVO;

        /**
         * Gets the value of the trackInfoVO property.
         * 
         * @return
         *     possible object is
         *     {@link TrackInfoVO }
         *     
         */
        public TrackInfoVO getTrackInfoVO() {
            return trackInfoVO;
        }

        /**
         * Sets the value of the trackInfoVO property.
         * 
         * @param value
         *     allowed object is
         *     {@link TrackInfoVO }
         *     
         */
        public void setTrackInfoVO(TrackInfoVO value) {
            this.trackInfoVO = value;
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SessionInfo{");
        sb.append("tracksCount=").append(tracksCount);
        sb.append(", baseURL='").append(baseURL).append('\'');
        sb.append(", track=").append(track);
        sb.append('}');
        return sb.toString();
    }
}