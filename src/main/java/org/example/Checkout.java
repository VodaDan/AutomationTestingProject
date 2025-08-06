package org.example;


import com.microsoft.playwright.*;

public class Checkout {

    private Page page;

    public Checkout(Page page) {
        this.page = page;
    }

    public void addProductToCart() {
        this.page.locator("#nav > ol > li.level0.nav-2.parent > a").click(); // man menu
        this.page.locator("#nav > ol > li.level0.nav-2.parent > ul > li.level1.nav-2-1.first > a").click(); // new arrivals
        this.page.locator("#product-collection-image-410").click(); // chelsee tee image
        this.page.locator("#swatch27 > span.swatch-label > img").click(); // blue color
        this.page.locator("#swatch79 > span.swatch-label").click(); // M
        Locator qtyField = this.page.locator("#qty");
        qtyField.fill("2");
        this.page.locator("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button").click(); // add to cart
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

}
