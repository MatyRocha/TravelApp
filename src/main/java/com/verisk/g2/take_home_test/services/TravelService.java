package com.verisk.g2.take_home_test.services;

import com.verisk.g2.take_home_test.dao.AirlineDAO;
import com.verisk.g2.take_home_test.dao.AirportDAO;
import com.verisk.g2.take_home_test.dao.RouteDAO;
import com.verisk.g2.take_home_test.dto.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
            result = "Error on loading catalogs";
        } else {
            List<Route> routesOrigin = routesDAO.getRoutes().stream()
                    .filter(route-> route.getOrigin().equalsIgnoreCase(orign))
                    .collect(Collectors.toList());
            if (routesOrigin.isEmpty()) {
                result = "Invalid Origin";
            } else {
                List<Route> routesDest = routesDAO.getRoutes().stream()
                        .filter(route-> route.getDestination().equalsIgnoreCase(destination))
                        .collect(Collectors.toList());
                if (routesDest.isEmpty()){
                    result = "Invalid Destination";
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
        Iterator routeIterator = routesOrigin.iterator();
        while (routeIterator.hasNext()) {
            Route r = (Route) routeIterator.next();
            String step = r.getDestination();
            String option= origin + " -> " + step;
            options.add(option);
        }
        result = doNextSearch(options, origin, destination);
        return result;
    }

    private  String  doNextSearch(List<String> options, String origin, String destination) {
        String result = "";
        Iterator optionsIterator = options.iterator();
        List<String> nextOptions = new ArrayList<>();
        while (optionsIterator.hasNext()) {
            String o = (String ) optionsIterator.next();
            String[] a = o.split(" -> ");
            String nextSearch = a[a.length-1];
            List<Route> routesOrigin = routesDAO.getRoutes().stream()
                    .filter(route-> route.getOrigin().equalsIgnoreCase(nextSearch))
                    .collect(Collectors.toList());
            if (!routesOrigin.isEmpty()) {
                // Add all the options
                Iterator routeIterator = routesOrigin.iterator();
                while (routeIterator.hasNext()) {
                    Route r = (Route) routeIterator.next();
                    String step = r.getDestination();
                    if (!o.contains(step)) {
                        String nextO = o +  " -> " + step;
                        nextOptions.add(nextO);
                        if (step.equalsIgnoreCase(destination)) {
                            result = nextO;
                            break;
                        }
                    }
                }
            }
            if (!result.isEmpty()) {
                break;
            }
        }
        if (result.isEmpty() && !nextOptions.isEmpty()) {
            // keep looking
            result = doNextSearch(nextOptions, origin, destination);
        } else if (nextOptions.isEmpty() && result.isEmpty()) {
            // no more options, and no results yet
            result = "No route";
        }
        return result;
    }

    private boolean areValidCatalogs(){
        boolean result = false;
        if (airportsDAO != null && !airportsDAO.getAirports().isEmpty() &&
                airlinesDAO != null && !airlinesDAO.getAirlines().isEmpty() &&
                routesDAO != null && !routesDAO.getRoutes().isEmpty()) {
            result = true;
        }
        return result;
    }
}
