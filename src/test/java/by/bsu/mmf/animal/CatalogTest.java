package by.bsu.mmf.animal;

import by.bsu.mmf.animal.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Anya) on 23.11.17.
 */
public class CatalogTest {

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

    @Test(description = "category test")
    public void setSearchByCategoryTest() {
        Assert.assertEquals(steps.searchByCategory(2),"Ветклиники");
    }

    @Test(description = "pagination test")
    public void paginationTest() {
        Assert.assertTrue(steps.paginationCheck());
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
