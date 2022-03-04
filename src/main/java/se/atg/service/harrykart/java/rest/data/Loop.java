package se.atg.service.harrykart.java.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(FIELD)
public class Loop {
    @XmlAttribute(name = "number")
    private int number;
    private List<Lane> lanes;
}
