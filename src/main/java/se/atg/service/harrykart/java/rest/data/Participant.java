package se.atg.service.harrykart.java.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private Integer lane;
    private String name;
    private Double baseSpeed;

    private Double lapTime;
}
