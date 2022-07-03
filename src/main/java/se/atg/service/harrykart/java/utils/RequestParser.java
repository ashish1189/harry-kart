package se.atg.service.harrykart.java.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;
import se.atg.service.harrykart.java.data.HarryKart;
import se.atg.service.harrykart.java.exception.InternalServerError;

@Component
public class RequestParser {
    public HarryKart getHarryKart(String request) {
        HarryKart harryKart;
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            harryKart = xmlMapper.readValue(request, HarryKart.class);
        } catch (JsonProcessingException e) {
            throw new InternalServerError("Unable to process input XML");
        }
        return harryKart;
    }
}
