package se.atg.service.harrykart.java.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "harryKart")
public class HarryKart {

    private int numberOfLoops;
    private StartList startList;
    private PowerUps powerUps;
}
