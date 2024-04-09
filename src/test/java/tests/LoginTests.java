package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        //if signOut presents ----> logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void loginPositiveTest1(){
        User user = new User().withEmail("lonchik_7_7@walla.co.il").withPassword("Samimi@44@");

//        user.setEmail("lonchik_7_7@walla.co.il");
//        user.setPassword("Samimi@44@");


        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickBtnOk();

    }

    @Test
    public void loginPositiveTest(){
        logger.info("Start test with name 'loginPositiveTest' ");
        logger.info("Test data--->email:lonchik_7_7@walla.co.il , password:Samimi@44@ ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7@walla.co.il","Samimi@44@");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickBtnOk();
        logger.info("Assert check that message 'Logged in success' appears");

    }

    @Test
    public void loginPositiveTestModel(){
        logger.info("Test data--->email:lonchik_7_7@walla.co.il , password:Samimi@44@ ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7@walla.co.il","Samimi@44@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickBtnOk();
        logger.info("Assert check that message 'Logged in success' appears");

    }

    @Test
    public void loginWrongEmail(){
        logger.info("Test data--->email:lonchik_7_7walla.co.il , password:Samimi@44@ ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7walla.co.il","Samimi@44@");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        logger.info("Assert check that error message 'It'snot look like email' appears");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert check that 'Yalla' button is not active");

    }


    @Test
    public void loginWrongPassword(){
        logger.info("Test data--->email:lonchik_7_7@walla.co.il , password:Samimi@44 ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7@walla.co.il","Samimi@44");
        app.getHelperUser().submit();
        app.getHelperUser().pause(5);
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check that message '\"Login or Password incorrect\"' appears");

    }


    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data--->email:dananana22@walla.co.il , password:Samimi@44 ");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dananana22@walla.co.il","Samimi@44");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check that message '\"Login or Password incorrect\"' appears");

    }

    @AfterMethod
    public void postCondition() {
            app.getHelperUser().clickBtnOk();
       }

}
