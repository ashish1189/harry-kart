package se.atg.service.harrykart.java.rest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
    private int position;
    private String horse;
}
