package by.bsu.mmf.animal;

import by.bsu.mmf.animal.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Anya) on 11.12.17.
 */
public class SearchPageTest {

    private static final String SEARCH_REQUEST = "Ветеринарная станция";
    private static final String CITY = "Минск";
    private Steps steps;

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();
    }


    @Test(description = "search test")
    public void searchTest() {
        Assert.assertTrue(steps.searchInCatalog(SEARCH_REQUEST));
    }

    @Test(description = "search by city")
    public void searchByCityTest() {
        Assert.assertTrue(steps.searchByCity(SEARCH_REQUEST,CITY));
    }


    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeDriver();
    }
}
