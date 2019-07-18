package com.verisk.g2.take_home_test.services;

import com.verisk.g2.take_home_test.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelServiceTest {

    @Autowired
    private TravelService inTest;

    @Test
    public void calculateShortRouteDirect() {
        String route = inTest.calculateShortRoute("YYZ", "JFK");
        assertNotNull(route);
        assertTrue(!route.isEmpty());
        assertEquals("YYZ -> JFK", route);
    }

    @Test
    public void calculateShortRouteScaled() {
        String route = inTest.calculateShortRoute("YYZ", "LAX");
        assertNotNull(route);
        assertTrue(!route.isEmpty());
        assertEquals("YYZ -> JFK -> LAX", route);
    }

    @Test
    public void calculateShortRouteInvalidOrigin() {
        String route = inTest.calculateShortRoute("MXX", "LAX");
        assertNotNull(route);
        assertTrue(!route.isEmpty());
        assertEquals(Constants.INVALID_ORIGIN, route);
    }

    @Test
    public void calculateShortRouteInvalidDestination() {
        String route = inTest.calculateShortRoute("YYZ", "MXX");
        assertNotNull(route);
        assertTrue(!route.isEmpty());
        assertEquals(Constants.INVALID_DESTINATION, route);
    }

    @Test
    public void calculateShortRouteNoRoute() {
        String route = inTest.calculateShortRoute("YVR", "JFK");
        assertNotNull(route);
        assertTrue(!route.isEmpty());
        assertEquals(Constants.NO_ROUTE, route);
    }

}