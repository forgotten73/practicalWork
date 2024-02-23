package tests;

import helpers.ConfProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormPage;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    public static FormPage formPage;

    @BeforeAll
    public static void setup() {
        int pageTimeout = Integer.parseInt(ConfProperties.getProperty("pageTimeout"));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        formPage = new FormPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(pageTimeout));
        driver.get(ConfProperties.getProperty("url"));
    }

    @AfterAll
    public static void testQuit() {
        driver.quit();
    }

}
