package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("lonchik_7_7@walla.co.il").withPassword("Samimi@44@")});
        list.add(new Object[]{new User().withEmail("shushu77@gmail.com").withPassword("Papichulo@975")});
        return list.iterator();
    }
}
