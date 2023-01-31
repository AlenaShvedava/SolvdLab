package pl.solvd.concerthall;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.services.AuthorsService;
import pl.solvd.concerthall.services.impl.AuthorsServiceImpl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        AuthorsService authorsService = new AuthorsServiceImpl();
        authorsService.saveEntity(new Authors(26L,"Alexandr", "Pushkin"));
        System.out.println();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document d = builder.parse(new File("C:\\Users\\alena\\IdeaProjects\\SolvdLab\\ConcertHall\\src\\main\\resources\\classes.xml"));
        NodeList nodeList = d.getElementsByTagName("authors");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node  = nodeList.item(i);
            System.out.println(nodeList.item(i));
            System.out.println(node.getTextContent());
        }
    }
}