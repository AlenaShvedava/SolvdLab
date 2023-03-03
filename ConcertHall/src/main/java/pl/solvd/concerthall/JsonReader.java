package pl.solvd.concerthall;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.solvd.concerthall.binary.ConcertHall;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    public static void main(String[] args) throws IOException {
        ObjectMapper om = new ObjectMapper();
        ConcertHall c = om.readValue(new File("JSON/src/main/resources/concerthall.json"), ConcertHall.class);
        System.out.println(c.getPoster().get(1).toString().replace("[", "").replace("]", ""));
    }
}
