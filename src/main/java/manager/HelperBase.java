package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {
    WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    public void type(By locator, String text){
        WebElement element = driver.findElement(locator);
        element.click();
        element.clear();
        if(text!=null) {
            element.sendKeys(text);
        }
    }

    public void click(By locator){
        WebElement element = driver.findElement(locator);
        element.click();

    }

    private WebElement findElementBase(By locator){
        return driver.findElement(locator);
    }

    private List<WebElement> findElementsBase(By locator){
        return driver.findElements(locator);
    }

    public boolean isElementPresent(By locator){
        List<WebElement>list = driver.findElements(locator);
        return !findElementsBase(locator).isEmpty();
    }

}
