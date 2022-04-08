package visual.tests;

import base.BaseTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import the_internet.herkuapp.pages.HomePage;

public class DeepDomTests extends BaseTests {

    @BeforeMethod
    @Override
    public void goHome() {
        driver.get(System.getProperty("site.largedom.url"));
        homePage = new HomePage(driver);
    }

    @Test
    public void testLargeDom() {
        eyesManager.validateWindow();
    }

}
