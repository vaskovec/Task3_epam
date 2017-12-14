package by.bsu.mmf.animal.pages;

import by.bsu.mmf.animal.util.ExternalSelectorFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Anya) on 26.11.17.
 */
public class SearchPage extends AbstractPage {

    public static final String PARENT_RESULTS = "PARENT_RESULTS";
    @FindBy(name = "keywords")
    private WebElement keyWordsInp;

    @FindBy(name = "address")
    private WebElement cityInp;

    @FindBy(className = "sabai-btn-primary")
    private WebElement searchBtn;

    @FindBy(className = "sabai-googlemaps-address-0")
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
        BASE_URL = "http://catalog.animal.by/all/";
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
        WebElement resultsParentBlock = driver.findElement(ExternalSelectorFactory.getSelectorByName(PARENT_RESULTS));

        return resultsParentBlock.isDisplayed();
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
