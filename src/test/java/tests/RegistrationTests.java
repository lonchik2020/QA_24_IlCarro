package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
//        Random random = new Random();
//        int i = random.nextInt(1000);
//        System.out.println(i);
//        System.out.println("========================");
//
        //System.out.println(System.currentTimeMillis());
        int y = (int)(System.currentTimeMillis()/1000)%3600;
        //System.out.println(y);
//        System.out.println("========================");

        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Show")
                .withEmail("show"+y+"@gmail.com")
                .withPassword("Show123456$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        //Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }


    @Test
    public void registrationWrongEmailWithoutAtSign(){
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .withEmail("snowgmail.com")
                .withPassword("Snow123456$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationWrongPassword(){
        User user = new User()
                .withFirstName("Lisa")
                .withLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Snow123456");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void registrationEmptyNameField(){
        User user = new User()
                .withFirstName("")
                .withLastName("Snow")
                .withEmail("snow@gmail.com")
                .withPassword("Snow123456$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        //app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),
                "Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickBtnOk();
    }

}
