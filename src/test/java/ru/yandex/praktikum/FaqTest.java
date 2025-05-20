package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageobjects.MainPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
    private MainPage mainPage;

    private final int questionIndex;
    private final String expectedAnswerText;

    public FaqTest(int questionIndex, String expectedAnswerText) {
        this.questionIndex = questionIndex;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой"},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим"},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30"},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее"},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010"},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится"},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои"},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области"}
        });
    }

    @Before
    public void setUp() {
        // Пробуем запустить в Firefox, если не получится - в Chrome
        try {
            driver = new FirefoxDriver();
        } catch (Exception e) {
            driver = new ChromeDriver();
        }

        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();
    }

    @Test
    public void testFaqAccordion() {
        mainPage.clickFaqQuestion(questionIndex);
        assertTrue("Ответ не отображается!", mainPage.isFaqAnswerDisplayed(questionIndex));
        assertTrue("Текст ответа неверный", mainPage.getFaqAnswerText(questionIndex).contains(expectedAnswerText));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}





/*
package ru.yandex.praktikum.tests;
//package ru.yandex.praktikum.FaqAnswers;

public class FaqAnswers {
    public static final String[] EXPECTED_ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };
}*/
