package se.atg.service.harrykart.java.rest.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import se.atg.service.harrykart.java.rest.data.*;
import se.atg.service.harrykart.java.rest.exception.EmptyStartListException;
import se.atg.service.harrykart.java.rest.exception.InsufficientRaceLapData;
import se.atg.service.harrykart.java.rest.exception.NoRaceParticipantsException;
import se.atg.service.harrykart.java.rest.exception.NotEnoughParticipants;
import se.atg.service.harrykart.java.rest.service.HarryKartService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingDouble;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;
import static se.atg.service.harrykart.java.rest.utils.Constants.TRACK_LENGTH;

@Service
@RequestScope
public class HarryKartServiceImpl implements HarryKartService {
    @Override
    public HarryKartResponse playHarryKartRace(HarryKart harryKart) {
        validateHarryKartRequest(harryKart);

        var standings = getStandings(harryKart);
        var rankings = getRankings(standings);

        return new HarryKartResponse(rankings);
    }

    private void validateHarryKartRequest(HarryKart harryKart) {
        if (null == harryKart.getStartList()) {
            throw new EmptyStartListException("Mandatory starter list not provided for the race.");
        }

        if (null == harryKart.getStartList().getParticipant() || harryKart.getStartList().getParticipant().isEmpty()) {
            throw new NoRaceParticipantsException("No starter participants provided for the race.");
        }

        if (harryKart.getStartList().getParticipant().size() < 4) {
            throw new NotEnoughParticipants("Not enough participants for the race. At least 4 should participate.");
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
    private Map<Integer, Participant> getStandings(HarryKart harryKart) {
        var standings =
                harryKart
                        .getStartList()
                        .getParticipant()
                        .stream()
                        .collect(toMap(Participant::getLane, p -> new Participant(p.getLane(), p.getName(),
                                p.getBaseSpeed(), TRACK_LENGTH / p.getBaseSpeed())));

        runRaceLoops(harryKart, standings);

        return standings
                .entrySet()
                .stream()
                .sorted(comparingByValue(
                        comparingDouble(Participant::getLapTime)
                ))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    private void runRaceLoops(HarryKart harryKart, Map<Integer, Participant> standings) {
        for (Loop loop : harryKart.getPowerUps().getLoop()) {
            for (Lane lane : loop.getLane()) {
                var participant = standings.get(lane.getNumber());
                participant.setBaseSpeed(standings.get(lane.getNumber()).getBaseSpeed() + lane.getPower());
                participant.setLapTime(participant.getLapTime() + (TRACK_LENGTH / participant.getBaseSpeed()));
                standings.put(lane.getNumber(), participant);
            }
        }
    }

    @NotNull
    private List<Ranking> getRankings(Map<Integer, Participant> standings) {
        List<Ranking> rankings = new ArrayList<>();
        for (var entry : standings.entrySet()) {
            var ranking = new Ranking(rankings.size() + 1, standings.get(entry.getKey()).getName());
            rankings.add(ranking);
            if (rankings.size() == 3)
                break;
        }
        return rankings;
    }
}
