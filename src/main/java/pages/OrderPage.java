package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OrderPage {
    private Page page;
    private String firstNameSelector;
    private String lastNameSelector;
    private String emailSelector;
    private String phoneSelector;
    private String citySelector;
    private String zipSelector;
    private String addressSelector;
    private String regionSelector;

    public OrderPage(Page pageSent) {
        page = pageSent;
        firstNameSelector="#billing\\:firstname";
        lastNameSelector="#billing\\:lastname";
        emailSelector="#billing\\:email";
        addressSelector="#billing\\:street1";
        zipSelector="#billing\\:postcode";
        citySelector="#billing\\:city";
        phoneSelector="#billing\\:telephone";
        regionSelector="#billing\\:region_id";
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
        page.locator(firstNameSelector).fill(firstName);
    }

    public void fillEmail(String email) {
        page.locator(emailSelector).fill(email);
    }

    public void fillAddress(String address) {
        page.locator(addressSelector).fill(address);
    }

    public void fillCity(String city) {
        page.locator(citySelector).fill(city);
    }

    public void fillZip(String zip) {
        page.locator(zipSelector).fill(zip);
    }

    public void fillPhone(String phone){
        page.locator(phoneSelector).fill(phone);
    }

    public void fillLastName(String lastname) {
        page.locator(lastNameSelector).fill(lastname);
    }

    public String getFirstNameSelector() {
        return firstNameSelector;
    }

    public void setFirstNameSelector(String firstNameSelector) {
        this.firstNameSelector = firstNameSelector;
    }

    public String getLastNameSelector() {
        return lastNameSelector;
    }

    public void setLastNameSelector(String lastNameSelector) {
        this.lastNameSelector = lastNameSelector;
    }

    public String getEmailSelector() {
        return emailSelector;
    }

    public void setEmailSelector(String emailSelector) {
        this.emailSelector = emailSelector;
    }

    public String getPhoneSelector() {
        return phoneSelector;
    }

    public void setPhoneSelector(String phoneSelector) {
        this.phoneSelector = phoneSelector;
    }

    public String getCitySelector() {
        return citySelector;
    }

    public void setCitySelector(String citySelector) {
        this.citySelector = citySelector;
    }

    public String getZipSelector() {
        return zipSelector;
    }

    public void setZipSelector(String zipSelector) {
        this.zipSelector = zipSelector;
    }

    public String getAddressSelector() {
        return addressSelector;
    }

    public void setAddressSelector(String addressSelector) {
        this.addressSelector = addressSelector;
    }

    public String getRegionSelector() {
        return regionSelector;
    }

    public void setRegionSelector(String regionSelector) {
        this.regionSelector = regionSelector;
    }
}
