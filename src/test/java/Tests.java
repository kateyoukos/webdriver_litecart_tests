import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;


public class Tests extends TestBase {

    @Test
    public void openMenu(){
        driver.get("http://localhost/litecart/admin/login.php");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("login_form")));
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=\"login\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-apps-menu-wrapper")));

        List<WebElement> links  = driver.findElements(By.cssSelector("ul li[id=app-]>a"));
        List<String> list_links = new ArrayList<String>();
        for (WebElement i:links) {
            list_links.add(i.getAttribute("href"));
        }

        for (String j:list_links) {
            driver.get(j);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
            try{
                List<WebElement> subcategory = driver.findElements(By.cssSelector("ul[class=docs] a"));
                List<String> list_subcategory = new ArrayList<String>();
                for (WebElement k:subcategory) {
                    list_subcategory.add(k.getAttribute("href"));
                }
                for (String s:list_subcategory) {
                    driver.get(s);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
                }
            }
            catch (StaleElementReferenceException error){

            }

        }
    }

}
