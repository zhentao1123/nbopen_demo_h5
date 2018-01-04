//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.09 at 10:10:23 AM CST 
//


package com.elong.nbopen.api.model.elong;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement; 
import java.util.List; 
import com.alibaba.fastjson.annotation.JSONField;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HotelStaticInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelStaticInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Hbi" type="{}HotelBaseInfo" minOccurs="0"/>
 *         &lt;element name="Hrlist" type="{}ArrayOfHotelRoom" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelStaticInfo", propOrder = {
    "hbi",
    "hrlist"
})
public class HotelStaticInfo {

    @JSONField(name = "Hbi")
    protected HotelBaseInfo hbi;
    @JSONField(name = "Hrlist")
    protected List<HotelRoom> hrlist;

    /**
     * Gets the value of the hbi property.
     * 
     * @return
     *     possible object is
     *     {@link HotelBaseInfo }
     *     
     */
    public HotelBaseInfo getHbi() {
        return hbi;
    }

    /**
     * Sets the value of the hbi property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelBaseInfo }
     *     
     */
    public void setHbi(HotelBaseInfo value) {
        this.hbi = value;
    }

    /**
     * Gets the value of the hrlist property.
     * 
     * @return
     *     possible object is
     *     {@link List<HotelRoom> }
     *     
     */
    public List<HotelRoom> getHrlist() {
        return hrlist;
    }

    /**
     * Sets the value of the hrlist property.
     * 
     * @param value
     *     allowed object is
     *     {@link List<HotelRoom> }
     *     
     */
    public void setHrlist(List<HotelRoom> value) {
        this.hrlist = value;
    }

}
