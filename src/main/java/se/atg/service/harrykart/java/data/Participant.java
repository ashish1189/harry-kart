package se.atg.service.harrykart.java.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @JacksonXmlProperty
    private Integer lane;
    @JacksonXmlProperty
    private String name;
    @JacksonXmlProperty
    private Double baseSpeed;

    private Double lapTime = 0.0;
}
