package se.atg.service.harrykart.java.rest.data;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Lane {
    @XmlAttribute(name = "number")
    private int number;
    @XmlValue
    private int power;
}
