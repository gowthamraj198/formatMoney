package uiTests.tests.common;


import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import uiTests.utils.Reports;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public static final String WEBDRIVER_FIREFOX_BIN = "webdriver.firefox.bin";
    public static final String LINUX_FIREFOX_PATH = "/usr/bin/firefox";
    public static final String LINUX_GECKO_DRIVER_PATH = "./src/test/resources/drivers/linux/geckodriver";
    public static final String MAC_GECKO_DRIVER_PATH = "./src/test/resources/drivers/mac/geckodriver";
    public static final String MAC_PHANTOM_DRIVER_PATH = "./src/test/resources/drivers/mac/phantomjs";
    public static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    public static final String LINUX_USER_AGENT_STRING = "Mozilla/5.0 (X11; Linux i686; rv:10.0) Gecko/20100101 Firefox/33.0";
    public static final String MAC_USER_AGENT_STRING = "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1";
    public static String test_for = System.getProperty("test_for");
    public static String browser = System.getProperty("browser");
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    public static String testExecutionTimeStamp = dateFormat.format(new Date());

    protected RemoteWebDriver driver;
    private JavascriptExecutor jse;
    protected Properties prop_data = new Properties();
    protected InputStream properties_data = null;
    protected Logger log = Logger.getLogger(BaseTest.class);
    protected Reports reports = new Reports();
    protected String pageURL;
    protected String property_file = "./src/test/resources/config/application-data.properties";


    @BeforeSuite
    public void beforeSuite()
    {
        reports.startResult();
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {

        try {
            PropertyConfigurator.configure("log4j.properties");
            log.info("##################################################################");

            loadConfigurationProperties();
            pageURL=prop_data.getProperty("pageURL");

            log.info("Test case started");
            if(browser.equals("phantom"))
            {
                if (test_for.equals("web")) instantiatePhantomjsDriverforWeb();
                else if (test_for.equals("mobile")) instantiatePhantomjsDriverforMobile();
            }
            else if(browser.equals("firefox"))
            {
                if (test_for.equals("web")) instantiateDriverForWeb();
                else if (test_for.equals("mobile")) instantiateDriveForMobile();
            }

            setPageLoadTimeout();
        } catch (Exception e) {
            log.error("Browser initialization failed:" + e.getMessage());
            throw new Exception();
        }
    }

    private void setPageLoadTimeout() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void instantiatePhantomjsDriverforWeb()
    {
        Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,MAC_PHANTOM_DRIVER_PATH);

        driver = new PhantomJSDriver(caps);
        driver.manage().window().maximize();
        jse = driver;
        jse.executeScript("window.focus()");
    }

    private void instantiatePhantomjsDriverforMobile()
    {
        Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true);
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,MAC_PHANTOM_DRIVER_PATH);

        driver = new PhantomJSDriver(caps);
        driver.manage().window().setSize(new Dimension(375, 667));
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.focus()");
    }

    private void instantiateDriveForMobile() throws IOException {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", SystemUtils.IS_OS_LINUX ? LINUX_USER_AGENT_STRING : MAC_USER_AGENT_STRING);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);

        initializeDriver(desiredCapabilities);
        driver.manage().window().setSize(new Dimension(375, 667));
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.focus()");
    }

    private void instantiateDriverForWeb() throws IOException {
        FirefoxProfile profile = new FirefoxProfile();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        initializeDriver(capabilities);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenResolution = new Dimension((int)
                toolkit.getScreenSize().getWidth(), (int)
                toolkit.getScreenSize().getHeight());

        driver.manage().window().setSize(screenResolution);
        driver.manage().window().maximize();
        jse = driver;
        jse.executeScript("window.focus()");
    }


    private void initializeDriver(DesiredCapabilities desiredCapabilities) throws IOException {
        desiredCapabilities.setCapability("acceptInsecureCerts", true);
        //desiredCapabilities.setCapability("marionette", false);
        if (SystemUtils.IS_OS_LINUX) {
            System.setProperty(WEBDRIVER_FIREFOX_BIN, LINUX_FIREFOX_PATH);
            System.setProperty(WEBDRIVER_GECKO_DRIVER, LINUX_GECKO_DRIVER_PATH);
        } else {
            System.setProperty(WEBDRIVER_GECKO_DRIVER, MAC_GECKO_DRIVER_PATH);
        }
        driver = new FirefoxDriver(desiredCapabilities);
        log.info("Driver initialized !");
    }

    private void loadConfigurationProperties() throws IOException {
        properties_data = new FileInputStream(new File(property_file));
        prop_data.load(properties_data);
    }


    @AfterMethod
    public void afterMethod() throws IOException {
        try
        {
            if(RetryAnalyzer.makeReports.equals("FAIL")) {
                reports.reportStep(driver, "TestCase Failed and will execute for one more time!", "ERROR");
                RetryAnalyzer.makeReports="PASS";
            }
        }
        finally {
            log.info("Test case completed");
            log.info("##################################################################");
            reports.reportStep(driver, "Test case completed", "INFO");
            reports.endTest();
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite()
    {
        reports.endResult();
    }
}
