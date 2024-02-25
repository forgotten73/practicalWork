package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    private final WebDriverWait wait;

    private Wait(WebDriver driver, long time) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    /**
     * Метод инициализации явного ожидания
     * для конкретных элементов на странице
     */
    public static Wait getInstance(WebDriver driver) {
        return new Wait(driver, 20);
    }

    public static Alert waitAlert(WebDriver driver) {
        return getInstance(driver).wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitClickableElement(WebDriver driver, WebElement element) {
        getInstance(driver).wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
