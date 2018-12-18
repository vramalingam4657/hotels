package com.nl.hotels.controller;
import com.nl.hotels.dto.HotelsData;
import com.nl.hotels.dto.HotelsDataResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@RestController
@ComponentScan
public class HotelsController {

    @GET
    @Produces("text/json")
    @RequestMapping("/hotels")
    public ArrayList<HotelsData> getHotels(
            @RequestParam(name = "airport", defaultValue = "YVR") String airport,
            @RequestParam(name = "date", defaultValue = "2019-10-30") String date
            ) {
        ArrayList<HotelsData> testData = new ArrayList<HotelsData>();

        RestTemplate restTemplate = new RestTemplate();
        String amadeusUrl = "https://sandbox.amadeus.com/travel-innovation-sandbox/apis/get/Hotels/search-airport"
                + "?airport=" + airport + "&date=" + date;

        System.out.println("Calling amadeus url = " + amadeusUrl);
        ResponseEntity<String> response
                = restTemplate.getForEntity(amadeusUrl, String.class);
        assert(response.getStatusCode() == HttpStatus.OK);

        // **** NOTE*** To test, just pass the full json response as the hotel name of the first test hotel record.
        testData.add(new HotelsData(response.getBody(), "address1", "phone1", 50.00));

        return testData;
    }

    @GET
    @Produces("text/json")
    @RequestMapping("/hotelsTest")
    public HotelsDataResponse getFlightsTestData() {
        ArrayList<HotelsData> testData = new ArrayList<HotelsData>();
        testData.add(new HotelsData("name1", "address1", "phone1", 50.00));
        testData.add(new HotelsData("name2", "address2", "phone2", 70.99));
        testData.add(new HotelsData("name3", "address3", "phone3", 250.01));

        HotelsDataResponse response = new HotelsDataResponse(testData);
        return response;
    }
}
