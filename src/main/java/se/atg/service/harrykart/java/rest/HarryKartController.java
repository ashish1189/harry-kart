package se.atg.service.harrykart.java.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atg.service.harrykart.java.data.HarryKart;
import se.atg.service.harrykart.java.data.HarryKartResponse;
import se.atg.service.harrykart.java.exception.InternalServerError;
import se.atg.service.harrykart.java.service.HarryKartService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Slf4j
@RestController
@RequestMapping("/java/api")
@Api(value = "Harry kart racing game service")
public class HarryKartController {

    private final HarryKartService harryKartService;

    public HarryKartController (HarryKartService harryKartService) {
        this.harryKartService = harryKartService;
    }

    @PostMapping(path = "/play", consumes = APPLICATION_XML_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Evaluates harry kart racing game result for given input",
            response = HarryKartResponse.class)
    public ResponseEntity<HarryKartResponse> playHarryKart(@RequestBody String request) {
        log.info("Starting harry kart race for the participants provided");
        HarryKart harryKart;
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
            harryKart = xmlMapper.readValue(request, HarryKart.class);
        } catch (JsonProcessingException e) {
            throw new InternalServerError("Unable to process input XML");
        }
        var response = harryKartService.playHarryKartRace(harryKart);

        log.info("Returning success response from harry-kart service");
        return ResponseEntity.status(CREATED).body(response);
    }

}
