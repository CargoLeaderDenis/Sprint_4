package ru.yandex.praktikum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.MainPage.MainPage;
import org.junit.Before;
import org.junit.After;
import java.time.Duration;

@RunWith(Parameterized.class)
public class MainPageVoprosiovajnomTest   {  //extends ru.yandex.praktikum.tests.SimpleTest {
        private WebDriver driver;
        private WebDriverWait wait;
        private MainPage mainPage;
        private final int index;
        private final String expectedAnswer;

        public MainPageVoprosiovajnomTest(int index, String expectedAnswer) {
            this.index = index;
            this.expectedAnswer = expectedAnswer;
        }

        @Parameterized.Parameters(name = "FAQ #{0}")
        public static Object[][] data() {
            String[] answers = ru.yandex.praktikum.tests.FaqAnswers.EXPECTED_ANSWERS;
            Object[][] result = new Object[answers.length][2];
            for (int i = 0; i < answers.length; i++) {
                result[i][0] = i;
                result[i][1] = answers[i];
            }
            return result;
        }

        @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver, wait);
    }

        @Test
    public void checkFaqAnswerTest() {
        mainPage.acceptCookies();
        mainPage.expandFaqAndCheckAnswer(index, expectedAnswer);
    }

        @After
    public void tearDown() {
        driver.quit();
    }
}

