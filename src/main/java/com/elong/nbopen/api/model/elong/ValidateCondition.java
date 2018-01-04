//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.09 at 10:10:23 AM CST 
//


package com.elong.nbopen.api.model.elong;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement; 
import java.util.List; 
import com.alibaba.fastjson.annotation.JSONField;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * <p>Java class for ValidateCondition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidateCondition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ArrivalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DepartureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EarliestArrivalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LatestArrivalTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="HotelId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoomTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RatePlanId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TotalPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="NumberOfRooms" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateCondition", propOrder = {
    "arrivalDate",
    "departureDate",
    "earliestArrivalTime",
    "latestArrivalTime",
    "hotelId",
    "roomTypeId",
    "ratePlanId",
    "totalPrice",
    "numberOfRooms"
})
public class ValidateCondition {
    @JSONField(name = "ArrivalDate", format = "yyyy-MM-dd")
    @XmlSchemaType(name = "dateTime")
    protected Date arrivalDate;
    @JSONField(name = "DepartureDate", format = "yyyy-MM-dd")
    @XmlSchemaType(name = "dateTime")
    protected Date departureDate;
    @JSONField(name = "EarliestArrivalTime", format = "yyyy-MM-dd HH:mm:SS")
    @XmlSchemaType(name = "dateTime")
    protected Date earliestArrivalTime;
    @JSONField(name = "LatestArrivalTime", format = "yyyy-MM-dd HH:mm:SS")
    @XmlSchemaType(name = "dateTime")
    protected Date latestArrivalTime;
    @JSONField(name = "HotelId")
    protected String hotelId;
    @JSONField(name = "RoomTypeId")
    protected String roomTypeId;
    @JSONField(name = "RatePlanId")
    protected int ratePlanId;
    @JSONField(name = "TotalPrice")
    protected BigDecimal totalPrice;
    @JSONField(name = "NumberOfRooms")
    protected int numberOfRooms;

    /**
     * Gets the value of the arrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the value of the arrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setArrivalDate(Date value) {
        this.arrivalDate = value;
    }

    /**
     * Gets the value of the departureDate property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the value of the departureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDepartureDate(Date value) {
        this.departureDate = value;
    }

    /**
     * Gets the value of the earliestArrivalTime property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getEarliestArrivalTime() {
        return earliestArrivalTime;
    }

    /**
     * Sets the value of the earliestArrivalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setEarliestArrivalTime(Date value) {
        this.earliestArrivalTime = value;
    }

    /**
     * Gets the value of the latestArrivalTime property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getLatestArrivalTime() {
        return latestArrivalTime;
    }

    /**
     * Sets the value of the latestArrivalTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setLatestArrivalTime(Date value) {
        this.latestArrivalTime = value;
    }

    /**
     * Gets the value of the hotelId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * Sets the value of the hotelId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHotelId(String value) {
        this.hotelId = value;
    }

    /**
     * Gets the value of the roomTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * Sets the value of the roomTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeId(String value) {
        this.roomTypeId = value;
    }

    /**
     * Gets the value of the ratePlanId property.
     * 
     */
    public int getRatePlanId() {
        return ratePlanId;
    }

    /**
     * Sets the value of the ratePlanId property.
     * 
     */
    public void setRatePlanId(int value) {
        this.ratePlanId = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPrice(BigDecimal value) {
        this.totalPrice = value;
    }

    /**
     * Gets the value of the numberOfRooms property.
     * 
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets the value of the numberOfRooms property.
     * 
     */
    public void setNumberOfRooms(int value) {
        this.numberOfRooms = value;
    }

}
