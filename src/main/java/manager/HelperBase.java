package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
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


    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File temp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(temp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTextBox(By locator){
        WebElement element = driver.findElement(locator);
        String os = System.getProperty("os.name");//to receive from the system the name of operational system
        System.out.println(os);
        if (os.startsWith("Win")){
            element.sendKeys(Keys.CONTROL, "a");
        }else {
            element.sendKeys(Keys.COMMAND, "a");
        }
        element.sendKeys(Keys.DELETE);
    }

    public String getErrorText() {
        return driver.findElement(By.cssSelector("div.error")).getText();
    }
}
