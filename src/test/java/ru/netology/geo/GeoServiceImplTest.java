package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GeoServiceImplTest {

    @ParameterizedTest
    @CsvSource(value = {
            "127.0.0.1, , , , 0",
            "172.0.32.11, Moscow, RUSSIA, Lenina, 15",
            "96.44.183.149, New York, USA, 10th Avenue, 32"
    })
    void test_byIp_location(String ip, ArgumentsAccessor argumentsForLocation) {
        Location locationExpected = new Location(argumentsForLocation.getString(1),
                (argumentsForLocation.get(2, Country.class)),
                argumentsForLocation.getString(3),
                argumentsForLocation.getInteger(4));
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location locationActual = geoService.byIp(ip);

        Assertions.assertEquals(locationExpected, locationActual);
    }

    @Test
    void test_byCoordinates() {
        double latitude = 0.0;
        double longitude = 0.0;
        GeoServiceImpl geoService = new GeoServiceImpl();
        Throwable exceptionActual = assertThrows(RuntimeException.class, () -> geoService.byCoordinates(latitude, longitude));
        Assertions.assertEquals("Not implemented", exceptionActual.getMessage());
    }
}