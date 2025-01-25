package com.example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",  // Pfad zu den Feature-Dateien
        glue = "com.example.steps",               // Pfad zu den Step-Definitionen
        plugin = {
                "pretty",                             // Für eine lesbare Konsolenausgabe
                "html:target/cucumber-reports.html"  // Generiert einen HTML-Report
        }
)
public class CucumberTestRunner {
}
