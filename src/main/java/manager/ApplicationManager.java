package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    WebDriver driver;

    HelperUser helperUser;

    HelperCar helperCar;

    public void init(){
        driver = new ChromeDriver();
        logger.info("All tests run in Chrome Browser");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://ilcarro.web.app/search");
        logger.info("The link--->" +driver.getCurrentUrl());
        helperUser = new HelperUser(driver);
        helperCar = new HelperCar(driver);
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
