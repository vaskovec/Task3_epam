package by.bsu.mmf.animal.pages;

import by.bsu.mmf.animal.steps.Steps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anya) on 3.12.17.
 */

public class AccountPage extends AbstractPage {

    @FindBy(css = "h1.entry-title > span:nth-child(1)")
    private WebElement accountCheckTag;

    @FindBy(css = "#last_name")
    private WebElement lastnameInp;

    @FindBy(css = "div.um-col-alt:nth-child(6) > div:nth-child(1) > input:nth-child(1)")
    private WebElement updateProfileBtn;

    @FindBy(css = ".um-account-side  li:nth-child(2) > a:nth-child(1)")
    private WebElement cahngePasswordLink;

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        BASE_URL = "http://animal.by/account/";
    }


    public boolean accessToAccount() {

        return accountCheckTag.getText().toLowerCase().matches("account");
    }

    public void changeLastname(String newLastname) {

        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+lastnameInp.getLocation().getY()+");");

        Actions actions = new Actions(driver);
        actions
                .click(lastnameInp)
                .keyDown(Keys.CONTROL)
                .sendKeys(Keys.chord("a"))
                .keyUp(Keys.CONTROL)
                .pause(2000)
                .sendKeys(newLastname)
                .pause(2000)
                .click(updateProfileBtn)
                .pause(3000)
                .build()
                .perform();
    }

    public void changePassword(String oldPassword, String newPassword) {

        cahngePasswordLink.click();

        WebElement oldPasswordField = driver.findElement(By.cssSelector("#current_user_password"));
        WebElement newsWordField = driver.findElement(By.cssSelector("#user_password"));
        WebElement confirmPasswordField = driver.findElement(By.cssSelector("#confirm_user_password"));
        WebElement updatePasswordBtn = driver.findElement(By.cssSelector("div.um-col-alt:nth-child(5) > div:nth-child(1) > input:nth-child(1)"));
        Actions actions = new Actions(driver);
        actions
                .sendKeys(oldPasswordField,oldPassword)
                .sendKeys(newsWordField,newPassword)
                .sendKeys(confirmPasswordField,newPassword)
                .pause(2000)
                .click(updatePasswordBtn)
                .build()
                .perform();
    }

    public boolean isPasswordChanged() {
        return driver.findElement(By.cssSelector(".um-notice")).getText().matches("(*.)success(*.)");
    }
}