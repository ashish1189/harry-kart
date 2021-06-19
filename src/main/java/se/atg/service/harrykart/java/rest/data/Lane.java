package se.atg.service.harrykart.java.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Lane {
    @XmlAttribute(name = "number")
    private int number;
    @XmlValue
    private int power;
}
