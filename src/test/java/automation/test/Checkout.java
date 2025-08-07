package automation.test;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Checkout {

    private static Page page;
    private static Navigation nav;

    @BeforeAll
    public static void startSessions() {
        Navigation navi = Navigation.getInstance(150,false); // slowMo , headless true
        nav = navi;
        page = nav.getPage();
    }

    public void addProductToCart() {
        page.locator("li[class='level0 nav-2 parent']").hover(); // man menu
        page.locator("#nav .nav-primary li:nth-child(2) li:nth-child(2)").click(); // new arrivals
        page.locator("#product-collection-image-410").click(); // chelsee tee image
        page.locator("li img[alt='Blue']").click(); // blue color
        page.locator("li.option-m").click(); // M
        Locator qtyField = page.locator("#qty");
        qtyField.fill("2");
        page.locator("div.add-to-cart-buttons button[title='Add to Cart']").click(); // add to cart
    }

    public void fillFirstName(String firstName) {
        page.locator("#billing\\:firstname").fill(firstName);
    }

    public void fillEmail(String email) {
        page.locator("#billing\\:email").fill(email);
    }

    public void fillAddress(String address) {
        page.locator("#billing\\:street1").fill(address);
    }

    public void fillCity(String city) {
        page.locator("#billing\\:city").fill(city);
    }

    public void fillZip(String zip) {
        page.locator("#billing\\:postcode").fill(zip);
    }

    public void fillPhone(String phone){
        page.locator("#billing\\:telephone").fill(phone);
    }

    public void fillLastName(String lastname) {
        page.locator("#billing\\:lastname").fill(lastname);
    }

    @Test@Ignore
    public void addProductToCartTest(){
        nav.navigateHomeQa2();
        addProductToCart();  // the method adds 2 in qty field
        assertThat(page.locator("table#shopping-cart-table tbody tr")).hasCount(1);
        assertThat(page.locator("td.product-cart-actions > input[class='input-text qty']")).hasValue("2");
    }

    @Test
    public void removeProductFromCart() {
        nav.navigateHomeQa2();
        addProductToCart();
        nav.navigateHomeQa2();
        nav.navigateToShoppingCart();
        page.locator("tr.first.odd td:last-child a").click();
        assertThat(page.locator("div.page-title h1")).hasText(Pattern.compile(".*shopping cart is empty.*",Pattern.CASE_INSENSITIVE));
    }



}
