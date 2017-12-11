package by.bsu.mmf.animal.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


/**
 * Created by Anya) on 23.11.17.
 */
public abstract class AbstractPage {

    WebDriver driver;
    Logger logger = LogManager.getRootLogger();
    String BASE_URL;

    public void openPage() {
        logger.info(getTitleOfCurrentPage());
        driver.navigate().to(BASE_URL);
        fullscreen();
    }

    AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private String getTitleOfCurrentPage() {
        return driver.getTitle();
    }

    private void fullscreen() {
        driver.manage().window().fullscreen();
    }



}
