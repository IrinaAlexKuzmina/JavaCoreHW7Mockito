package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.", "172.3.56.25"})
    void byIpTest_verifyRussia(String arg) {
        Location locationRussia = new Location("", Country.RUSSIA, "", 1);

        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(arg);

        Assertions.assertEquals(locationRussia.getCountry(), result.getCountry());
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.", "96.3.56.25"})
    void byIpTest_verifyUSA(String arg) {
        Location locationUSA = new Location("", Country.USA, "", 1);

        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(arg);

        Assertions.assertEquals(locationUSA.getCountry(), result.getCountry());
    }
}
