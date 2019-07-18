package com.verisk.g2.take_home_test.api;

import com.verisk.g2.take_home_test.services.TravelService;
import com.verisk.g2.take_home_test.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import sun.plugin.javascript.navig.Array;

@RestController
public class TravelAPI {
    @Autowired
    TravelService travelService;

    @RequestMapping(value="/travel", method=RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String[]> TravelAPI(@RequestParam String origin, @RequestParam String destination) {
        if (origin.isEmpty() || destination.isEmpty()) {
            return new ResponseEntity<String[]>(Constants.MISSING_PARAMETERS_INFO.split(" -> "), HttpStatus.BAD_REQUEST);
        }
        String shortRute = travelService.calculateShortRoute(origin, destination);
        String route[] = shortRute.split(" -> ");

        if (route.length < 2) {
            if (route[0].equalsIgnoreCase(Constants.NO_ROUTE)) {
                return new ResponseEntity<String[]>(route, HttpStatus.NOT_FOUND);
            } else if (route[0].equalsIgnoreCase(Constants.FILE_NOT_FOUND)) {
                return new ResponseEntity<String[]>(route, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (route[0].equalsIgnoreCase(Constants.INVALID_ORIGIN) ||
                    route[0].equalsIgnoreCase(Constants.INVALID_DESTINATION) ) {
                return new ResponseEntity<String[]>(route, HttpStatus.BAD_REQUEST);
            }
            // Any other error is handled as a bad request.
            return new ResponseEntity<String[]>(route, HttpStatus.BAD_REQUEST);
        }

        // Route found.
        return new ResponseEntity<String[]>(route, HttpStatus.OK);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        String message = ex.getParameterName() + Constants.MISSING_SPEC_PARAMETERS;
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }
}
