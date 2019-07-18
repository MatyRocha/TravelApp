package com.verisk.g2.take_home_test.api;

import com.verisk.g2.take_home_test.services.TravelService;
import com.verisk.g2.take_home_test.to.Travel;
import com.verisk.g2.take_home_test.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TravelAPI {
    @Autowired
    TravelService travelService;

    @RequestMapping(value="/travel", method=RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Travel>> TravelAPI(@RequestParam String origin, @RequestParam String destination) {

        if (origin.isEmpty() || destination.isEmpty()) {
            List<Travel> err = new ArrayList<>();
            err.add( new Travel(Constants.MISSING_PARAMETERS_INFO));
            return new ResponseEntity<List<Travel>>(err, HttpStatus.BAD_REQUEST);
        }

        String shortRute = travelService.calculateShortRoute(origin, destination);
        String route[] = shortRute.split(" -> ");

        if (route.length < 2) {
            List<Travel> err = new ArrayList<>();
            err.add( new Travel(shortRute));

            if (route[0].equalsIgnoreCase(Constants.NO_ROUTE)) {
                return new ResponseEntity<List<Travel>>(err, HttpStatus.NOT_FOUND);
            } else if (route[0].equalsIgnoreCase(Constants.FILE_NOT_FOUND)) {
                return new ResponseEntity<List<Travel>>(err, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (route[0].equalsIgnoreCase(Constants.INVALID_ORIGIN) ||
                    route[0].equalsIgnoreCase(Constants.INVALID_DESTINATION) ) {
                return new ResponseEntity<List<Travel>>(err, HttpStatus.BAD_REQUEST);
            }
            // Any other error is handled as a bad request.
            return new ResponseEntity<List<Travel>>(err, HttpStatus.BAD_REQUEST);
        }

        // Route found.  Lets gonna expose all the info
        List<Travel> travel = travelService.getTravelInfo(shortRute);
        return new ResponseEntity<List<Travel>>(travel, HttpStatus.OK);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String message = ex.getParameterName() + Constants.MISSING_SPEC_PARAMETERS;
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }
}
