package pl.solvd.concerthall;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.solvd.concerthall.models.ConcertHall;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        ObjectMapper om = new ObjectMapper();
        ConcertHall c = om.readValue(new File("JSON/src/main/resources/concerthall.json"), ConcertHall.class);
        System.out.println(c.getPoster().get(1).toString().replace("[", "").replace("]", ""));
    }
}
