//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.09 at 10:10:23 AM CST 
//


package com.elong.nbopen.api.model.elong;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HotelBrand complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HotelBrand">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="BrandId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="GroupId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ShortName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ShortNameEn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NameEn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Letters" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LettersEn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HotelBrand")
public class HotelBrand {

    @XmlAttribute(name = "BrandId")
    protected int brandId;
    @XmlAttribute(name = "GroupId")
    protected int groupId;
    @XmlAttribute(name = "ShortName")
    protected String shortName;
    @XmlAttribute(name = "ShortNameEn")
    protected String shortNameEn;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "NameEn")
    protected String nameEn;
    @XmlAttribute(name = "Letters")
    protected String letters;
    @XmlAttribute(name = "LettersEn")
    protected String lettersEn;

    /**
     * Gets the value of the brandId property.
     * 
     */
    public int getBrandId() {
        return brandId;
    }

    /**
     * Sets the value of the brandId property.
     * 
     */
    public void setBrandId(int value) {
        this.brandId = value;
    }

    /**
     * Gets the value of the groupId property.
     * 
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     */
    public void setGroupId(int value) {
        this.groupId = value;
    }

    /**
     * Gets the value of the shortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the shortNameEn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortNameEn() {
        return shortNameEn;
    }

    /**
     * Sets the value of the shortNameEn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortNameEn(String value) {
        this.shortNameEn = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the nameEn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * Sets the value of the nameEn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameEn(String value) {
        this.nameEn = value;
    }

    /**
     * Gets the value of the letters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLetters() {
        return letters;
    }

    /**
     * Sets the value of the letters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLetters(String value) {
        this.letters = value;
    }

    /**
     * Gets the value of the lettersEn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLettersEn() {
        return lettersEn;
    }

    /**
     * Sets the value of the lettersEn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLettersEn(String value) {
        this.lettersEn = value;
    }

}
