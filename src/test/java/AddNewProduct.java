import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddNewProduct extends TestBase{

    @Test
    public void addNewProduct() {

        driver.findElement(By.cssSelector("[href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href=\"http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product\"]")));
        driver.findElement(By.cssSelector("[href=\"http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));

        //add General info
        String productName = "Flowers Composition";
        driver.findElement(By.name("name[en]")).sendKeys(productName);
        driver.findElement(By.name("code")).sendKeys("000101");

        driver.findElement(By.cssSelector("input[value=\"1-3\"]")).click();
        WebElement quantity = driver.findElement(By.name("quantity"));
        quantity .click();
        quantity.clear();
        quantity.sendKeys("9.99");

        //find abs path and attach file
        String pathAbsolute = new File("src/images/flowers1.jpg").getAbsolutePath();
        driver.findElement(By.name("new_images[]")).sendKeys(pathAbsolute);

        //work with calendar
        driver.findElement(By.name("date_valid_from")).sendKeys(Keys.HOME + "15.09.2018");
        driver.findElement(By.name("date_valid_to")).sendKeys(Keys.HOME + "15.10.2018");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href=\"#tab-information\"]")));

        //add Information info
        driver.findElement(By.cssSelector("[href=\"#tab-information\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tab-general")));

        driver.findElement(By.name("keywords")).sendKeys("flower");
        driver.findElement(By.name("short_description[en]")).sendKeys("Wonderful flower composition");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("As a gift or for other purpose you will be happy with this one.");
        driver.findElement(By.name("head_title[en]")).sendKeys("Big Flower Composition");
        driver.findElement(By.name("meta_description[en]")).sendKeys("flower");

        //add Prices infoFlowers Composition
        driver.findElement(By.cssSelector("[href=\"#tab-prices\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("purchase_price")));

        WebElement price = driver.findElement(By.name("meta_description[en]"));
        driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("99");

        /*((JavascriptExecutor) driver).executeScript("arguments[0].maxLength = 3;"
                + "arguments[0].value = 38;"
                + "arguments[0].dispatchEvent(new Event('change'))", price);
        System.out.println(price.getAttribute("value"));
        System.out.println(price.getText());*/

        //purchasePrice.sendKeys("9.99");
        driver.findElement(By.name("purchase_price_currency_code")).click();
        driver.findElement(By.cssSelector("[value=\"USD\"]")).click();

        driver.findElement(By.name("prices[USD]")).sendKeys("2");
        driver.findElement(By.name("prices[EUR]")).sendKeys("1.7");

        driver.findElement(By.name("save")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td>img+a")));

        //check product after adding
        List<WebElement> listProducts = driver.findElements(By.cssSelector("td>img+a"));
        List<String> listProductsName = new ArrayList<String>();
        for (WebElement i: listProducts) {
            listProductsName.add(i.getText());
        }
        assert listProductsName.contains(productName);
    }
}
