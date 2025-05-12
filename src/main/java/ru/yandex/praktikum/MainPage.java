package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private By cookieButton = By.className("App_CookieButton__3cvqF");
    private By orderButton = By.cssSelector(".Button_Button__ra12g");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public static By getQuestionByIndex(int index) {
        return By.id("accordion__heading-" + index);
    }

    public static By getAnswerByIndex(int index) {
        return By.id("accordion__panel-" + index);
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void expandFaqAndCheckAnswer(int index, String expectedText) {
        By question = getQuestionByIndex(index);
        By answer = getAnswerByIndex(index);

        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(answer));

        if (!answerElement.getText().contains(expectedText)) {
            throw new AssertionError("Ожидался текст: " + expectedText + "\nФактически: " + answerElement.getText());
        }
    }
}