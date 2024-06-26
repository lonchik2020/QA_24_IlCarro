package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{
    public HelperCar(WebDriver driver) {
        super(driver);
    }

    public void openCarForm() {
        pause(5);
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

    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        driver.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"4/25/2024","4/28/2024"
        String[] from = dateFrom.split("/");///["4"]["25"]["2024"]

        String locatorFrom = "//div[text()=' "+from[1]+" ']";

        String[] to = dateTo.split("/");///["4"]["28"]["2024"]

        click(By.xpath(locatorFrom));
        click(By.xpath("//div[text()=' "+to[1]+" ']"));
    }

    private void typeCity(String city) {
        type(By.id("city"),city);
        pause(1);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        //"4/27/2024", "6/28/2024"
        LocalDate now = LocalDate.now();
        System.out.println(now);//2024-04-13 === object, not possible to split
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate
                .parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));//2024-04-27
        //to change string to "local date", must explain how to receive this string(the format)

        //LocalDate from1 = LocalDate.parse("2013:23/05", DateTimeFormatter.ofPattern("yyyy:d/MM"));

        System.out.println(from);
        //System.out.println(from1);

        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));//2024-06-28

        int diffMonth = from.getMonthValue()-month;
        if (diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' "+from.getDayOfMonth()+" ']"));
        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' "+to.getDayOfMonth()+" ']"));
    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }


    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));

        //"12/27/2024", "2/28/2025"
        LocalDate now = LocalDate.now();
        System.out.println(now);//2024-04-13 === object, not possible to split
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate
                .parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));//2024-12-27
        System.out.println(from);

        LocalDate to = LocalDate
                .parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));//2025-02-28
        System.out.println(to);

        //**from

        int diffMonth;
        int diffYear = from.getYear()-year;

        if (diffYear==0){//2024 = 2024
            diffMonth = from.getMonthValue()-month;//12-4 = 8 clicks
        }else{//2024--->2025
            diffMonth = 12 -month+from.getMonthValue();//12-4+2 = 10 clicks
            //to get until the end of year and to choose a month in the next year
        }
        clickNextMonthBtn(diffMonth);
//        String[] fromD = dateFrom.split("/");
//        String locatorFrom = "//div[text()=' "+fromD[1]+" ']";
//        click(By.xpath(locatorFrom));

       String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        //**to

        diffYear = to.getYear()-from.getYear();
        if(diffYear==0){
            diffMonth = to.getMonthValue()-from.getMonthValue();
        }else{
            diffMonth = 12 - from.getMonthValue()+to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));

    }

    public void navigateToHomePageByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        clearTextBox(By.id("dates"));
        type(By.id("dates"), dateFrom+ " - "+ dateTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
    }
}
