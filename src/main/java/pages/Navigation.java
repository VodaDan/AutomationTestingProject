package pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Navigation {
    private static Navigation instance;
    private String env="qa2";
    private Playwright playwright;
    private Browser browser;
    private Page page;

    public Navigation(int slowMoValue) {
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(slowMoValue));
        this.page = browser.newPage();
    }

    private Navigation(int slowMoValue, boolean headless) {
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(slowMoValue)
        );
        this.page = browser.newPage();
    }

    public static Navigation getInstance(int slowMoValue, boolean headless) {
        if (instance == null) {
            instance = new Navigation(slowMoValue, headless);
        }
        return instance;
    }

    public static Navigation getInstance(int slowMoValue) {
        return getInstance(slowMoValue, false);
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    public void setPlaywright(Playwright playwright) {
        this.playwright = playwright;
    }

    public void navigateHomeQa2(){
        this.page.navigate("http://qa2magento.dev.evozon.com/");
        this.env = "qa2";
    }

    public void navigateHomeQa3(){
        this.page.navigate("http://qa3magento.dev.evozon.com/");
        this.env = "qa3";
    }

    public void navigateToChelseeTee(){
        this.page.navigate("http://"+this.env+"magento.dev.evozon.com/chelsea-tee-737.html");
    }

    public static Navigation getInstanceIfInitialized() {
        return instance;
    }

    public void navigateClose() {
        browser.close();
        playwright.close();
        instance = null; // Optional: allow re-initialization later
    }

    public void navigateToRegisterPage(){
        this.navigateHomeQa2();
        this.page.locator("a[data-target-element='#header-account']").click();
        this.page.locator("a[title='Register']").click();
//        this.page.locator("#header-account > div > ul > li:nth-child(5) > a").click();
    }

    public void navigateToShoppingCart() {
        this.page.navigate("http://qa2magento.dev.evozon.com/checkout/cart/");
    }

    public void logOutUser(){
        this.navigateHomeQa2();
        this.page.locator("a[data-target-element='#header-account']").click();
        this.page.locator("a[title='Log Out']").click();
    }

    public void navigateToLoginPage(){
        this.navigateHomeQa2();
        this.page.locator("a[data-target-element='#header-account']").click();
        this.page.locator("a[title='Log In']").click();
    }

    public void navigateToCheckout(){
//        this.page.locator("#s_method_flatrate_flatrate").click();
        this.page.locator("div.header-minicart>a").click();
        this.page.locator("div.minicart-actions a[title='Checkout']").click();
        this.page.locator("#onepage-guest-register-button").click();
    }




}
