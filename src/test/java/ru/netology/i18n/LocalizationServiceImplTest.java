package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @CsvSource(value = {
            "RUSSIA, Добро пожаловать",
            "GERMANY, Welcome",
    })
    void test_locale(String country, String expectedWord) {

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String countryActual = localizationService.locale(Country.valueOf(country));

        Assertions.assertEquals(expectedWord, countryActual);
    }
}