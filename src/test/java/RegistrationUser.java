import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class RegistrationUser extends TestBase {

    @Test
    public void regisrtationUser() {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));

        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/en/create_account\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("create-account")));

        //fill in taxID field
        driver.findElement(By.name("tax_id")).sendKeys("123");

        //fill in company field
        driver.findElement(By.name("company")).sendKeys("TestCompany");

        //fill in first name field
        driver.findElement(By.name("firstname")).sendKeys("Ivanna");

        //fill in last name field
        driver.findElement(By.name("lastname")).sendKeys("Mirgorodskaya");

        //fill in address1 field
        driver.findElement(By.name("address1")).sendKeys("Svododu street, b.1, ap.3");

        //fill in address2 field
        driver.findElement(By.name("address2")).sendKeys("Leona street b.2");

        //fill in postcode field
        driver.findElement(By.name("postcode")).sendKeys("50301-5098");

        //fill in city field
        driver.findElement(By.name("city")).sendKeys("New York");

        //select country
        driver.findElement(By.className("select2-selection__arrow")).click();
        driver.findElement(By.className("select2-search__field")).sendKeys("United States");
        driver.findElement(By.cssSelector("[id$=\"US\"]")).click();


        //fill in email field
        Random random = new Random();
        String randomEmail = "ks13581+".concat(String.valueOf(random.nextInt(1000)).concat("@gmail.com")) ;
        driver.findElement(By.name("email")).sendKeys(randomEmail);

        //fill in phone field
        driver.findElement(By.name("phone")).sendKeys("+17897897987");

        //fill in password field
        String password = "test11";
        driver.findElement(By.name("password")).sendKeys(password);

        //fill in confirmed password field
        driver.findElement(By.name("confirmed_password")).sendKeys("test11");

        //enter submit
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navigation")));

        driver.findElement(By.cssSelector("[id=\"navigation\"] [href=\"http://localhost/litecart/en/logout\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navigation")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));

        driver.findElement(By.name("email")).sendKeys(randomEmail);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("navigation")));

        driver.findElement(By.cssSelector("[id=\"navigation\"] [href=\"http://localhost/litecart/en/logout\"]")).click();
    }
}
