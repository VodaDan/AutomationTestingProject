package pages;

import com.microsoft.playwright.Page;

public class ProductPage {

    private Page page;
    private String addToWishlistSelector;

    public ProductPage (Page pageSent) {
        page = pageSent;
        addToWishlistSelector = "a.link-wishlist";
    }

    public void addToWishList() {
        page.locator(addToWishlistSelector).click();
    }


}
