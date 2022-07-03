package se.atg.service.harrykart.java.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartList {
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Participant> participant = null;
}
