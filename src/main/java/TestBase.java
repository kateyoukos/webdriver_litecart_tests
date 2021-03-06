import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    //Chrome starts
    public void start() {
        //System.setProperty("webdriver.chrome.driver", "E:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("http://localhost/litecart/admin/login.php");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("login_form")));
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=\"login\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-apps-menu-wrapper")));
    }

   /* @After
    public void stop() {
        driver.quit();
        driver = null;
    }*/
}