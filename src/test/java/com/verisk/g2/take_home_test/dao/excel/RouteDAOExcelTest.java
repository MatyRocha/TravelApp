package com.verisk.g2.take_home_test.dao.excel;

import static org.junit.Assert.*;

import com.verisk.g2.take_home_test.dto.Route;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteDAOExcelTest {

    @Autowired
    private RouteDAOExcel inTest;

    @Test
    public void getRoutes() {
        List<Route> routes = inTest.getRoutes();
        assertTrue(!routes.isEmpty());
        assertTrue(5 == routes.size());
    }
}