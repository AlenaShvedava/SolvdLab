package pl.solvd.concert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        JAXBContext j = JAXBContext.newInstance(ConcertHall.class);
        Unmarshaller u = j.createUnmarshaller();
        ConcertHall c = (ConcertHall) u.unmarshal(new File("JaxB//src//main//resources//concerthall.xml"));
        System.out.println(c.getPoster().get(0));
    }
}
