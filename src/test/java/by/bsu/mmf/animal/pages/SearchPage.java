package by.bsu.mmf.animal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


/**
 * Created by Anya) on 26.11.17.
 */
public class SearchPage extends AbstractPage {

    private Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "http://catalog.animal.by/all/";

    @FindBy(name = "keywords")
    private WebElement keyWordsInp;

    @FindBy(name = "address")
    private WebElement cityInp;

    @FindBy(className = "sabai-btn-primary")
    private WebElement searchBtn;

    @FindBy(css = "#sabai-entity-content-48 > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > span:nth-child(1)")
    private WebElement cityCheck;

    @FindBy(css = "a.sabai-btn:nth-child(3)")
    private WebElement paginationLink;

    @FindBy(css = "select.sabai-pull-right")
    private WebElement categorySelect;

    @FindBy(css = "div.sabai-directory-category:nth-child(3) > a:nth-child(1)")
    private WebElement resultCategory;

    @FindBy(css = "select.sabai-pull-right > option:nth-child(3)")
    private WebElement categoryOption;

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

    public void setCity(String city) {

        Actions actions = new Actions(driver);

        actions
                .sendKeys(cityInp,city)
                .moveToElement(keyWordsInp)
                .build()
                .perform();
    }

    public boolean isAnyResultsFound() {
        WebElement resultsParentBlock = driver.findElement (By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[2]/div[1]/div/div[3]/div/div/div[2]/div[3]"));

        return resultsParentBlock.findElement(By.className("sabai-row")) != null;
    }

    public boolean isAnyResultsFoundByCity(String city) {

       return cityCheck.getText().toLowerCase().matches("(.*)" + city + "(.*)");
    }

    public void clickOnPaginationLink() {

        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+paginationLink.getLocation().getY()+");");

        Actions actions = new Actions(driver);
        actions
                .moveToElement(paginationLink)
                .pause(1000)
                .click(paginationLink)
                .pause(2000)
                .build()
                .perform();
    }

    public boolean isPaginationWorking() {

        System.out.println(paginationLink.getAttribute("class"));
        return paginationLink.getAttribute("class").matches("(.*)active(.*)");
    }


    public void searchByCategory(Integer categoryId) {

        Actions actions = new Actions(driver);
        actions
                .click(categorySelect)
                .pause(3000)
                .sendKeys(Keys.DOWN)
                .sendKeys(Keys.DOWN)
                .sendKeys(Keys.ENTER)
                .click(searchBtn)
                .build()
                .perform();
    }


    public String getResultCategory() {

        logger.info(resultCategory.getText());
        return resultCategory.getText();
    }
}
