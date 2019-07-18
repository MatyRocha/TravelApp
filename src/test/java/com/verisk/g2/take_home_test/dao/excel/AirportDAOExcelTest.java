package com.verisk.g2.take_home_test.dao.excel;

import static org.junit.Assert.*;

import com.verisk.g2.take_home_test.dto.Airport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportDAOExcelTest {

    @Autowired
    private AirportDAOExcel inTest;

    @Test
    public void getAirports() {
        List<Airport> airports = inTest.getAirports();
        assertTrue(!airports.isEmpty());
        assertTrue(5653 == airports.size());
    }
}