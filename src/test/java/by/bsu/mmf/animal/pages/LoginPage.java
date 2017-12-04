package by.bsu.mmf.animal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anya) on 23.11.17.
 */
public class LoginPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "http://animal.by/login/";

    @FindBy(css = ".uimob500 > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    private WebElement inputUsername;

    @FindBy(css = ".uimob500 > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
    private WebElement inputPassword;

    @FindBy(className = "access_form_button")
    private WebElement accessBtn;

    @FindBy(css = ".uimob500 > div:nth-child(1) > form:nth-child(1) > div:nth-child(5) > div:nth-child(3) > input:nth-child(1)")
    private WebElement buttonSubmit;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Login page opened");
    }

    public void login(String username, String password)
    {

        Actions actions = new Actions(driver);
        actions.click(inputUsername)
                .pause(2000)
                .sendKeys(inputUsername,username)
                .sendKeys(inputPassword,password)
                .click(buttonSubmit)
                .pause(3000)
                .build()
                .perform();

        logger.info("Login performed");
    }



}
