package com.verisk.g2.take_home_test.services;

import com.verisk.g2.take_home_test.dao.AirlineDAO;
import com.verisk.g2.take_home_test.dao.AirportDAO;
import com.verisk.g2.take_home_test.dao.RouteDAO;
import com.verisk.g2.take_home_test.dto.Route;
import com.verisk.g2.take_home_test.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService {
    @Autowired
    private AirportDAO airportsDAO;
    @Autowired
    private AirlineDAO airlinesDAO;
    @Autowired
    private RouteDAO routesDAO;

    public String calculateShortRoute(String orign, String destination){
        String result = "";
        if ( !areValidCatalogs() ) {
            result = Constants.FILE_NOT_FOUND;
        } else {
            List<Route> routesOrigin = routesDAO.getRoutes().stream()
                    .filter(route-> route.getOrigin().equalsIgnoreCase(orign))
                    .collect(Collectors.toList());
            if (routesOrigin.isEmpty()) {
                result = Constants.INVALID_ORIGIN;
            } else {
                List<Route> routesDest = routesDAO.getRoutes().stream()
                        .filter(route-> route.getDestination().equalsIgnoreCase(destination))
                        .collect(Collectors.toList());
                if (routesDest.isEmpty()){
                    result = Constants.INVALID_DESTINATION;
                } else {
                    result = calculateRoute(routesOrigin, orign, destination);
                }
            }
        }
        return result;
    }

    private String calculateRoute(List<Route> routesOrigin, String origin, String destination){
        String result = "";
        List<Route> directRoute = routesOrigin.stream()
                .filter(route -> (route.getOrigin().equalsIgnoreCase(origin)
                                && route.getDestination().equalsIgnoreCase(destination)))
                .collect(Collectors.toList());
        if (directRoute.isEmpty()) {
            result = calculateComplexRoute(routesOrigin, origin, destination);
        } else {
            result = origin + " -> " + destination;
        }

        return result;
    }

    private  String calculateComplexRoute(List<Route> routesOrigin, String origin, String destination){
        String result = "";
        // Construct base list for searching
        List<String> options = new ArrayList<>();
        for (Route r : routesOrigin){
            String step = r.getDestination();
            String option= origin + " -> " + step;
            options.add(option);
        }
        result = doNextSearch(options, origin, destination);
        return result;
    }

    private  String  doNextSearch(List<String> options, String origin, String destination) {
        String result = "";
        List<String> nextOptions = new ArrayList<>();
        for (String o : options){
            String[] a = o.split(" -> ");
            String nextSearch = a[a.length-1];
            // Look for the last destination,as new origin (nextSearch)
            List<Route> routesOrigin = routesDAO.getRoutes().stream()
                    .filter(route-> route.getOrigin().equalsIgnoreCase(nextSearch))
                    .collect(Collectors.toList());
            if (!routesOrigin.isEmpty()) {
                // Add all the options
                for (Route r : routesOrigin){
                    String step = r.getDestination();
                    if (!o.contains(step)) {
                        String nextOrigin = o +  " -> " + step;
                        nextOptions.add(nextOrigin);
                        if (step.equalsIgnoreCase(destination)) {
                            // we found destination!
                            return (nextOrigin);
                        }
                    }
                }
            }
        }
        if (result.isEmpty() && !nextOptions.isEmpty()) {
            // keep looking
            result = doNextSearch(nextOptions, origin, destination);
        } else if (nextOptions.isEmpty() && result.isEmpty()) {
            // no more options, and no results yet
            result = Constants.NO_ROUTE;
        }
        return result;
    }

    private boolean areValidCatalogs(){
        return  (airportsDAO != null && !airportsDAO.getAirports().isEmpty() &&
                airlinesDAO != null && !airlinesDAO.getAirlines().isEmpty() &&
                routesDAO != null && !routesDAO.getRoutes().isEmpty());
    }
}
