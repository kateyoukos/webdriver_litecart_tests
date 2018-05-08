import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class WorkWithBucket extends TestBase {

    @Test
    public void addProductsToBucket() {

        for(int i = 1; i < 4; i++){
            driver.get("http://localhost/litecart/");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));

            List<WebElement> products = driver.findElements(By.cssSelector("li[class=\"product column shadow hover-light\"]"));
            //open first product
            products.get(0).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("add_cart_product")));

            //fix with yellow duck - when user should select size
            if(!driver.findElements(By.name("options[Size]")).isEmpty()){
                driver.findElement(By.name("options[Size]")).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=\"Small\"]")));
                driver.findElement(By.cssSelector("option[value=\"Small\"]")).click();
            }
            driver.findElement(By.name("add_cart_product")).click();
            String countProducts = driver.findElement(By.cssSelector("span[class=\"quantity\"]")).getText();
            WebElement counter = driver.findElement(By.cssSelector("span[class=\"quantity\"]"));
            String text = i + "";
            wait.until(ExpectedConditions.textToBePresentInElement(counter, text));

            }

        driver.findElement(By.cssSelector("a[href=\"http://localhost/litecart/en/checkout\"][class=\"link\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[name=\"remove_cart_item\"]")));

        Integer counterProductsUpdate = 2;
        while (counterProductsUpdate != 1){
            driver.findElement(By.cssSelector("[name=\"remove_cart_item\"]")).click();
            List<WebElement> listProductsAfter = driver.findElements(By.cssSelector("[style=\"text-align: center;\"]"));
            counterProductsUpdate = listProductsAfter.size();
            //wait until element will disappear
            wait.until(ExpectedConditions.stalenessOf(listProductsAfter.get(0)));
            }
    }
}
