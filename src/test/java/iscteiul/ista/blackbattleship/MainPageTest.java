package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() throws InterruptedException {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");
        Thread.sleep(1500);
        
        mainPage = new MainPage(driver);
        mainPage.closePopUp();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }


    @Test
    public void search() throws InterruptedException {
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton)).click();
        //Thread.sleep(1000);
        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='search-input']"))
        );
        searchField.sendKeys("Selenium");
        Thread.sleep(1000);

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='full-search-button']"))
        );
        submitButton.click();

        WebElement searchPageField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-test-id='search-input']"))
        );
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)).click();
        Thread.sleep(1500);

        WebElement menuPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-test='main-submenu']"))
        );
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.seeDeveloperToolsButton)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.findYourToolsButton)).click();
        Thread.sleep(1500);

        assertTrue(driver.getCurrentUrl().contains("/products/"));
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
