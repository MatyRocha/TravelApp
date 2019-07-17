package com.verisk.g2.take_home_test.dao.excel;

import com.verisk.g2.take_home_test.dao.AirportDAO;
import com.verisk.g2.take_home_test.dto.Airport;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class AirportDAOExcel implements AirportDAO {
    private static List<Airport> airports;
    private static final String AIRPORT_FILENAME = "airports.csv";

    public  List<Airport> getAirports(){
        if (airports == null) {
            getAirportsFromExcel();
        }
        return  airports;
    }

    private void getAirportsFromExcel() {
        airports = new ArrayList<Airport>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(AIRPORT_FILENAME);
        try (Scanner scanner = new Scanner(inputStream) ) {
            scanner.nextLine(); // skip first row, which is the title
            while (scanner.hasNextLine()) {
                addAirport(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAirport(String record) {
        try (Scanner row = new Scanner(record)) {
            row.useDelimiter("\\s*,\\s*");
            Airport airport = new Airport(row.next(), row.next(), row.next(), row.next(), row.next(), row.next());
            airports.add(airport);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
