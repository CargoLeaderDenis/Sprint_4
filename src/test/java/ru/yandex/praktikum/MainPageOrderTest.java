package ru.yandex.praktikum.tests;

import org.junit.*;
import org.junit.Assert;
import org.junit.Test;

public class MainPageOrderTest extends ru.yandex.praktikum.tests.SimpleTest {

    @Test
    public void faqAccordionTest() {
        mainPage.acceptCookies();

        // Используем константы для тестовых данных
        for (int i = 0; i < ru.yandex.praktikum.tests.FaqAnswers.EXPECTED_ANSWERS.length; i++) {
            mainPage.expandFaqAndCheckAnswer(i, ru.yandex.praktikum.tests.FaqAnswers.EXPECTED_ANSWERS[i]);
        }
    }

    @Test
    public void createOrderTest() {
        mainPage.clickOrderButton();
        orderPage.fillUserInfo("Иван", "Иванов", "ул. Мира, 1", "Черкизовская", "+79998880000");
        orderPage.fillRentalInfo("28.04.2025", "двое суток", "black", "Вход слева");
        Assert.assertTrue(orderPage.isConfirmationVisible());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}