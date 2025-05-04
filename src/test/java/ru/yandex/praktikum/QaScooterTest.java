package ru.yandex.praktikum.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;

import java.time.Duration;

public class QaScooterTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private OrderPage orderPage;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver, wait);
        orderPage = new OrderPage(driver, wait);
    }

    @Test
    public void faqAccordionTest() {
        mainPage.acceptCookies();
        mainPage.expandFaqAndCheckAnswer(0, "Сутки — 400 рублей. Оплата курьеру");
        mainPage.expandFaqAndCheckAnswer(1, "один заказ — один самокат");
        mainPage.expandFaqAndCheckAnswer(2, "Отсчёт времени аренды");
        mainPage.expandFaqAndCheckAnswer(3, "начиная с завтрашнего дня");
        mainPage.expandFaqAndCheckAnswer(4, "всегда можно позвонить в поддержку");
        mainPage.expandFaqAndCheckAnswer(5, "полной зарядкой. Этого хватает");
        mainPage.expandFaqAndCheckAnswer(6, "Штрафа не будет");
        mainPage.expandFaqAndCheckAnswer(7, "И Москве, и Московской области");
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



