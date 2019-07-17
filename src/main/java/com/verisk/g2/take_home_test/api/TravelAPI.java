package com.verisk.g2.take_home_test.api;

import com.verisk.g2.take_home_test.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TravelAPI {
    @Autowired
    TravelService travelService;

    @RequestMapping(value="/travel", method=RequestMethod.GET)
    public String TravelAPI(@RequestParam String origin, @RequestParam String destination) {

        return travelService.calculateShortRoute(origin, destination);
    }
}
