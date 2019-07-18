package com.verisk.g2.take_home_test.api;

import com.verisk.g2.take_home_test.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TravelAPI {
    @Autowired
    TravelService travelService;

    @RequestMapping(value="/travel", method=RequestMethod.GET)
    public String TravelAPI(@RequestParam String origin, @RequestParam String destination) {

        return travelService.calculateShortRoute(origin, destination);
    }
}
