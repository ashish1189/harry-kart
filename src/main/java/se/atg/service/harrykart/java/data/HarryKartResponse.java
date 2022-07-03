package se.atg.service.harrykart.java.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HarryKartResponse {
    private List<Ranking> rankings;
}
