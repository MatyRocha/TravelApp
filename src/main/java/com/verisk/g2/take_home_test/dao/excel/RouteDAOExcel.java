package com.verisk.g2.take_home_test.dao.excel;

import com.verisk.g2.take_home_test.dao.RouteDAO;
import com.verisk.g2.take_home_test.dto.Route;
import com.verisk.g2.take_home_test.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class RouteDAOExcel implements RouteDAO {
    private static List<Route> routes;
    Logger logger = LoggerFactory.getLogger(RouteDAOExcel.class);

    public  List<Route> getRoutes(){
        if (routes == null) {
            getRoutesFromExcel();
        }
        return  routes;
    }

    private void getRoutesFromExcel() {
        routes = new ArrayList<Route>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.ROUTE_FILENAME);
        try (Scanner scanner = new Scanner(inputStream) ) {
            scanner.nextLine(); // skip first row, which is the title
            while (scanner.hasNextLine()) {
                addRoute(scanner.nextLine());
            }
        } catch (NullPointerException ex) {
            routes.clear();
            logger.error( "Problems opening " + Constants.ROUTE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (IllegalArgumentException ex) {
            routes.clear();
            logger.error( "Problems opening " + Constants.ROUTE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (IllegalStateException ex) {
            routes.clear();
            logger.error( "Problems reading " + Constants.ROUTE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (NoSuchElementException ex) {
            routes.clear();
            logger.error( "Problems reading " + Constants.ROUTE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        }
    }

    private void addRoute(String record) throws NoSuchElementException, IllegalStateException {
        Scanner row = new Scanner(record);
        row.useDelimiter("\\s*,\\s*");
        Route route = new Route(row.next(), row.next(), row.next());
        routes.add(route);
    }

}
