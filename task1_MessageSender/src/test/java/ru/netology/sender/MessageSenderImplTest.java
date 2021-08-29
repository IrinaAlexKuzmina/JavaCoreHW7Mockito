package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @ParameterizedTest
    @CsvSource({"172.,Привет!",
            "96.,Hello!"})
    void sendTest(String location, String welcomeMessage) {
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Привет!");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Hello!");

        Location locationRussia = new Location("", Country.RUSSIA, "", 1);
        Location locationUSA = new Location("", Country.USA, "", 1);

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.")).thenReturn(locationRussia);
        Mockito.when(geoService.byIp("96.")).thenReturn(locationUSA);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, location);
        String result = messageSender.send(headers);
        Assertions.assertEquals(welcomeMessage, result);
    }
}
