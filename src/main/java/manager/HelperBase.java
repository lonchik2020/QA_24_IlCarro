package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        clearNew(element);
        if(text!=null) {
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element){
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void click(By locator){
//        WebElement element = driver.findElement(locator);
//        element.click();
        driver.findElement(locator).click();

    }

    private WebElement findElementBase(By locator){
        return driver.findElement(locator);
    }

    private List<WebElement> findElementsBase(By locator){
        return driver.findElements(locator);
    }

    public boolean isElementPresent(By locator){
//        List<WebElement>list = driver.findElements(locator);
//        return list.size()>0;

        return !driver.findElements(locator).isEmpty();
    }

    public void pause(int sec){
        try {
            Thread.sleep(1000L *sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isYallaButtonNotActive() {
      boolean res =  isElementPresent(By.cssSelector("button[disabled]"));

      WebElement element = driver.findElement(By.cssSelector("button[type='submit']"));
      boolean result = element.isEnabled();

      return res && !result;
    }

    public void submit() {
        click(By.xpath("//button[@type='submit']"));
    }

    public String getMessage() {
//        WebElement element = driver.findElement(By.cssSelector(".dialog-container>h2"));
//        String text = element.getText();
//        return text;
        //pause(3000);
        return driver.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }


}
