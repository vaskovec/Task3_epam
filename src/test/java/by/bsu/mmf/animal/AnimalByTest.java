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

    public static void main(String[] args) {
        AnimalByTest animalByTest = new AnimalByTest();
        animalByTest.searchTest();
    }

    @Test(description = "search test")
    public void searchTest() {
        Assert.assertTrue(steps.searchInCatalog(SEARCH_REQUEST));
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}
