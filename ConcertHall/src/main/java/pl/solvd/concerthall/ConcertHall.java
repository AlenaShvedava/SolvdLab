package pl.solvd.concerthall;
import pl.solvd.concerthall.models.Poster;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "concerthall")
public class ConcertHall {
    private List <Poster> posters = new ArrayList<>();

    public ConcertHall() {

    }

    public ConcertHall(List <Poster> poster) {
        this.posters = poster;
    }

    @XmlElement(name = "poster")
    public List <Poster> getPoster() {
        return posters;
    }

    public void setPoster(List <Poster> posters) {
        this.posters = posters;
    }

    @Override
    public String toString() {
        return "Poster: " + posters + "\n" +
        "=====================================";
    }
}
