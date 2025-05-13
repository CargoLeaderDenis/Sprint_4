package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class OrderCreationTestZakazat extends ru.yandex.praktikum.tests.SimpleTest {

    @Test
    public void createOrderTest() {
        mainPage.acceptCookies();
        mainPage.clickOrderButton();
        orderPage.fillUserInfo("Иван", "Иванов", "ул. Мира, 1", "Черкизовская", "+79998880000");
        orderPage.fillRentalInfo("28.05.2025", "двое суток", "black", "Вход слева");

        Assert.assertTrue("Подтверждение оформления заказа не найдено", orderPage.isConfirmationVisible());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}