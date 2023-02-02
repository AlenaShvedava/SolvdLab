package pl.solvd.concerthall.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.solvd.concerthall.models.Authors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        List<Authors> authorsList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document d = builder.parse(new File("ConcertHall//src//main//resources//concerthall.xml"));
        d.getDocumentElement().normalize();
        System.out.println("Root Element: " + d.getDocumentElement().getNodeName());
        NodeList nodeAuthorsList = d.getElementsByTagName("authors");
        for (int i = 0; i < nodeAuthorsList.getLength(); i++) {
            Node node = nodeAuthorsList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                authorsList.add(new Authors(element.getElementsByTagName("type").item(0).getTextContent(), element.getElementsByTagName("firstname").item(0).getTextContent(), element.getElementsByTagName("lastname").item(0).getTextContent()));
            }
        }
        System.out.println(authorsList.toString().replace("[", "").replace("]", "").replace(",", "\n"));
    }
}
