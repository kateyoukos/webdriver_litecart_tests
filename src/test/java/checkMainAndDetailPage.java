import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class checkMainAndDetailPage extends TestBase{

    @Test
    public void checkNameMainAndDetailed() {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));

        //store name product on main page
        String nameMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] [class=\"name\"]")).getText();

        WebElement elemUrlDetailedPage = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] a[class=\"link\"]"));
        String urlDetailedPage = elemUrlDetailedPage.getAttribute("href");
        driver.get(urlDetailedPage);

        String nameDetailed = driver.findElement(By.cssSelector("h1[class=\"title\"]")).getText();
        //check equal name
        assert nameMain.equalsIgnoreCase(nameDetailed);
    }

    @Test
    public void checkPricesMainAndDetailed() {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));

        //store regular price product on main page
        String regularPriceMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] s")).getText();

        //store campaign price product on main page
        String campaignPriceMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] strong")).getText();

        //go to DetailedPage
        String urlDetailedPage = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] a[class=\"link\"]")).getAttribute("href");
        driver.get(urlDetailedPage);

        //store regular price product on detailed page
        String regularPriceDetailed = driver.findElement(By.className("regular-price")).getText();

        //store campaign price product on detailed page
        String campaignPriceDetailed = driver.findElement(By.className("campaign-price")).getText();

        //check equal prices
        assert Integer.parseInt(regularPriceMain.substring(1)) == Integer.parseInt(regularPriceDetailed.substring(1));
        assert Integer.parseInt(campaignPriceMain.substring(1)) == Integer.parseInt(campaignPriceDetailed.substring(1));
    }

    @Test
    public void checkDecorRegularPricesMainAndDetailed() {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));

        //store color regular price product on main page
        String colorRegularPriceMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] s")).getCssValue("color");
        String decorRegularPriceMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] s")).getCssValue("text-decoration-line");

        //check regular price is line-through and grey on main page
        assert decorRegularPriceMain.equalsIgnoreCase("line-through");
        assert colorRegularPriceMain.contains("119, 119, 119,");


        //go to DetailedPage
        String urlDetailedPage = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] a[class=\"link\"]")).getAttribute("href");
        driver.get(urlDetailedPage);

        //store color regular price product on detailed page
        String colorRegularPriceDetailed = driver.findElement(By.className("regular-price")).getCssValue("color");
        String decorRegularPriceDetailed = driver.findElement(By.className("regular-price")).getCssValue("text-decoration-line");

        //check regular price is line-through and grey on detailed page
        assert decorRegularPriceDetailed.equalsIgnoreCase("line-through");
        assert colorRegularPriceDetailed.contains("102, 102, 102,");
    }

    @Test
    public void checkDecorCampaignPricesMainAndDetailed() {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-account-login")));

        //store attr regular price product on main page
        String colorCampaignPriceMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] strong")).getCssValue("color");
        String decorCampaignPriceMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] strong")).getCssValue("font-weight");
        String fontSizeRegularPriceMain = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] strong")).getCssValue("font-size");
        fontSizeRegularPriceMain = fontSizeRegularPriceMain.substring(0, fontSizeRegularPriceMain.indexOf("p"));

        //check campaign price is red and bold (700)
        assert (decorCampaignPriceMain.equalsIgnoreCase("bold") || (new Integer(decorCampaignPriceMain) == 700));
        assert colorCampaignPriceMain.contains(", 0, 0,");

        //go to DetailedPage
        String urlDetailedPage = driver.findElement(By.cssSelector("div[id=\"box-campaigns\"] a[class=\"link\"]")).getAttribute("href");
        driver.get(urlDetailedPage);

        //store attr
        String colorCampaignPriceDetailed = driver.findElement(By.className("campaign-price")).getCssValue("color");
        String decorCampaignPriceDetailed = driver.findElement(By.className("campaign-price")).getCssValue("font-weight");
        String fontSizeCampaignPriceMain = driver.findElement(By.className("campaign-price")).getCssValue("font-size");
        fontSizeCampaignPriceMain = fontSizeCampaignPriceMain.substring(0, fontSizeCampaignPriceMain.indexOf("p"));

        //check campaign price is red and bold (700)
        assert (decorCampaignPriceDetailed.equalsIgnoreCase("bold") || (new Integer(decorCampaignPriceDetailed) == 700));
        assert colorCampaignPriceDetailed.contains(", 0, 0,");
        assert Double.parseDouble(fontSizeCampaignPriceMain) > Double.parseDouble(fontSizeRegularPriceMain);
    }
}
