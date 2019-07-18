package com.verisk.g2.take_home_test.dao.excel;

import com.verisk.g2.take_home_test.dao.AirlineDAO;
import com.verisk.g2.take_home_test.dto.Airline;
import com.verisk.g2.take_home_test.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

@Component
public class AirlineDAOExcel implements AirlineDAO {
    private static List<Airline> airlines;
    Logger logger = LoggerFactory.getLogger(AirlineDAOExcel.class);

    public  List<Airline> getAirlines(){
        if (airlines == null) {
            getAirlinesFromExcel();
        }
        return  airlines;
    }

    private void getAirlinesFromExcel() {
        airlines = new ArrayList<Airline>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.AIRLINE_FILENAME);
        try (Scanner scanner = new Scanner(inputStream) ) {
            scanner.nextLine(); // skip first row, which is the title
            while (scanner.hasNextLine()) {
                addAirline(scanner.nextLine());
            }
        } catch (NullPointerException ex) {
            airlines.clear();
            logger.error( "Problems opening " + Constants.AIRLINE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (IllegalArgumentException ex) {
            airlines.clear();
            logger.error( "Problems opening " + Constants.AIRLINE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (IllegalStateException ex) {
            airlines.clear();
            logger.error( "Problems reading " + Constants.AIRLINE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        } catch (NoSuchElementException ex) {
            airlines.clear();
            logger.error( "Problems reading " + Constants.AIRLINE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        }
    }

    private void addAirline(String record) throws NoSuchElementException, IllegalStateException {
        Scanner row = new Scanner(record);
        row.useDelimiter("\\s*,\\s*");
        Airline airline = new Airline(row.next(), row.next(), row.next(), row.next());
        airlines.add(airline);
    }

}
