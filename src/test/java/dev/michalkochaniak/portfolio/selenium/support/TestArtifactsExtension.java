package dev.michalkochaniak.portfolio.selenium.support;

import dev.michalkochaniak.portfolio.selenium.config.WebDriverHolder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestArtifactsExtension implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = WebDriverHolder.get();
        if (!(driver instanceof TakesScreenshot screenshotDriver)) {
            return;
        }

        File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
        String fileName = FilenameUtils.getBaseName(context.getDisplayName()) + ".png";
        Path targetPath = Path.of("artifacts", fileName);

        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(screenshot.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to persist screenshot artifact", exception);
        }
    }
}
