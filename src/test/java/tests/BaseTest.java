package tests;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.Navigation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    private Playwright playwright;
    private Browser browser;
    protected Page page;
    protected Navigation nav;

    private BrowserContext context;

    @BeforeEach
    public void startSession() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true));
        context = browser.newContext();
        this.page = context.newPage();
        this.nav = new Navigation(page);
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));


    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        String testName = testInfo.getDisplayName().replaceAll("[^a-zA-Z0-9_-]", "_");
        Path screenshotPath = Paths.get("target/screenshots/" + testName + ".png");
        try{
            Allure.addAttachment("screenshot", "image/png", Files.newInputStream(screenshotPath), ".png");
        } catch (Exception e) {
            System.out.println("Exception--" + e.getMessage());
        }

        if (context != null) {
            try {
                Path tracePath = Paths.get("target/traces/" + testName + ".zip");
                context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));

                Allure.addAttachment(
                        "Trace - " + testName,
                        "application/zip",
                        Files.newInputStream(tracePath),
                        ".zip"
                );
            } catch (Exception e) {
                System.out.println("Trace attachment failed: " + e.getMessage());
            }
        }

        context.close();
        browser.close();
        playwright.close();
    }


}
