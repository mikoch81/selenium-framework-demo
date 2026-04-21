package dev.michalkochaniak.portfolio.selenium.support;

import dev.michalkochaniak.portfolio.selenium.config.DriverFactory;
import dev.michalkochaniak.portfolio.selenium.config.WebDriverHolder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(TestArtifactsExtension.class)
public abstract class BaseUiTest {
    private static final LocalDemoServer SERVER = new LocalDemoServer();
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeAll
    static void beforeAll() {
        SERVER.start();
    }

    @AfterAll
    static void afterAll() {
        SERVER.stop();
    }

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createChromeDriver();
        WebDriverHolder.set(driver);
        baseUrl = SERVER.getBaseUrl();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        WebDriverHolder.clear();
    }
}
