package pl.solvd.concerthall;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.entities.ConcertHalls;
import pl.solvd.concerthall.services.AuthorsService;
import pl.solvd.concerthall.services.impl.AuthorsServiceImpl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        AuthorsService authorsService = new AuthorsServiceImpl();
        authorsService.saveEntity(new Authors(26L, "Alexandr", "Pushkin"));
    }
}
