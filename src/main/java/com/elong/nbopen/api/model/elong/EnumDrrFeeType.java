//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.09 at 10:10:23 AM CST 
//


package com.elong.nbopen.api.model.elong;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumDrrFeeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumDrrFeeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="WeekendFee"/>
 *     &lt;enumeration value="WeekdayFee"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumDrrFeeType")
@XmlEnum
public enum EnumDrrFeeType {

    @XmlEnumValue("WeekendFee")
    WeekendFee("WeekendFee"),
    @XmlEnumValue("WeekdayFee")
    WeekdayFee("WeekdayFee");
    private final String value;

    EnumDrrFeeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumDrrFeeType fromValue(String v) {
        for (EnumDrrFeeType c: EnumDrrFeeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
