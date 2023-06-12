package org.ndviet.library.webui.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ndviet.library.configuration.ConfigurationFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.util.LinkedHashMap;
import java.util.List;

import static org.ndviet.library.configuration.Constants.SELENIUM_CHROME_ARGS;
import static org.ndviet.library.configuration.Constants.SELENIUM_CHROME_PREFS;

public enum BrowserFactory {
    CHROME {
        @Override
        public WebDriver createLocalDriver() {
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            List<String> listArgs = ConfigurationFactory.getInstance().getListValues(SELENIUM_CHROME_ARGS);
            options.addArguments(listArgs.toArray(new String[0]));
            LinkedHashMap listPrefs = ConfigurationFactory.getInstance().getMapValues(SELENIUM_CHROME_PREFS);
            listPrefs.forEach((key, value) -> {
                options.setCapability(key.toString(), value);
            });
            return options;
        }
    },
    FIREFOX {
        @Override
        public WebDriver createLocalDriver() {
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            return null;
        }
    },
    EDGE {
        @Override
        public WebDriver createLocalDriver() {
            return null;
        }

        @Override
        public EdgeOptions getOptions() {
            return null;
        }
    },
    SAFARI {
        @Override
        public WebDriver createLocalDriver() {
            return null;
        }

        @Override
        public SafariOptions getOptions() {
            return null;
        }
    };

    public abstract WebDriver createLocalDriver();

    public abstract AbstractDriverOptions<?> getOptions();

    private static final Logger LOGGER = LogManager.getLogger(BrowserFactory.class);
}
