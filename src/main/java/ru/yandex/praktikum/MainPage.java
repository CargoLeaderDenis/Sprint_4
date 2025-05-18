package ru.yandex.praktikum.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String QUESTION_ID_PREFIX = "accordion__heading-";
    private static final String ANSWER_ID_PREFIX = "accordion__panel-";
    private static final String COOKIE_BUTTON_CLASS = "App_CookieButton__3cvqF";
    private static final String ORDER_BUTTON_SELECTOR = ".Button_Button__ra12g";


    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final By cookieButton = By.className(COOKIE_BUTTON_CLASS);
    private final By orderButton = By.cssSelector(ORDER_BUTTON_SELECTOR);


    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public static By getQuestionByIndex(int index) {
        return By.id(QUESTION_ID_PREFIX + index);
    }

    public static By getAnswerByIndex(int index) {
        return By.id(ANSWER_ID_PREFIX + index);
    }

    public void expandFaqAndCheckAnswer(int index, String expectedText) {
        By question = getQuestionByIndex(index);
        By answer = getAnswerByIndex(index);

        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(answer));

        String actualText = answerElement.getText();
        assertEquals("Ответ на вопрос с индексом " + index + " не соответствует ожидаемому.", expectedText, actualText);
        }
}
