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
 * <p>Java class for BookingRule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BookingRule">
 *   &lt;complexContent>
 *     &lt;extension base="{}BaseBookingRule">
 *       &lt;sequence>
 *         &lt;element name="BookingRuleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookingRule", propOrder = {
    "bookingRuleId"
})
public class BookingRule
    extends BaseBookingRule
{

    @JSONField(name = "BookingRuleId")
    protected long bookingRuleId;

    /**
     * Gets the value of the bookingRuleId property.
     * 
     */
    public long getBookingRuleId() {
        return bookingRuleId;
    }

    /**
     * Sets the value of the bookingRuleId property.
     * 
     */
    public void setBookingRuleId(long value) {
        this.bookingRuleId = value;
    }

}
