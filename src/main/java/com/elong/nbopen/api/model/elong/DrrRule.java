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
 * <p>Java class for DrrRule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrrRule">
 *   &lt;complexContent>
 *     &lt;extension base="{}BaseDrrRule">
 *       &lt;sequence>
 *         &lt;element name="DrrRuleId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrrRule", propOrder = {
    "drrRuleId"
})
public class DrrRule
    extends BaseDrrRule
{

    @JSONField(name = "DrrRuleId")
    protected int drrRuleId;

    /**
     * Gets the value of the drrRuleId property.
     * 
     */
    public int getDrrRuleId() {
        return drrRuleId;
    }

    /**
     * Sets the value of the drrRuleId property.
     * 
     */
    public void setDrrRuleId(int value) {
        this.drrRuleId = value;
    }

}
