package se.atg.service.harrykart.java.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class HarryKart {

    @JacksonXmlProperty
    private int numberOfLoops;
    @JacksonXmlProperty
    private StartList startList;
    @JacksonXmlProperty
    private PowerUps powerUps;
}
