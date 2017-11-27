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


}
