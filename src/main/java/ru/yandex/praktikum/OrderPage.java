package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы для первой страницы заказа
    private final By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.className("select-search__input");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы для второй страницы заказа
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[text()='Заказать']");
    private final By confirmButton = By.xpath("//button[text()='Да']");

    // Локатор для подтверждения заказа
    private final By orderConfirmation = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Увеличил таймаут до 10 секунд
    }

    // Методы для заполнения первой страницы заказа
    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void selectMetroStation(String stationName) {
        driver.findElement(metroStationField).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='" + stationName + "']"))).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Методы для заполнения второй страницы заказа
    public void setDeliveryDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);
    }

    public void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath("//div[text()='" + period + "']")).click();
    }

    public void selectColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            driver.findElement(blackColorCheckbox).click();
        } else if (color.equalsIgnoreCase("grey")) {
            driver.findElement(greyColorCheckbox).click();
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        orderBtn.click();
    }

    public void clickConfirmButton() {
        try {
            // Небольшая пауза перед нажатием на кнопку подтверждения
            Thread.sleep(1000);

            // Попробуем найти кнопку подтверждения
            WebElement confirmBtn = wait.until(ExpectedConditions.presenceOfElementLocated(confirmButton));

            // Если кнопка не кликабельна, используем JavaScript для клика
            if (!confirmBtn.isEnabled() || !confirmBtn.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
            } else {
                confirmBtn.click();
            }
        } catch (Exception e) {
            System.out.println("Ошибка при нажатии на кнопку подтверждения: " + e.getMessage());
            // Попробуем альтернативный способ - JavaScript клик
            try {
                WebElement confirmBtn = driver.findElement(confirmButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
            } catch (Exception ex) {
                System.out.println("Не удалось нажать на кнопку подтверждения даже с помощью JavaScript: " + ex.getMessage());
            }
        }
    }

    // Метод для проверки подтверждения заказа
    public boolean isOrderConfirmed() {
        try {
            // Увеличиваем время ожидания для появления подтверждения
            WebElement confirmation = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
            return confirmation.getText().contains("Заказ оформлен");
        } catch (Exception e) {
            System.out.println("Не удалось найти подтверждение заказа: " + e.getMessage());
            return false;
        }
    }

    // Метод для заполнения всей формы заказа
    public void fillOrderForm(String firstName, String lastName, String address, String metroStation,
                              String phone, String date, String rentalPeriod, String color, String comment) {
        // Заполнение первой страницы
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        selectMetroStation(metroStation);
        setPhone(phone);
        clickNextButton();

        // Заполнение второй страницы
        setDeliveryDate(date);
        selectRentalPeriod(rentalPeriod);
        selectColor(color);
        setComment(comment);
        clickOrderButton();
        clickConfirmButton();
    }
}






/*
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
*/
