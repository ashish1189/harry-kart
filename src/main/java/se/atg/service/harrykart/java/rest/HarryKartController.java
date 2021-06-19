package se.atg.service.harrykart.java.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.atg.service.harrykart.java.rest.data.HarryKart;
import se.atg.service.harrykart.java.rest.data.HarryKartResponse;
import se.atg.service.harrykart.java.rest.service.HarryKartService;

@RestController
@RequestMapping("/java/api")
@Api(value = "Harry kart racing game service")
public class HarryKartController {

    private final HarryKartService harryKartService;

    public HarryKartController (HarryKartService harryKartService) {
        this.harryKartService = harryKartService;
    }

    @PostMapping(path = "/play", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Evaluates harry kart racing game result for given input",
        response = HarryKartResponse.class)
    public ResponseEntity<HarryKartResponse> playHarryKart(@RequestBody HarryKart harryKart) {

        var response = harryKartService.playHarryKartRace(harryKart);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
