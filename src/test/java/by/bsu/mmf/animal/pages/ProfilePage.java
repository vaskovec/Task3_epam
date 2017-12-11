package by.bsu.mmf.animal.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Anya) on 25.11.17.
 */
public class ProfilePage extends AbstractPage {

    @FindBy(css = "h1.entry-title > span:nth-child(1)")
    private WebElement fullName;

    @FindBy(className = "access_form_button")
    private WebElement profileCheckBtn;

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        BASE_URL = "http://animal.by/user/animal_fan_98/";
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
