package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;
import pages.Navigation;

public class BaseTest {

    private  Playwright playwright;
    private Browser browser;
    protected Page page;
    protected Navigation nav;

    @BeforeEach
    public void startSession () {
        this.nav = new Navigation(500,false);
        this.playwright = nav.getPlaywright();
        this.browser = nav.getBrowser();
        this.page = nav.getPage();
    }

    @AfterEach
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
