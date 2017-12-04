package by.bsu.mmf.animal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Anya) on 25.11.17.
 */
public class ProfilePage extends AbstractPage {

    private static final Logger logger = LogManager.getRootLogger();
    public final String BASE_URL = "http://animal.by/user/animal_fan_98/";


    @FindBy(css = "h1.entry-title > span:nth-child(1)")
    private WebElement fullName;

    @FindBy(className = "access_form_button")
    private WebElement profileCheckBtn;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("profile page opened");
    }

    public Boolean profileAccessAbility() {
        Actions waitAction = new Actions(driver);
        waitAction
                .pause(4000)
                .perform();
        System.out.println(profileCheckBtn.getText());
        return profileCheckBtn.getText().toLowerCase().equals("профиль");
    }

    public String getLastname() {
        return fullName.getText().split(" ")[1].trim();
    }

}
