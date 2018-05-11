import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckLogBrowser extends TestBase {

    @Test
    public void checkLogs() {
        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1\"]")));
        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1\"]")));

        List<WebElement> products = driver.findElements(By.cssSelector("img+a[href*=\"category_id=1&product_id\"]"));
        for(int i = 0; i < products.size(); i++){
            products.get(i).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href=\"#tab-general\"]")));
            //System.out.println(driver.manage().logs().getAvailableLogTypes());
            //show all logs browser
            driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            assert logs.isEmpty();

                    //return to Catalog page
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            products = driver.findElements(By.cssSelector("img+a[href*=\"category_id=1&product_id\"]"));


        }

    }

}
