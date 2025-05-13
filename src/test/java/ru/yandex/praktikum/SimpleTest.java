package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;
import java.time.Duration;

public class SimpleTest {
    public WebDriver driver;
    public WebDriverWait wait;
    protected MainPage mainPage;
    public OrderPage orderPage;
    int index;
    String expectedAnswer;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver, wait);
        orderPage = new OrderPage(driver, wait);

    }
}
    //*@Test
    //public void MainPageVoprosiovajnomTest() {
     //   mainPage.acceptCookies();
      //  mainPage.expandFaqAndCheckAnswer(index, expectedAnswer);
    //}

    //@After
    //public void tearDown() {
     //   driver.quit();
    //}
