package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

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

    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }



    public void clickBtnOk(){
        if(isElementPresent(By.xpath("//button[@type='button']")))
            click(By.xpath("//button[@type='button']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()='Delete account']"));
    }

    public void logout() {
        click(By.xpath("//a[@href='/logout?url=%2Fsearch']"));
        //click(By.xpath("//*[.=' Logout ']"));
    }



    public String getErrorText() {
        return driver.findElement(By.cssSelector("div.error")).getText();
    }

    //************************REGISTRATION***************************************

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        //click(By.id("terms-of-use"));
        //click(By.cssSelector("label[for='terms-of-use']"));
        if(!driver.findElement(By.id("terms-of-use")).isSelected()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector('#terms-of-use').click()");
        }
    }

    public void checkPolicyXY(){
//        Dimension size = driver.manage().window().getSize();
//        System.out.println("Width screen--->" + size.getWidth());

        if(!driver.findElement(By.id("terms-of-use")).isSelected()) {
            WebElement label = driver.findElement(By.cssSelector("label[for='terms-of-use']"));
            Rectangle rect = label.getRect();
            int w = rect.getWidth();
            Actions actions = new Actions(driver);
            int xOffSet = -w / 2;
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }

    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickBtnOk();
    }

}
