package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver driver) {
        super(driver);
    }

    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
//        WebElement loginBtn = driver.findElement(By.xpath("//a[text()='Log in']"));
//        loginBtn.click();
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    public void submitLogin() {
        click(By.xpath("//button[@type='submit']"));
    }

    public void clickBtnOk(){
        click(By.xpath("//button[@type='button']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()='Delete account']"));
    }

    public void logout() {
        click(By.xpath("//a[@href='/logout?url=%2Fsearch']"));
    }


    public String getMessage() {
//        WebElement element = driver.findElement(By.cssSelector(".dialog-container>h2"));
//        String text = element.getText();
//        return text;
        //pause(2000);
        return driver.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }
}
