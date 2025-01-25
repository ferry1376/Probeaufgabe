package com.example.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtils {

    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";
        File destFile = new File("target/ExtentReports/" + screenshotPath);

        // Screenshot erstellen
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


        // Datei kopieren
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
