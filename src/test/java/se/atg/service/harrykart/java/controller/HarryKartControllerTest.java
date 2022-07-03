package se.atg.service.harrykart.java.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import se.atg.service.harrykart.java.data.HarryKartResponse;
import se.atg.service.harrykart.java.utils.MockDataUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HarryKartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Successful service response test.")
    void testPlayHarryKartRacing() throws Exception {
        String requestBody = MockDataUtils.getHarryKartRace1();

        MvcResult mvcResult = mockMvc.perform(post("/java/api/play")
                        .content(requestBody)
                        .contentType(APPLICATION_XML_VALUE)
                        .accept(ALL_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertEquals(APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
    }

    @Test
    @DisplayName("Successful response body validation tests.")
    void testHarryKartStandings() throws Exception {
        String requestBody = MockDataUtils.getHarryKartRace1();

        MvcResult mvcResult = mockMvc.perform(post("/java/api/play")
                        .content(requestBody)
                        .contentType(APPLICATION_XML_VALUE)
                        .accept(ALL_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        var response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), HarryKartResponse.class);

        assertEquals(3, response.getRankings().size());
        assertEquals("TIMETOBELUCKY", response.getRankings().get(0).getHorse());
        assertEquals("HERCULES BOKO", response.getRankings().get(1).getHorse());
        assertEquals("CARGO DOOR", response.getRankings().get(2).getHorse());
    }

    @Test
    @DisplayName("Response code test for no startup information")
    void testEmptyStartListException() throws Exception {
        String requestBody = MockDataUtils.getHarryKartRace2();

        MvcResult mvcResult = mockMvc.perform(post("/java/api/play")
                        .content(requestBody)
                        .contentType(APPLICATION_XML_VALUE)
                        .accept(ALL_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("Response code test for insufficient race information")
    void testInsufficientRaceLapData() throws Exception {
        String requestBody = MockDataUtils.getHarryKartRace3();

        MvcResult mvcResult = mockMvc.perform(post("/java/api/play")
                        .content(requestBody)
                        .contentType(APPLICATION_XML_VALUE)
                        .accept(ALL_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    @DisplayName("Response code test for not enough participants")
    void testNotEnoughParticipants() throws Exception {
        String requestBody = MockDataUtils.getHarryKartRace4();

        MvcResult mvcResult = mockMvc.perform(post("/java/api/play")
                        .content(requestBody)
                        .contentType(APPLICATION_XML_VALUE)
                        .accept(ALL_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }
}
