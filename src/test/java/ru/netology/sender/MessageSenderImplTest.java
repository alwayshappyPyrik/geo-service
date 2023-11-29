package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.Map;

class MessageSenderImplTest {

    @Test
    void test_check_message_for_Russian() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String sendCountryActual = messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11"));

        Assertions.assertEquals("Добро пожаловать", sendCountryActual);
    }

    @Test
    void test_check_message_for_USA() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String sendCountryActual = messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149"));

        Assertions.assertEquals("Welcome", sendCountryActual);
    }


}