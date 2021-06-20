package se.atg.service.harrykart.java.rest.service.impl;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@RequestScope
public class HarryKartServiceImpl implements HarryKartService {
    @Override
    public HarryKartResponse playHarryKartRace(HarryKart harryKart) {
        log.info("Inside play harry-kart service");

        log.debug("Validating input for required data to compute the race.");
        validateHarryKartRequest(harryKart);
        log.debug("Input validation successful.");

        log.debug("Evaluating the standings by running all the loops/laps.");
        var standings = getStandings(harryKart);
        log.debug("Ordered standings for the harry-kart race - {} ", standings);

        log.debug("Populating service response");
        var rankings = getRankings(standings);
        log.debug("Response - {} ", rankings);

        log.info("Returning harry-kart service response.");
        return new HarryKartResponse(rankings);
    }

    private void validateHarryKartRequest(HarryKart harryKart) {
        log.debug("Checking for starter list in the input");
        if (null == harryKart.getStartList()) {
            throw new EmptyStartListException("Mandatory starter list not provided for the race.");
        }

        log.debug("Validating participants for the race.");
        if (null == harryKart.getStartList().getParticipant() || harryKart.getStartList().getParticipant().isEmpty()) {
            throw new NoRaceParticipantsException("No starter participants provided for the race.");
        }

        log.debug("Validating if at least 4 participants are running the race");
        if (harryKart.getStartList().getParticipant().size() < 4) {
            throw new NotEnoughParticipants("Not enough participants for the race. At least 4 should participate.");
        }
        log.debug("Starter list validation successful");

        log.debug("Validating if sufficient Lap information is provided to compuete the results");
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
        log.debug("Lap information validation successful.");
    }

    @NotNull
    private Map<Integer, Participant> getStandings(HarryKart harryKart) {
        log.debug("Evaluating base standings after running first lap.");
        var standings =
                harryKart
                        .getStartList()
                        .getParticipant()
                        .stream()
                        .collect(toMap(Participant::getLane, p -> new Participant(p.getLane(), p.getName(),
                                p.getBaseSpeed(), TRACK_LENGTH / p.getBaseSpeed())));
        log.debug("Standings after running the first lap - {} ", standings);

        log.debug("Running rest of the laps/loops");
        runRaceLoops(harryKart, standings);
        log.debug("Standings after running all the laps - {} ", standings);

        log.debug("Returning standings by sorting in ascending order of total lap time.");
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
        Participant participant = null;
        int position = 0;
        for (var entry : standings.entrySet()) {
            if (participant == null) {
                participant = entry.getValue();
                Ranking ranking = new Ranking(++position, participant.getName());
                rankings.add(ranking);
                continue;
            }

            if (participant.getLapTime().equals(entry.getValue().getLapTime())) {
                participant = entry.getValue();
                Ranking ranking = new Ranking(position, participant.getName());
                rankings.add(ranking);
                continue;
            }

            ++position;

            if (position > 3)
                break;

            participant = entry.getValue();
            Ranking ranking = new Ranking(position, participant.getName());
            rankings.add(ranking);
        }
        return rankings;
    }
}
