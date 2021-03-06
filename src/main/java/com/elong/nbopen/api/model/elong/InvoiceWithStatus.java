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
 * <p>Java class for InvoiceWithStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceWithStatus">
 *   &lt;complexContent>
 *     &lt;extension base="{}Invoice">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DeliveryStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceWithStatus", propOrder = {
    "status",
    "deliveryStatus"
})
public class InvoiceWithStatus
    extends Invoice
{

    @JSONField(name = "Status")
    protected boolean status;
    @JSONField(name = "DeliveryStatus")
    protected boolean deliveryStatus;
    @JSONField(name = "ProcessType")
    protected int processType;
    @JSONField(name = "ProcessStatus")
    protected int processStatus;
    @JSONField(name = "MemoInfo")
    protected String memoInfo;
    @JSONField(name = "UrlForPDF")
    protected String urlForPDF;
    @JSONField(name = "UrlForWeixinCard")
    protected String urlForWeixinCard;
    @JSONField(name = "InvCode")
    protected String invCode;
    @JSONField(name = "InvNumber")
    protected String invNumber;
    @JSONField(name = "BillNumber")
    protected String billNumber;

    /**
     * Gets the value of the status property.
     * 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the deliveryStatus property.
     * 
     */
    public boolean isDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * Sets the value of the deliveryStatus property.
     * 
     */
    public void setDeliveryStatus(boolean value) {
        this.deliveryStatus = value;
    }

    public int getProcessType() {
        return processType;
    }

    public void setProcessType(int processType) {
        this.processType = processType;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public String getMemoInfo() {
        return memoInfo;
    }

    public void setMemoInfo(String memoInfo) {
        this.memoInfo = memoInfo;
    }

    public String getUrlForPDF() {
        return urlForPDF;
    }

    public void setUrlForPDF(String urlForPDF) {
        this.urlForPDF = urlForPDF;
    }

    public String getUrlForWeixinCard() {
        return urlForWeixinCard;
    }

    public void setUrlForWeixinCard(String urlForWeixinCard) {
        this.urlForWeixinCard = urlForWeixinCard;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
}
