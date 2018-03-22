//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.20 at 12:48:21 PM GMT 
//


package weka.core.pmml.jaxbbindings;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * <p>Java class for NO-TRUE-CHILD-STRATEGY.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NO-TRUE-CHILD-STRATEGY">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="returnNullPrediction"/>
 *     &lt;enumeration value="returnLastPrediction"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum NOTRUECHILDSTRATEGY {

    @XmlEnumValue("returnLastPrediction")
    RETURN_LAST_PREDICTION("returnLastPrediction"),
    @XmlEnumValue("returnNullPrediction")
    RETURN_NULL_PREDICTION("returnNullPrediction");
    private final String value;

    NOTRUECHILDSTRATEGY(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NOTRUECHILDSTRATEGY fromValue(String v) {
        for (NOTRUECHILDSTRATEGY c: NOTRUECHILDSTRATEGY.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
