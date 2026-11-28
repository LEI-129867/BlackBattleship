package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");
        
        mainPage = new MainPage(driver);
        mainPage.closePopUp();
    }

    @AfterEach
    public void tearDown() {
      driver.quit();
    }


    @Test
    public void search() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton)).click();

        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='search-input']"))
        );
        searchField.click();
        searchField.clear();
        searchField.sendKeys("Selenium");
        searchField.sendKeys(org.openqa.selenium.Keys.ENTER);

        wait.until(ExpectedConditions.urlContains("s=full"));
        assertTrue(driver.getCurrentUrl().contains("s=full"));
    }

    @Test
    public void toolsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu)).click();

        wait.until(ExpectedConditions.attributeToBe(mainPage.toolsMenu, "aria-expanded", "true"));
        WebElement menuPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-test='main-submenu']"))
        );
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        assertTrue(driver.getCurrentUrl().contains("/products/"));
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
