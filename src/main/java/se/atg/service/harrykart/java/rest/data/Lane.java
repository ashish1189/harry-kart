package se.atg.service.harrykart.java.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(FIELD)
public class Lane {
    @XmlAttribute(name = "number")
    private int number;
    @XmlValue
    private int power;
}
