package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver driver) {
        super(driver);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//*[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"),car.getManufacture());
        type(By.cssSelector("#model"), car.getModel());
        type(By.xpath("//input[@id='year']"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"),String.valueOf(car.getSeats()));
        type(By.id("class"),car.getCarClass());
        type(By.id("serialNumber"),car.getCarRegNumber());

        //type(By.id("price"),String.valueOf(car.getPrice()));
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"),car.getAbout());

    }

    private void select(By locator, String option) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(option);
        //Gas
        //select.selectByIndex(5);
        //select.selectByValue("Gas");
        //select.selectByVisibleText("Gas");

    }
    private void typeLocation(String location) {
        type(By.id("pickUpPlace"),location);
        click(By.cssSelector("div.pac-item"));
    }
}
