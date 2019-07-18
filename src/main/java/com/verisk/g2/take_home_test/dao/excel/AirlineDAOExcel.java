package com.verisk.g2.take_home_test.dao.excel;

import com.verisk.g2.take_home_test.dao.AirlineDAO;
import com.verisk.g2.take_home_test.dto.Airline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class AirlineDAOExcel implements AirlineDAO {
    private static List<Airline> airlines;
    private static final String AIRLINE_FILENAME = "airlines.csv";
    Logger logger = LoggerFactory.getLogger(AirportDAOExcel.class);

    public  List<Airline> getAirlines(){
        if (airlines == null) {
            getAirlinesFromExcel();
        }
        return  airlines;
    }

    private void getAirlinesFromExcel() {
        airlines = new ArrayList<Airline>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(AIRLINE_FILENAME);
        try (Scanner scanner = new Scanner(inputStream) ) {
            scanner.nextLine(); // skip first row, which is the title
            while (scanner.hasNextLine()) {
                addAirline(scanner.nextLine());
            }
        } catch (Exception ex) {
            airlines.clear();
            logger.error( "Problems reading " + AIRLINE_FILENAME,
                    ex.getMessage(), ex.getStackTrace());
        }
    }

    private void addAirline(String record) throws InputMismatchException {
        Scanner row = new Scanner(record);
        row.useDelimiter("\\s*,\\s*");
        Airline airline = new Airline(row.next(), row.next(), row.next(), row.next());
        airlines.add(airline);
    }

}
