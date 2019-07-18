package com.verisk.g2.take_home_test.to;

import com.verisk.g2.take_home_test.dto.Airline;
import com.verisk.g2.take_home_test.dto.Airport;
import com.verisk.g2.take_home_test.dto.Route;
import com.verisk.g2.take_home_test.util.Constants;

import java.io.Serializable;

public class Travel implements Serializable {
    private String message;
    private Route route;
    private Airport airportOrigin;
    private Airport airportDestination;
    private Airline airline;

    public Travel(Route route, Airport airportOrigin, Airport airportDestination, Airline airline) {
        this.message = Constants.SUCCESS;
        this.route = route;
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
        this.airline = airline;
    }

    public Travel(String message) {
        // For Handling errors
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airport getAirportOrigin() {
        return airportOrigin;
    }

    public void setAirportOrigin(Airport airportOrigin) {
        this.airportOrigin = airportOrigin;
    }

    public Airport getAirportDestination() {
        return airportDestination;
    }

    public void setAirportDestination(Airport airportDestination) {
        this.airportDestination = airportDestination;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

/*    public Travel(String steps) {
        travel = new ArrayList<Step>();
        String[] travelPoints = steps.split(" ->");
        int length = travelPoints.length;
        for(int i=0; i<travelPoints.length-2; ++i){
            Step step = new Step(travelPoints[i],travelPoints[i+1]);
            travel.add(step);
        }
    }*/


/*    class Step{

        public Step(String origin, String destination) {
            List<Route> a = routesDAO.getRoutes();
            List<Airport> b = airportsDAO.getAirports();
            List<Airline> c = airlinesDAO.getAirlines();

            List<Route> stepRoute = routesDAO.getRoutes().stream()
                    .filter(route-> (route.getOrigin().equalsIgnoreCase(origin) &&
                                    route.getDestination().equalsIgnoreCase(destination)))
                    .collect(Collectors.toList());
            route = stepRoute.get(0);
            List<Airline> stepAirline = airlinesDAO.getAirlines().stream()
                    .filter(airline-> (airline.getCode2().equalsIgnoreCase(route.getAirline())))
                    .collect(Collectors.toList());
            airline = stepAirline.get(0);
            List<Airport> stepAiportOrigin = airportsDAO.getAirports().stream()
                    .filter(airport-> (airport.getIata3().equalsIgnoreCase(origin)))
                    .collect(Collectors.toList());
            airportOrigin = stepAiportOrigin.get(0);
            List<Airport> stepAiportDest = airportsDAO.getAirports().stream()
                    .filter(airport-> (airport.getIata3().equalsIgnoreCase(destination)))
                    .collect(Collectors.toList());
            airportDestination = stepAiportDest.get(0);
        }
    }
*/

}
