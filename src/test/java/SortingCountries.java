import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SortingCountries extends TestBase {

    /*@Test
    public void checkSortingCountries() {
        driver.get("http://localhost/litecart/admin/login.php");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("login_form")));
        driver.findElement(By.cssSelector("input[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[name=\"login\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-apps-menu-wrapper")));

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));

        List<WebElement> elem_countries = driver.findElements(By.cssSelector(".row>td a"));
        List<WebElement> elem_zones = driver.findElements(By.cssSelector(".row>td+td+td+td+td+td"));
        List<String> countries = new ArrayList<String>();
        List<String> zones = new ArrayList<String>();

        for (int i = 0; i <elem_countries.size(); i = i + 2){
            countries.add(elem_countries.get(i).getText());
            String z =  elem_zones.get(i).getText();
            zones.add(z);
            int number_zones = Integer.parseInt(z);
            if (number_zones != 0) {
                String url = elem_countries.get(i).getAttribute("href");
                //open in new tab
                ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", url);

                List<WebElement> sub_zones_el = driver.findElements(By.cssSelector("td>input[name$=\"name]\"][type=\"hidden\"]"));
                List<String> sub_zones = new ArrayList<String>();
                for (WebElement zone : sub_zones_el) {
                    sub_zones.add(zone.getAttribute("value"));
                }

                //sort list
                List<String> sortedSubZones = new ArrayList<String>();
                for (String j : sub_zones) {
                    sortedSubZones.add(j);
                }
                Collections.sort(sortedSubZones);
                //check equal
                assert sub_zones != sortedSubZones;

                //go back to previous page
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"w");
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));

            }
        }

        //sort list
        List<String> sortedCountries = new ArrayList<String>();
        for (String j:countries) {
            sortedCountries.add(j);
        }
         Collections.sort(sortedCountries);
        //check equal
        assert countries != sortedCountries;
    }*/

    @Test
    public void checkSortingZones() {


        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));

        List<WebElement> elem_countries = driver.findElements(By.cssSelector("td>a"));
        System.out.println(elem_countries);
        for(int i = 0; i < elem_countries.size(); i=i+2){
            String url = elem_countries.get(i).getAttribute("href");
            System.out.println(url);
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            List<WebElement> elem_zones = driver.findElements(By.cssSelector("select[name*=\"zone_code\"]>option[selected]"));
            List<String> zones = new ArrayList<String>();
            for (WebElement zone: elem_zones) {
                zones.add(zone.getText());
            }

            //sort list
            List<String> sortedZones = new ArrayList<String>();
            for (String j:zones) {
                sortedZones.add(j);
            }
            Collections.sort(sortedZones);
            //check equal
            assert zones != sortedZones;

            System.out.println(zones);
            //go back to previous page
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"w");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));


        }

    }
}
