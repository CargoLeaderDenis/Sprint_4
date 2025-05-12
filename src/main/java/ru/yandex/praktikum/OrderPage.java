package ru.yandex.praktikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void fillUserInfo(String name, String surname, String address, String station, String phone) {
        driver.findElement(By.xpath("//input[@placeholder='* Имя']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@placeholder='* Фамилия']")).sendKeys(surname);
        driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(address);
        driver.findElement(By.className("select-search__input")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + station + "']"))).click();
        driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(phone);
        driver.findElement(By.xpath("//button[text()='Далее']")).click();
    }
    public void fillRentalInfo(String date, String period, String color, String comment) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='* Когда привезти самокат']")));
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);

        driver.findElement(By.className("Dropdown-placeholder")).click();
        driver.findElement(By.xpath("//div[text()='" + period + "']")).click();
        driver.findElement(By.id(color)).click();
        driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']")).sendKeys(comment);
        driver.findElement(By.xpath("//button[text()='Заказать']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Да']"))).click();
    }
    public boolean isConfirmationVisible() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[contains(text(), 'Заказ оформлен')]"),
                "Заказ оформлен"
        ));
    }
}