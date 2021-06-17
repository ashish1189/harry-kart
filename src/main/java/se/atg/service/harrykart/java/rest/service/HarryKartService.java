package se.atg.service.harrykart.java.rest.service;

import se.atg.service.harrykart.java.rest.data.HarryKart;
import se.atg.service.harrykart.java.rest.data.HarryKartResponse;

public interface HarryKartService {
    HarryKartResponse playHarryKartRace(HarryKart harryKart);
}
