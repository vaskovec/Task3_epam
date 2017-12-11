package by.bsu.mmf.animal;

import by.bsu.mmf.animal.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Anya) on 11.12.17.
 */
public class AccountPageTest {

    private Steps steps;
    private final String USERNAME = "animal_fan_98";
    private final String PASSWORD = "Ane4ka98";
    private static final String NEW_LASTNAME = "Васьковец";
    private static final String NEW_PASSWORD = "Ane4ka98";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }


    @Test(description = "access to account test")
    public void accessToAccountTest() {
        steps.login(USERNAME,PASSWORD);
        Assert.assertTrue(steps.canAccessToAccount());
    }

    @Test(description = "change lastname")
    public void cahngeLastnameTest() {

        steps.login(USERNAME,PASSWORD);
        steps.changeLastnameAction(NEW_LASTNAME);

        Assert.assertEquals(steps.getLastname(),NEW_LASTNAME);
    }

    @Test(description = "change password")
    public void changePasswordTest() {
        steps.login(USERNAME,PASSWORD);
        Assert.assertTrue(steps.changePassword(PASSWORD,NEW_PASSWORD));
    }


    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}
