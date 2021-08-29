package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    LocalizationService locService;

    @BeforeEach
    public void init() {
        locService = new LocalizationServiceImpl();
    }

    @Test
    void localeTest_verifyUSA() {
        String result = locService.locale(Country.USA);
        Assertions.assertEquals(result, "Welcome");
    }

    @Test
    void localeTest_verifyRussia() {
        String result = locService.locale(Country.RUSSIA);
        Assertions.assertEquals(result, "Добро пожаловать");
    }
}
