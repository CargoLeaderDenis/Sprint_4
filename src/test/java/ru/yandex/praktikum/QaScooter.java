package ru.yandex.praktikum;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class QaScooter {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void openGoogle() {

        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void test() {

        driver.findElement(By.className("App_CookieButton__3cvqF")).click();

        var question0 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-0")));
        question0.click();
        var answer0 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-0")));
        assertTrue("Ответ отображается!", answer0.isDisplayed());
        assertTrue("Текст ответа верный", answer0.getText().contains("Сутки — 400 рублей. Оплата курьеру — наличными или картой"));

        var question1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-1")));
        question1.click();
        var answer1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-1")));
        assertTrue("Ответ отображается!", answer1.isDisplayed());
        assertTrue("Текст ответа верный", answer1.getText().contains("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим"));

        var question2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-2")));
        question2.click();
        var answer2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-2")));
        assertTrue("Ответ отображается!", answer2.isDisplayed());
        assertTrue("Текст ответа верный", answer2.getText().contains("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30"));

        var question3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-3")));
        question3.click();
        var answer3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-3")));
        assertTrue("Ответ отображается!", answer3.isDisplayed());
        assertTrue("Текст ответа верный", answer3.getText().contains("Только начиная с завтрашнего дня. Но скоро станем расторопнее"));

        var question4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-4")));
        question4.click();
        var answer4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-4")));
        assertTrue("Ответ отображается!", answer4.isDisplayed());
        assertTrue("Текст ответа верный", answer4.getText().contains("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010"));

        var question5 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-5")));
        question5.click();
        var answer5 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-5")));
        assertTrue("Ответ отображается!", answer5.isDisplayed());
        assertTrue("Текст ответа верный", answer5.getText().contains("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится"));

        var question6 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-6")));
        question6.click();
        var answer6 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-6")));
        assertTrue("Ответ отображается!", answer6.isDisplayed());
        assertTrue("Текст ответа верный", answer6.getText().contains("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои"));

        var question7 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-7")));
        question7.click();
        var answer7 = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__panel-7")));
        assertTrue("Ответ отображается!", answer7.isDisplayed());
        assertTrue("Текст ответа верный", answer7.getText().contains("Да, обязательно. Всем самокатов! И Москве, и Московской области"));
    }

    @Test
    public void testZakazat() {
        driver.findElement(By.cssSelector(".Button_Button__ra12g")).click();
        driver.findElement(By.xpath("//input[@placeholder='* Имя']")).sendKeys("Иван");
        driver.findElement(By.xpath("//input[@placeholder='* Фамилия']")).sendKeys("Иванов");
        driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys("ул. Мира, 1");
        driver.findElement(By.className("select-search__input")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Черкизовская']"))).click();
        driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys("+79998880000");
        driver.findElement(By.xpath("//button[text()='Далее']")).click();

        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='* Когда привезти самокат']")));
        dateInput.sendKeys("28.04.2025");
        dateInput.sendKeys(Keys.ENTER);

        driver.findElement(By.className("Dropdown-placeholder")).click();
        driver.findElement(By.xpath("//div[text()='двое суток']")).click();
        driver.findElement(By.id("black")).click();
        driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']")).sendKeys("Вход слева");
        driver.findElement(By.xpath("//button[text()='Заказать']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Да']"))).click();

        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Заказ оформлен')]")));
        assertTrue(confirmation.getText().contains("Заказ оформлен"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Object inputField = new Object();
//        Actions actions = null;
//
//  var answer = driver.findElement(By.id("accordion__panel-5"));
// assertTrue("Ответ не отображается!", answer.isDisplayed());
// assertTrue("Текст ответа неверный", answer.getText().contains("400 рублей"));

// public void testVoprosiOvajnom() {
//   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//   var question = wait.until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-5")));
//   question.click();


//@After
//public void teardown() {
//Закрой браузер
//  driver.quit();
//}
//}


//System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/Chrome/Application/chrome.exe");
