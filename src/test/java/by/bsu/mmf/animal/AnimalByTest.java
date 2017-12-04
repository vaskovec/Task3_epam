package by.bsu.mmf.animal;

import by.bsu.mmf.animal.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Anya) on 23.11.17.
 */
public class AnimalByTest {

    private static final String SEARCH_REQUEST = "Ветеринарная станция";
    private static final String CITY = "Минск";
    private static final String NEW_LASTNAME = "Васьковец";
    private static final String NEW_PASSWORD = "Ane4ka98";
    private Steps steps;
    private final String USERNAME = "animal_fan_98";
    private final String PASSWORD = "Ane4ka98";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test(description = "login test")
    public void loginTest() {
        steps.login(USERNAME, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn());
    }


    @Test(description = "search test")
    public void searchTest() {
        Assert.assertTrue(steps.searchInCatalog(SEARCH_REQUEST));
    }

    @Test(description = "category test")
    public void setSearchByCategoryTest() {
        Assert.assertEquals(steps.searchByCategory(2),"Ветклиники");
    }

    @Test(description = "search by city")
    public void searchByCityTest() {
        Assert.assertTrue(steps.searchByCity(SEARCH_REQUEST,CITY));
    }

    @Test(description = "pagination test")
    public void paginationTest() {
        Assert.assertTrue(steps.paginationCheck());
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

    @Test(description = "display News")
    public void displayNewsTest() {

        Assert.assertTrue(steps.openNews());
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}
