package se.atg.service.harrykart.java.rest.data;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "harryKart")
@Data
public class HarryKart {

    private int numberOfLoops;
    private StartList startList;
    private PowerUps powerUps;
}
