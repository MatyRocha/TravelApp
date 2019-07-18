package com.verisk.g2.take_home_test.to;

import com.verisk.g2.take_home_test.dao.AirlineDAO;
import com.verisk.g2.take_home_test.dao.AirportDAO;
import com.verisk.g2.take_home_test.dao.RouteDAO;
import com.verisk.g2.take_home_test.dto.Airline;
import com.verisk.g2.take_home_test.dto.Airport;
import com.verisk.g2.take_home_test.dto.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class Travel {
    Step[] travel;
    @Autowired
    private AirportDAO airportsDAO;
    @Autowired
    private AirlineDAO airlinesDAO;
    @Autowired
    private RouteDAO routesDAO;


    public Travel(String steps) {
        String[] travelPoints = steps.split(" ->");


    }

    class Step{
        String origin;
        String destination;
        Route route;
        Airport airportOrigin;
        Airport airportDestination;
        Airline airline;

        public Step(String origin, String destination) {
            List<Route> stepRoute = routesDAO.getRoutes().stream()
                    .filter(route-> (route.getOrigin().equalsIgnoreCase(origin) &&
                                    route.getDestination().equalsIgnoreCase(destination)))
                    .collect(Collectors.toList());
            route = stepRoute.get(0);
            List<Airline> stepAirline = airlinesDAO.getAirlines().stream()
                    .filter(airline-> (airline.getCode2().equalsIgnoreCase(route.getAirline())))
                    .collect(Collectors.toList());

        }
    }


}
