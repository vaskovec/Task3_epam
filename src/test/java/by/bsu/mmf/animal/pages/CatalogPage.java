package by.bsu.mmf.animal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anya) on 25.11.17.
 */
public class CatalogPage extends AbstractPage {

    private final String BASE_URL = "http://catalog.animal.by/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(name = "keywords")
    private WebElement searchField;

    @FindBy(xpath = "/html/body/div[3]/div[2]/div[2]/div/div[1]/div[2]/div/div/div/div/div/div/div[1]/div/div[2]/h3/a")
    private WebElement newsLink;

    @FindBy(css = "#td_uid_2_5a25cc9b4934d > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > h3:nth-child(1)")
    private WebElement openedNews;

    public CatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public String search(String request) {

        WebElement searchBtn = searchField.findElement(By.xpath("following-sibling::*[2]"));
        Actions searchQueueActions = new Actions(driver);

        searchQueueActions
                .sendKeys(searchField,request)
                .pause(3000)
                .click(searchBtn)
                .build()
                .perform();
        logger.info("searching");

        return searchField.getAttribute("value");
    }


    public void openNews() {
        Actions actions = new Actions(driver);
        actions
                .click(newsLink)
                .build()
                .perform();

    }

    public boolean isNewsDisplayed() {

        return newsLink.isDisplayed();

    }
}
