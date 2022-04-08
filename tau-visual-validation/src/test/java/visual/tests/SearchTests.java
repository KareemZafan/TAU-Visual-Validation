package visual.tests;

import automation_bookstore.pages.SearchPage;
import base.BaseTests;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTests extends BaseTests {

    SearchPage searchPage;

    @BeforeClass
    public void setUpBeforeSearch() {
        searchPage = new SearchPage(driver);
    }

    @Test
    public void testSearchByFullTitle() {
        String bookTitle = "Agile Testing";
        searchPage.search(bookTitle);
        eyesManager.validateWindow();
    }

    @Test
    public void testSearchAgileTestingBook() {
        String bookTitle = "Agile Testing";
        searchPage.search(bookTitle);
        eyesManager.validateElement(By.id("pid3"));
    }


}
