//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.11.20 at 10:53:24 AM CET 
//


package hu.uni.miskolc.iit.uhv61t.XmlAssignment.xjcModels;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for borrowStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="borrowStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BORROWED"/>
 *     &lt;enumeration value="RETURNED"/>
 *     &lt;enumeration value="EXPIRED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "borrowStatusType")
@XmlEnum
public enum BorrowStatusType {

    BORROWED,
    RETURNED,
    EXPIRED;

    public String value() {
        return name();
    }

    public static BorrowStatusType fromValue(String v) {
        return valueOf(v);
    }

}
