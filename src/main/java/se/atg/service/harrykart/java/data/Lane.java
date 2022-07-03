package se.atg.service.harrykart.java.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@XmlAccessorType(FIELD)
public class Lane {
    //@XmlAttribute(name = "number")
    @JacksonXmlProperty(isAttribute = true)
    private int number;
    @JacksonXmlText
    private int power;
}
