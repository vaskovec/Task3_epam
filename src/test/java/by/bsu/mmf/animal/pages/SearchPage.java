package by.bsu.mmf.animal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anya) on 26.11.17.
 */
public class SearchPage extends AbstractPage {

    private final String BASE_URL = "http://catalog.animal.by/all/";

    @FindBy(name = "keywords")
    private WebElement keyWordsInp;

    @FindBy(className = "sabai-btn-primary")
    private WebElement searchBtn;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver,this);
    }


    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void search(String req) {
        Actions searchActions = new Actions(driver);

        searchActions
                .sendKeys(keyWordsInp,req)
                .pause(2000)
                .click(searchBtn)
                .pause(5000)
                .build()
                .perform();

    }

    public boolean isAnyResultsFound() {
        WebElement resultsParentBlock = driver.findElement (By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[2]/div[1]/div/div[3]/div/div/div[2]/div[3]"));

        return resultsParentBlock.findElement(By.className("sabai-row")) != null;
    }

}
