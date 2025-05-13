package ru.yandex.praktikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Локаторы вынесены в поля класса
    private static final By USER_NAME_INPUT = By.xpath("//input[@placeholder='* Имя']");
    private static final By USER_LASTNAME_INPUT = By.xpath("//input[@placeholder='* Фамилия']");
    private static final By USER_ADDRESS_INPUT = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By STATION_DROPDOWN = By.className("select-search__input");
    private static final By PHONE_INPUT = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.xpath("//button[text()='Далее']");
    private static final By RENTAL_DATE_INPUT = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private static final By RENTAL_PERIOD_DROPDOWN = By.className("Dropdown-placeholder");
   // private static final By COLOR_OPTION = By.id("color");
    private static final By COMMENT_INPUT = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private static final By ORDER_BUTTON = By.xpath("//button[text()='Заказать']");
    private static final By CONFIRM_BUTTON = By.xpath("//button[text()='Да']");
    private static final By CONFIRMATION_MESSAGE = By.xpath("//div[contains(text(), 'Заказ оформлен')]");


    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void fillUserInfo(String name, String lastname, String address, String station, String phone) {
        enterText(USER_NAME_INPUT, name);
        enterText(USER_LASTNAME_INPUT, lastname);
        enterText(USER_ADDRESS_INPUT, address);
        selectStation(station);
        enterText(PHONE_INPUT, phone);
        clickNextButton();
    }

    private void enterText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    private void selectStation(String station) {
        driver.findElement(STATION_DROPDOWN).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + station + "']"))).click();
    }

    private void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    public void fillRentalInfo(String date, String period, String color, String comment) {
        enterRentalDate(date);
        selectRentalPeriod(period);
        selectColor(color);
        enterText(COMMENT_INPUT, comment);
        submitOrder();
    }

    private void enterRentalDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(RENTAL_DATE_INPUT));
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);
    }

    private void selectRentalPeriod(String period) {
        driver.findElement(RENTAL_PERIOD_DROPDOWN).click();
        driver.findElement(By.xpath("//div[text()='" + period + "']")).click();
    }

    private void selectColor(String color) {
        driver.findElement(By.id(color)).click();
    }

    private void submitOrder() {
        driver.findElement(ORDER_BUTTON).click();
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_BUTTON)).click();
    }

    public boolean isConfirmationVisible() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(CONFIRMATION_MESSAGE, "Заказ оформлен"));
    }
}
