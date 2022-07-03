package se.atg.service.harrykart.java.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@XmlAccessorType(FIELD)
public class Loop {
    //@XmlAttribute(name = "number")
    @JacksonXmlProperty(isAttribute = true)
    private int number;
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Lane> lane;
}
