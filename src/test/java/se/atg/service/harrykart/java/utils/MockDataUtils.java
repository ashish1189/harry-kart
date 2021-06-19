package se.atg.service.harrykart.java.utils;

import se.atg.service.harrykart.java.rest.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDataUtils {

    public static Map<Integer, List<Lane>> getLanes() {
        List<Lane> lanes = new ArrayList<>();
        Map<Integer, List<Lane>> map = new HashMap<>();

        lanes.add(new Lane(1, 1));
        lanes.add(new Lane(2, 1));
        lanes.add(new Lane(3, 0));
        lanes.add(new Lane(4, -2));

        map.put(1, lanes);

        lanes = new ArrayList<>();
        lanes.add(new Lane(1, 1));
        lanes.add(new Lane(2, -1));
        lanes.add(new Lane(3, 2));
        lanes.add(new Lane(4, -2));
        map.put(2, lanes);

        return map;
    }

    public static Map<Integer, List<Loop>> getLoops() {
        Map<Integer, List<Loop>> map = new HashMap<>();

        List<Loop> loops = new ArrayList<>();
        loops.add(new Loop(1, getLanes().get(1)));
        loops.add(new Loop(2, getLanes().get(2)));
        map.put(1, loops);

        return map;
    }

    public static Map<Integer, PowerUps> getPowerUps() {
        Map<Integer, PowerUps> map = new HashMap<>();

        map.put(1, new PowerUps(getLoops().get(1)));

        return map;
    }

    public static Map<Integer, List<Participant>> getParticipants() {
        Map<Integer, List<Participant>> map = new HashMap<>();

        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant(1, "DON JUAN", 10.0, null));
        participants.add(new Participant(2, "SILVER LINING", 10.0, null));
        participants.add(new Participant(3, "SPIRIT", 10.0, null));
        participants.add(new Participant(4, "SUNSHINE", 10.0, null));
        map.put(1, participants);

        return map;
    }

    public static StartList getStartList() {
        return new StartList(getParticipants().get(1));
    }

    public static Map<Integer, HarryKart> getHarryKart() {
        Map<Integer, HarryKart> map = new HashMap<>();

        map.put(1, new HarryKart(3, getStartList(), getPowerUps().get(1)));

        return map;
    }

    public static String getHarryKartRace1() {
        return "<harryKart>\n" +
                "    <numberOfLoops>3</numberOfLoops>\n" +
                "    <startList>\n" +
                "        <participant>\n" +
                "            <lane>1</lane>\n" +
                "            <name>TIMETOBELUCKY</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>2</lane>\n" +
                "            <name>CARGO DOOR</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>3</lane>\n" +
                "            <name>HERCULES BOKO</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>4</lane>\n" +
                "            <name>WAIKIKI SILVIO</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "    </startList>\n" +
                "    <powerUps>\n" +
                "        <loop number=\"1\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">1</lane>\n" +
                "            <lane number=\"3\">0</lane>\n" +
                "            <lane number=\"4\">-2</lane>\n" +
                "        </loop>\n" +
                "        <loop number=\"2\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">-1</lane>\n" +
                "            <lane number=\"3\">2</lane>\n" +
                "            <lane number=\"4\">-2</lane>\n" +
                "        </loop>\n" +
                "    </powerUps>\n" +
                "</harryKart>";
    }

    public static String getHarryKartRace2() {
        return "<harryKart>\n" +
                "    <numberOfLoops>3</numberOfLoops>\n" +
                "    <startList>\n" +
                "    </startList>\n" +
                "    <powerUps>\n" +
                "        <loop number=\"1\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">1</lane>\n" +
                "            <lane number=\"3\">0</lane>\n" +
                "            <lane number=\"4\">-2</lane>\n" +
                "        </loop>\n" +
                "        <loop number=\"2\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">-1</lane>\n" +
                "            <lane number=\"3\">2</lane>\n" +
                "            <lane number=\"4\">-2</lane>\n" +
                "        </loop>\n" +
                "    </powerUps>\n" +
                "</harryKart>";
    }

    public static String getHarryKartRace3() {
        return "<harryKart>\n" +
                "    <numberOfLoops>4</numberOfLoops>\n" +
                "    <startList>\n" +
                "        <participant>\n" +
                "            <lane>1</lane>\n" +
                "            <name>TIMETOBELUCKY</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>2</lane>\n" +
                "            <name>CARGO DOOR</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>3</lane>\n" +
                "            <name>HERCULES BOKO</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>4</lane>\n" +
                "            <name>WAIKIKI SILVIO</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "    </startList>\n" +
                "    <powerUps>\n" +
                "        <loop number=\"1\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">1</lane>\n" +
                "            <lane number=\"3\">0</lane>\n" +
                "            <lane number=\"4\">-2</lane>\n" +
                "        </loop>\n" +
                "        <loop number=\"2\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">-1</lane>\n" +
                "            <lane number=\"3\">2</lane>\n" +
                "            <lane number=\"4\">-2</lane>\n" +
                "        </loop>\n" +
                "    </powerUps>\n" +
                "</harryKart>";
    }

    public static String getHarryKartRace4() {
        return "<harryKart>\n" +
                "    <numberOfLoops>3</numberOfLoops>\n" +
                "    <startList>\n" +
                "        <participant>\n" +
                "            <lane>1</lane>\n" +
                "            <name>TIMETOBELUCKY</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>2</lane>\n" +
                "            <name>CARGO DOOR</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>3</lane>\n" +
                "            <name>HERCULES BOKO</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "    </startList>\n" +
                "    <powerUps>\n" +
                "        <loop number=\"1\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">1</lane>\n" +
                "            <lane number=\"3\">0</lane>\n" +
                "        </loop>\n" +
                "        <loop number=\"2\">\n" +
                "            <lane number=\"1\">1</lane>\n" +
                "            <lane number=\"2\">-1</lane>\n" +
                "            <lane number=\"3\">2</lane>\n" +
                "        </loop>\n" +
                "    </powerUps>\n" +
                "</harryKart>";
    }
}
