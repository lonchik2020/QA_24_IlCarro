package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderCar {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> DPFile_addNewCarSuccess(){
        List<Object[]> list = new ArrayList<>();
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String path = "src/test/resources/car.csv";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            String line = reader.readLine();
            while (line!=null){
                String[]splitArray = line.split("/");
                list.add(new Object[]{Car.builder()
                        .location(splitArray[0])
                        .manufacture(splitArray[1])
                        .model(splitArray[2])
                        .year(splitArray[3])
                        .fuel(splitArray[4])
                        .seats(Integer.parseInt(splitArray[5]))
                        .carClass(splitArray[6])
                        .carRegNumber(splitArray[7]+i)
                        .price(Double.parseDouble(splitArray[8]))
                        .build()});
                line = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.iterator();
    }
}
