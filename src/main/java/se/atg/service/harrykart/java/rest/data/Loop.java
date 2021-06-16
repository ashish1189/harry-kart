package se.atg.service.harrykart.java.rest.data;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Loop {
    @XmlAttribute(name = "number")
    private int number;
    private List<Lane> lane;
}
