package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void precondition() {
        //if signOut presents ----> logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginPositiveTest1(){
        User user = new User().withEmail("lonchik_7_7@walla.co.il").withPassword("Samimi@44@");

//        user.setEmail("lonchik_7_7@walla.co.il");
//        user.setPassword("Samimi@44@");


        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickBtnOk();

    }

    @Test
    public void loginPositiveTest(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7@walla.co.il","Samimi@44@");
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickBtnOk();

    }

    @Test
    public void loginPositiveTestModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7@walla.co.il","Samimi@44@");
        app.getHelperUser().submitLogin();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickBtnOk();

    }

    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7walla.co.il","Samimi@44@");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isMessageForWrongEmailAppears());

    }


    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("lonchik_7_7@walla.co.il","Samimi@44");
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(5);
        Assert.assertTrue(app.getHelperUser().isMessageLoginFailedAppears());

    }


    @Test
    public void loginUnregisteredUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("dananana22@walla.co.il","Samimi@44");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isMessageLoginFailedAppears());

    }

    @AfterMethod
    public void postCondition()
    {
        if(app.getHelperUser().isMessageLoginFailedAppears())
            app.getHelperUser().clickBtnOk();
       }

}
