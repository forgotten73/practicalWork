package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    public WebDriverWait wait;

    public Wait(WebDriver driver, long time) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
    }

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
