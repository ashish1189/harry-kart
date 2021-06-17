package se.atg.service.harrykart.java.rest.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import se.atg.service.harrykart.java.rest.data.*;
import se.atg.service.harrykart.java.rest.exception.EmptyStartListException;
import se.atg.service.harrykart.java.rest.exception.InsufficientRaceLapData;
import se.atg.service.harrykart.java.rest.service.HarryKartService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;
import static se.atg.service.harrykart.java.rest.utils.Constants.TRACK_LENGTH;

@Service
@RequestScope
public class HarryKartServiceImpl implements HarryKartService {
    @Override
    public HarryKartResponse playHarryKartRace(HarryKart harryKart) {
        validateHarryKartRequest(harryKart);

        var horseLane = getHorseLane(harryKart);
        var standings = getStandings(horseLane, harryKart);
        var rankings = getRankings(horseLane, standings);

        return new HarryKartResponse(rankings);
    }

    private void validateHarryKartRequest(HarryKart harryKart) {
        if (null == harryKart.getStartList().getParticipant() || harryKart.getStartList().getParticipant().isEmpty()) {
            throw new EmptyStartListException("No starter participants provided for the race.");
        }

        int count = (harryKart.getStartList().getParticipant().isEmpty() ? 0 : 1) + harryKart.getPowerUps().getLoop().size();
        if (harryKart.getNumberOfLoops() != count) {
            throw new InsufficientRaceLapData("Insufficient laps information available to play and determine the positions.");
        }

        for (Loop loop : harryKart.getPowerUps().getLoop()) {
            if (harryKart.getNumberOfLoops() > 1 && (harryKart.getStartList().getParticipant().size() !=
                    loop.getLane().size())) {
                throw new InsufficientRaceLapData("Insufficient laps information available to play and determine the positions.");
            }
        }
    }

    @NotNull
    private Map<Integer, Participant> getHorseLane(HarryKart harryKart) {
        return harryKart
                .getStartList()
                .getParticipant()
                .stream()
                .collect(toMap(Participant::getLane, Function.identity()));
    }

    @NotNull
    private Map<Integer, Double> getStandings(Map<Integer, Participant> horseLane, HarryKart harryKart) {
        var standings =
                harryKart
                        .getStartList()
                        .getParticipant()
                        .stream()
                        .collect(toMap(Participant::getLane,
                                participant -> TRACK_LENGTH / participant.getBaseSpeed()));

        runRaceLoops(horseLane, harryKart, standings);

        return standings
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)
                );
    }

    private void runRaceLoops(Map<Integer, Participant> horseLane, HarryKart harryKart, Map<Integer, Double> standings) {
        for (Loop loop : harryKart.getPowerUps().getLoop()) {
            double power;
            for (Lane lane : loop.getLane()) {
                power = horseLane.get(lane.getNumber()).getBaseSpeed() + lane.getPower();
                standings.put(lane.getNumber(), standings.get(lane.getNumber()) + (TRACK_LENGTH / power));

                var participant = horseLane.get(lane.getNumber());
                participant.setBaseSpeed(power);
                horseLane.put(lane.getNumber(), participant);
            }
        }
    }

    @NotNull
    private List<Ranking> getRankings(Map<Integer, Participant> horseLane, Map<Integer, Double> standings) {
        List<Ranking> rankings = new ArrayList<>();
        for (var entry : standings.entrySet()) {
            var ranking = new Ranking(rankings.size() + 1, horseLane.get(entry.getKey()).getName());
            rankings.add(ranking);
            if (rankings.size() == 3)
                break;
        }
        return rankings;
    }
}
