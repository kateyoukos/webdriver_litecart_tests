import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckStickerProduct extends TestBase {

    @Test
    public void checkStickerProduct(){
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));
        List<WebElement> products = driver.findElements(By.cssSelector("li[class=\"product column shadow hover-light\"]"));
        for (WebElement i:products) {
            //for everu product count stickers to list
            List<WebElement> countStickers = driver.findElements(By.cssSelector("[class^=\"sticker\"]"));
            //check that sticker is 1 for every product
            assert countStickers.size() != 1;
        }
    }
}
