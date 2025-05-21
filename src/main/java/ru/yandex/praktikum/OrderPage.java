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

    private final By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.className("select-search__input");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");
    private final By orderButtonAlternative = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final By confirmButton = By.xpath("//button[text()='Да']");

    private final By orderConfirmation = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

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
        System.out.println("Пытаемся нажать кнопку 'Заказать'...");

        try {
            WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderBtn);
            Thread.sleep(1000);
            System.out.println("Найдена кнопка 'Заказать', пытаемся кликнуть...");
            orderBtn.click();
            System.out.println("Кнопка 'Заказать' нажата методом 1");
            return;
        } catch (Exception e) {
            System.out.println("Метод 1 не сработал: " + e.getMessage());
        }

        try {
            WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderButtonAlternative));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderBtn);
            Thread.sleep(1000);
            System.out.println("Найдена кнопка 'Заказать' (метод 2), пытаемся кликнуть...");
            orderBtn.click();
            System.out.println("Кнопка 'Заказать' нажата методом 2");
            return;
        } catch (Exception e) {
            System.out.println("Метод 2 не сработал: " + e.getMessage());
        }

        try {
            System.out.println("Пробуем метод 3 - поиск всех кнопок...");
            java.util.List<WebElement> buttons = driver.findElements(By.tagName("button"));
            if (!buttons.isEmpty()) {
                WebElement lastButton = buttons.get(buttons.size() - 1);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastButton);
                Thread.sleep(1000);
                System.out.println("Найдена последняя кнопка, пытаемся кликнуть...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lastButton);
                System.out.println("Последняя кнопка нажата методом 3");
                return;
            }
        } catch (Exception e) {
            System.out.println("Метод 3 не сработал: " + e.getMessage());
        }

        try {
            System.out.println("Пробуем метод 4 - прямой JavaScript клик...");
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelector('.Button_Button__ra12g.Button_Middle__1CSJM').click()");
            System.out.println("Кнопка 'Заказать' нажата методом 4");
            return;
        } catch (Exception e) {
            System.out.println("Метод 4 не сработал: " + e.getMessage());
        }

        try {
            System.out.println("Пробуем метод 5 - клик по координатам...");
            WebElement orderFormContainer = driver.findElement(By.className("Order_Buttons__1xGrp"));
            int x = orderFormContainer.getLocation().getX() + orderFormContainer.getSize().getWidth() - 100;
            int y = orderFormContainer.getLocation().getY() + orderFormContainer.getSize().getHeight() / 2;

            ((JavascriptExecutor) driver).executeScript(
                    "var evt = document.createEvent('MouseEvents');" +
                            "evt.initMouseEvent('click', true, true, window, 0, 0, 0, " + x + ", " + y +
                            ", false, false, false, false, 0, null);" +
                            "document.elementFromPoint(" + x + ", " + y + ").dispatchEvent(evt);");

            System.out.println("Клик по координатам выполнен методом 5");
        } catch (Exception e) {
            System.out.println("Метод 5 не сработал: " + e.getMessage());
        }

        System.out.println("Все методы клика по кнопке 'Заказать' не сработали");
    }

    public void clickConfirmButton() {
        try {
            System.out.println("Ожидаем появления кнопки подтверждения...");
            Thread.sleep(2000);

            WebElement confirmBtn = wait.until(ExpectedConditions.presenceOfElementLocated(confirmButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmBtn);
            Thread.sleep(1000);

            System.out.println("Кнопка подтверждения найдена, пытаемся кликнуть...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
            System.out.println("Кнопка подтверждения нажата");
        } catch (Exception e) {
            System.out.println("Ошибка при нажатии на кнопку подтверждения: " + e.getMessage());

            try {
                System.out.println("Пробуем альтернативный метод для кнопки подтверждения...");
                ((JavascriptExecutor) driver).executeScript(
                        "document.querySelector('.Order_Buttons__1xGrp button:last-child').click()");
                System.out.println("Альтернативный метод для кнопки подтверждения сработал");
            } catch (Exception ex) {
                System.out.println("Альтернативный метод для кнопки подтверждения не сработал: " + ex.getMessage());
            }
        }
    }

    public boolean isOrderConfirmed() {
        try {
            WebElement confirmation = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
            return confirmation.getText().contains("Заказ оформлен");
        } catch (Exception e) {
            System.out.println("Не удалось найти подтверждение заказа: " + e.getMessage());
            return false;
        }
    }

    public void fillOrderForm(String firstName, String lastName, String address, String metroStation,
                              String phone, String date, String rentalPeriod, String color, String comment) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        selectMetroStation(metroStation);
        setPhone(phone);
        clickNextButton();

        setDeliveryDate(date);
        selectRentalPeriod(rentalPeriod);
        selectColor(color);
        setComment(comment);

        try {
            org.openqa.selenium.OutputType<java.io.File> outputType = org.openqa.selenium.OutputType.FILE;
            java.io.File screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(outputType);
            System.out.println("Скриншот перед нажатием кнопки заказа сохранен: " + screenshot.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот: " + e.getMessage());
        }

        clickOrderButton();

        try {
            Thread.sleep(2000);
            org.openqa.selenium.OutputType<java.io.File> outputType = org.openqa.selenium.OutputType.FILE;
            java.io.File screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(outputType);
            System.out.println("Скриншот после нажатия кнопки заказа сохранен: " + screenshot.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Не удалось сделать скриншот: " + e.getMessage());
        }

        clickConfirmButton();
    }
}
