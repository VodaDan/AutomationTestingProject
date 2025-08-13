package pages;

import com.microsoft.playwright.Page;

public class ProductPage {

    private Page page;
    private String wishlistAddLinkSelector;

    public ProductPage (Page pageSent) {
        page = pageSent;
        wishlistAddLinkSelector = "a.link-wishlist";
    }

    public void addToWishlist() {
        page.locator(wishlistAddLinkSelector).click();
    }




}
