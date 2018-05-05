import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class CheckLinksInNewWindow extends TestBase {

    @Test
    public void addProductsToBucket() {

        driver.findElement(By.cssSelector("a[href = \"http://localhost/litecart/admin/?app=countries&doc=countries\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));
        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/admin/?app=countries&doc=edit_country\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[class=\"fa fa-external-link\"]")));
        //find all external links
        List<WebElement> externalLinksList = driver.findElements(By.cssSelector("i[class=\"fa fa-external-link\"]"));

        for (WebElement link : externalLinksList) {
            String mainWindow = driver.getWindowHandle();
            link.click();
            //function
            //wait.until();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("i[class=\"fa fa-external-link\"]")));

            Set <String> oldWindows = driver.getWindowHandles();
            for (String i: oldWindows) {
                if (!i.equals(mainWindow)){
                    driver.switchTo().window(i);
                    JavascriptExecutor js = (JavascriptExecutor)driver;
                    js.executeScript("return document.readyState").toString().equals("complete");
                    //wait.until(driver.executeScript("document.body.scrollHeight"));

                    driver.close();
                    driver.switchTo().window(mainWindow);
                }
            }
        }
    }
}
