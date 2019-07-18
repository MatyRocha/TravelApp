package com.verisk.g2.take_home_test.dao.excel;

import com.verisk.g2.take_home_test.dao.AirportDAO;
import com.verisk.g2.take_home_test.dto.Airport;
import com.verisk.g2.take_home_test.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class AirportDAOExcel implements AirportDAO {
    private static List<Airport> airports;
    Logger logger = LoggerFactory.getLogger(AirportDAOExcel.class);

    public  List<Airport> getAirports(){
        if (airports == null) {
            getAirportsFromExcel();
        }
        return  airports;
    }

    private void getAirportsFromExcel() {
        airports = new ArrayList<Airport>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.AIRPORT_FILENAME) ) {
            Scanner scanner = new Scanner(inputStream);
            scanner.nextLine(); // skip first row, which is the title
            while (scanner.hasNextLine()) {
                addAirport(scanner.nextLine());
            }
            inputStream.close();
        } catch (IOException ex) {
            airports.clear();
            logger.error( "Problems opening " + Constants.AIRPORT_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (NullPointerException ex) {
            airports.clear();
            logger.error( "Problems opening " + Constants.AIRPORT_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (IllegalArgumentException ex) {
            airports.clear();
            logger.error( "Problems opening " + Constants.AIRPORT_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (IllegalStateException ex) {
            airports.clear();
            logger.error( "Problems reading " + Constants.AIRPORT_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (NoSuchElementException ex) {
            airports.clear();
            logger.error( "Problems reading " + Constants.AIRPORT_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        }
    }

    private void addAirport(String record) throws NoSuchElementException, IllegalStateException {
        Scanner row = new Scanner(record);
        row.useDelimiter("\\s*,\\s*");
        Airport airport = new Airport(row.next(), row.next(), row.next(), row.next(), row.next(), row.next());
        airports.add(airport);
    }

}
