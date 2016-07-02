package by.epam.xmlparsers.sax;

import by.epam.xmlparsers.domain.Dish;
import by.epam.xmlparsers.util.PrintDish;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Shahray on 27.05.2016.
 */
public class SaxMain {
    public static void main(String[] args) throws SAXException, IOException {

        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("Task2_1/src/by/epam/xmlparsers/resources/menu.xml"));
        reader.setFeature("http://xml.org/sax/features/validation", true);
        reader.setFeature("http://xml.org/sax/features/namespaces", true);
        reader.setFeature("http://xml.org/sax/features/string-interning", true);
        reader.setFeature("http://apache.org/xml/features/validation/schema", false);

        Map<String, List<Dish>> menu = handler.getMenu();

        for (Map.Entry<String, List<Dish>> pair : menu.entrySet()) {
            System.out.println(pair.getKey());
            for (Dish d : pair.getValue()) {
                PrintDish.print(d);
            }
        }
    }
}
