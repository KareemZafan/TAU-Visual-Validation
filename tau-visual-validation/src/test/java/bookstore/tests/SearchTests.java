package bookstore.tests;

import automation_bookstore.pages.SearchPage;
import base.BaseTests;
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
        validateWindow();
    }

    @Test
    public void checkSearchResults() {
        driver.get("https://eyes.applitools.com/app/test-results/");
        
    }
}
