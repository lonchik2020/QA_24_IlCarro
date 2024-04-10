package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    public void init(){
        //driver = new ChromeDriver();
        driver = new EventFiringWebDriver(new ChromeDriver());
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
