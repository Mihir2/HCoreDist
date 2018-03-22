//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.20 at 12:48:21 PM GMT 
//


package weka.core.pmml.jaxbbindings;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataField element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="DataField">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{http://www.dmg.org/PMML-4_1}Extension" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;sequence>
 *             &lt;element ref="{http://www.dmg.org/PMML-4_1}Interval" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element ref="{http://www.dmg.org/PMML-4_1}Value" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/sequence>
 *         &lt;attribute name="dataType" use="required" type="{http://www.dmg.org/PMML-4_1}DATATYPE" />
 *         &lt;attribute name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *         &lt;attribute name="isCyclic" default="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="0"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/attribute>
 *         &lt;attribute name="name" use="required" type="{http://www.dmg.org/PMML-4_1}FIELD-NAME" />
 *         &lt;attribute name="optype" use="required" type="{http://www.dmg.org/PMML-4_1}OPTYPE" />
 *         &lt;attribute name="taxonomy" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "extension",
    "interval",
    "value"
})
@XmlRootElement(name = "DataField")
public class DataField {

    @XmlElement(name = "Extension", namespace = "http://www.dmg.org/PMML-4_1", required = true)
    protected List<Extension> extension;
    @XmlElement(name = "Interval", namespace = "http://www.dmg.org/PMML-4_1", required = true)
    protected List<Interval> interval;
    @XmlElement(name = "Value", namespace = "http://www.dmg.org/PMML-4_1", required = true)
    protected List<Value> value;
    @XmlAttribute(required = true)
    protected DATATYPE dataType;
    @XmlAttribute
    protected String displayName;
    @XmlAttribute
    protected String isCyclic;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute(required = true)
    protected OPTYPE optype;
    @XmlAttribute
    protected String taxonomy;

    public DataField() {}
    
    public DataField(String name, OPTYPE optype) {
    	this.name = name;
    	this.optype = optype;
    }
    
    /**
     * Gets the value of the extension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Extension }
     * 
     * 
     */
    public List<Extension> getExtension() {
        if (extension == null) {
            extension = new ArrayList<Extension>();
        }
        return this.extension;
    }

    /**
     * Gets the value of the interval property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interval property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterval().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interval }
     * 
     * 
     */
    public List<Interval> getInterval() {
        if (interval == null) {
            interval = new ArrayList<Interval>();
        }
        return this.interval;
    }

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Value }
     * 
     */
    public List<Value> getValues() {
        if (value == null) {
            value = new ArrayList<Value>();
        }
        return this.value;
    }
    
    public void addValue(Value v) {
    	if (value == null) {
            value = new ArrayList<Value>();
        }
    	value.add(v);
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link DATATYPE }
     *     
     */
    public DATATYPE getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DATATYPE }
     *     
     */
    public void setDataType(DATATYPE value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the isCyclic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsCyclic() {
        if (isCyclic == null) {
            return "0";
        } else {
            return isCyclic;
        }
    }

    /**
     * Sets the value of the isCyclic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsCyclic(String value) {
        this.isCyclic = value;
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
     * Gets the value of the optype property.
     * 
     * @return
     *     possible object is
     *     {@link OPTYPE }
     *     
     */
    public OPTYPE getOptype() {
        return optype;
    }

    /**
     * Sets the value of the optype property.
     * 
     * @param value
     *     allowed object is
     *     {@link OPTYPE }
     *     
     */
    public void setOptype(OPTYPE value) {
        this.optype = value;
    }

    /**
     * Gets the value of the taxonomy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxonomy() {
        return taxonomy;
    }

    /**
     * Sets the value of the taxonomy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxonomy(String value) {
        this.taxonomy = value;
    }

}
