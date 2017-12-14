package by.bsu.mmf.animal.pages;

import by.bsu.mmf.animal.util.ExternalSelectorFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anya) on 3.12.17.
 */

public class AccountPage extends AbstractPage {

    public static final String CURRENT_PASSWORD = "CURRENT_PASSWORD";
    public static final String NEW_PASSWORD = "NEW_PASSWORD";
    public static final String CONFIRM_PASSWORD = "CONFIRM_PASSWORD";
    public static final String UPDATE_PASSWORD = "UPDATE_PASSWORD";
    @FindBy(css = "h1.entry-title > span:nth-child(1)")
    private WebElement accountCheckTag;

    @FindBy(css = "#last_name")
    private WebElement lastnameInp;

    @FindBy(css = "div.um-col-alt:nth-child(6) > div:nth-child(1) > input:nth-child(1)")
    private WebElement updateProfileBtn;

    @FindBy(css = ".um-account-side  li:nth-child(2) > a:nth-child(1)")
    private WebElement changePasswordLink;

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

        changePasswordLink.click();

        WebElement oldPasswordField = driver.findElement(ExternalSelectorFactory.getSelectorByName(CURRENT_PASSWORD));
        WebElement newsWordField = driver.findElement(ExternalSelectorFactory.getSelectorByName(NEW_PASSWORD));
        WebElement confirmPasswordField = driver.findElement(ExternalSelectorFactory.getSelectorByName(CONFIRM_PASSWORD));
        WebElement updatePasswordBtn = driver.findElement(ExternalSelectorFactory.getSelectorByName(UPDATE_PASSWORD));
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