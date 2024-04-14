package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @BeforeMethod
    public void postCondition(){
        app.getHelperCar().navigateToHomePageByLogo();
    }

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "4/25/2024","4/28/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentMonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Tel Aviv, Israel", "4/27/2024", "6/28/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentYear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess(){
        app.getHelperCar().searchAnyPeriod("Tel Aviv, Israel", "12/27/2024", "2/28/2025");
        app.getHelperCar().getScreen("src/test/screenshots/anyPeriod.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

}
