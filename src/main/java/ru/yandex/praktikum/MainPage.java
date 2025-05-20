package ru.yandex.praktikum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By cookieButton = By.className("App_CookieButton__3cvqF");
    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    private final By bottomOrderButton = By.cssSelector(".Home_FinishButton__1_cWm");

    // Шаблоны локаторов для FAQ
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
        driver.findElement(bottomOrderButton).click();
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
