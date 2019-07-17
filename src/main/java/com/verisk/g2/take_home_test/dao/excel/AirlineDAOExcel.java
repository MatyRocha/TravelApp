package com.verisk.g2.take_home_test.dao.excel;

import com.verisk.g2.take_home_test.dao.AirlineDAO;
import com.verisk.g2.take_home_test.dto.Airline;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class AirlineDAOExcel implements AirlineDAO {
    private static List<Airline> airlines;
    private static final String AIRLINE_FILENAME = "airlines.csv";

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAirline(String record) {
        try (Scanner row = new Scanner(record)) {
            row.useDelimiter("\\s*,\\s*");
            Airline airline = new Airline(row.next(), row.next(), row.next(), row.next());
            airlines.add(airline);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
