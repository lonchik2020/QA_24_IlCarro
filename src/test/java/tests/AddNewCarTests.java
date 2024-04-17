package tests;

import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase{

    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User()
                    .withEmail("lonchik_7_7@walla.co.il")
                    .withPassword("Samimi@44@"));
        }
    }

    @Test
    public  void addNewCarSuccessAll(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("555-555"+ i)
                .price(50)
                .about("Nice Car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().getScreen("src/test/screenshots/screen" +i+ ".png");
        app.getHelperCar().attachPhoto("C:\\JavaProjects\\QA_24\\QA_24_IlCarro\\Lamborghini-Revuelto-Featured-Gear.webp");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar()
                .getMessage(), car.getManufacture()+ " "+car.getModel()+ " added successful");
    }

    @Test(dataProvider = "DPFile_addNewCarSuccess", dataProviderClass = DataProviderCar.class)
    public  void addNewCarSuccess(Car car){
        logger.info("Tests run with data: -----> " + car.toString());
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar()
                .getMessage(), car.getManufacture()+ " "+car.getModel()+ " added successful");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperCar().returnToHome();
    }
}
