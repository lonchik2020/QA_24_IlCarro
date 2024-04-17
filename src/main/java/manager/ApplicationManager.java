package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    //WebDriver driver;

    EventFiringWebDriver driver;

    HelperUser helperUser;

    HelperCar helperCar;

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        if(browser.equals(BrowserType.CHROME)){
            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("All tests run in Chrome Browser");
        }else if(browser.equals(BrowserType.FIREFOX)){
            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("All tests run in FIREFOX Browser");
        }else if(browser.equals(BrowserType.EDGE)){
            driver = new EventFiringWebDriver(new EdgeDriver());
            logger.info("All tests run in EDGE Browser");
        }

        //driver = new ChromeDriver();
        logger.info("All tests run in Chrome Browser");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://ilcarro.web.app/search");
        logger.info("The link--->" +driver.getCurrentUrl());
        helperUser = new HelperUser(driver);
        helperCar = new HelperCar(driver);
        driver.register(new ListenerWD(logger));//connecting listener to application manager
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public void stop(){
        driver.quit();
    }

}
