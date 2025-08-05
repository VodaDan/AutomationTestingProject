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
        this.page.locator("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button"); // add to cart
    }

}
