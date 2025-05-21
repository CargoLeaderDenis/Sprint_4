package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieButton = By.className("App_CookieButton__3cvqF");
    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By bottomOrderButton = By.cssSelector(".Home_FinishButton__1_cWm");

    private final String accordionHeadingTemplate = "accordion__heading-%d";
    private final String accordionPanelTemplate = "accordion__panel-%d";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            Thread.sleep(500);

            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton));
            button.click();
        } catch (Exception e) {
            System.out.println("Ошибка при нажатии на нижнюю кнопку заказа: " + e.getMessage());
            try {
                WebElement button = driver.findElement(bottomOrderButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            } catch (Exception ex) {
                System.out.println("Не удалось нажать на нижнюю кнопку заказа даже с помощью JavaScript: " + ex.getMessage());
            }
        }
    }

    public void clickFaqQuestion(int questionIndex) {
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(
                By.id(String.format(accordionHeadingTemplate, questionIndex))));
        question.click();
    }

    public String getFaqAnswerText(int questionIndex) {
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id(String.format(accordionPanelTemplate, questionIndex))));
        return answer.getText();
    }

    public boolean isFaqAnswerDisplayed(int questionIndex) {
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id(String.format(accordionPanelTemplate, questionIndex))));
        return answer.isDisplayed();
    }
}
