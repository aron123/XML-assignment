//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.11.20 at 10:53:24 AM CET 
//


package hu.uni.miskolc.iit.uhv61t.XmlAssignment.xjcModels;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for genreType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="genreType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Scifi"/>
 *     &lt;enumeration value="Crimi"/>
 *     &lt;enumeration value="Natural"/>
 *     &lt;enumeration value="Fiction"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "genreType")
@XmlEnum
public enum GenreType {

    @XmlEnumValue("Scifi")
    SCIFI("Scifi"),
    @XmlEnumValue("Crimi")
    CRIMI("Crimi"),
    @XmlEnumValue("Natural")
    NATURAL("Natural"),
    @XmlEnumValue("Fiction")
    FICTION("Fiction");
    private final String value;

    GenreType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GenreType fromValue(String v) {
        for (GenreType c: GenreType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
