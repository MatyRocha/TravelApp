package com.verisk.g2.take_home_test.dao.excel;

import com.verisk.g2.take_home_test.dao.RouteDAO;
import com.verisk.g2.take_home_test.dto.Route;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class RouteDAOExcel implements RouteDAO {
    private static List<Route> routes;
    private static final String ROUTE_FILENAME = "routes.csv";
//    private static final String ROUTE_FILENAME = "routesTest.csv";

    public  List<Route> getRoutes(){
        if (routes == null) {
            getRoutesFromExcel();
        }
        return  routes;
    }

    private void getRoutesFromExcel() {
        routes = new ArrayList<Route>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ROUTE_FILENAME);
        try (Scanner scanner = new Scanner(inputStream) ) {
            scanner.nextLine(); // skip first row, which is the title
            while (scanner.hasNextLine()) {
                addRoute(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addRoute(String record) {
        try (Scanner row = new Scanner(record)) {
            row.useDelimiter("\\s*,\\s*");
            Route route = new Route(row.next(), row.next(), row.next());
            routes.add(route);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
