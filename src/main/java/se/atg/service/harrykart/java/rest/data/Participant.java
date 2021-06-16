package se.atg.service.harrykart.java.rest.data;

import lombok.Data;

@Data
public class Participant {
    private int lane;
    private String name;
    private Double baseSpeed;
}
