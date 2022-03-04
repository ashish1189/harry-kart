package se.atg.service.harrykart.java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import se.atg.service.harrykart.java.rest.exception.EmptyStartListException;
import se.atg.service.harrykart.java.rest.exception.InsufficientRaceLapData;
import se.atg.service.harrykart.java.rest.exception.NoRaceParticipantsException;
import se.atg.service.harrykart.java.rest.exception.NotEnoughParticipants;
import se.atg.service.harrykart.java.rest.service.impl.HarryKartServiceImpl;
import se.atg.service.harrykart.java.utils.MockDataUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HarryKartServiceImplTest {

    @InjectMocks
    HarryKartServiceImpl harryKartService;

    @Test
    @DisplayName("Successful response builder test")
    void testPlayHarryKartRace() {
        var harryKart = MockDataUtils.getHarryKart().get(1);

        var response = harryKartService.playHarryKartRace(harryKart);

        assertEquals(3, response.getRankings().size());
        assertEquals("DON JUAN", response.getRankings().get(0).getHorse());
        assertEquals("SPIRIT", response.getRankings().get(1).getHorse());
        assertEquals("SILVER LINING", response.getRankings().get(2).getHorse());
    }

    @Test
    @DisplayName("Start up information not provided for race")
    void testNoStartList() {
        var harryKart = MockDataUtils.getHarryKart().get(1);
        harryKart.setStartList(null);

        assertThrows(EmptyStartListException.class, () -> {
            harryKartService.playHarryKartRace(harryKart);
        });
    }

    @Test
    @DisplayName("No or Empty participants list")
    void testNoParticipantsFound() {
        var harryKart = MockDataUtils.getHarryKart().get(1);
        harryKart.getStartList().setParticipants(null);

        assertThrows(NoRaceParticipantsException.class, () -> {
            harryKartService.playHarryKartRace(harryKart);
        });
    }

    @Test
    @DisplayName("Insufficient lap/loops information provided for evaluation")
    void testInSufficientLapData() {
        var harryKart = MockDataUtils.getHarryKart().get(1);
        harryKart.setNumberOfLoops(4);

        assertThrows(InsufficientRaceLapData.class, () -> {
            harryKartService.playHarryKartRace(harryKart);
        });
    }

    @Test
    @DisplayName("Less than 4 participants for the race")
    void testNotEnoughParticipants() {
        var harryKart = MockDataUtils.getHarryKart().get(1);
        harryKart.getStartList().getParticipants().remove(3);

        assertThrows(NotEnoughParticipants.class, () -> {
            harryKartService.playHarryKartRace(harryKart);
        });
    }
}
