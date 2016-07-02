package by.epam.xmlparsers.sax;

import by.epam.xmlparsers.domain.Dish;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Shahray on 27.05.2016.
 */
public class MenuSaxHandler extends DefaultHandler {
    private List<Dish> dishes = new ArrayList<>();
    private Map<String, List<Dish>> menu = new HashMap<>();
    private Dish dish;
    private String section;
    private StringBuilder text;

    public Map<String, List<Dish>> getMenu() {
        return menu;
    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started");
    }

    public void endDocument() throws SAXException{
        System.out.println("Parsing ended");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.printf("StartElement -> uri: %s, localName: %s, qMame: %s\n", uri, localName, qName );
        text = new StringBuilder();

        if (qName.equals("dish")){
            dish = new Dish();
            dish.setId(attributes.getValue("id"));
        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(localName.toUpperCase().replace("-", "_"));
        switch (tagName){
            case PHOTO:
                dish.setPhoto(text.toString());
                break;
            case NAME:
                dish.setName(text.toString());
                break;
            case DESCRIPTION:
                dish.setDescription(text.toString());
                break;
            case HELPING_WEIGHT:
                dish.setHelpingWeight(text.toString());
                break;
            case PRICE:
                dish.setPrice(text.toString());
                break;
            case DISH:
                dishes.add(dish);
                dish = null;
                break;
            case SECTION_NAME:
                section = text.toString();
                break;
            case SECTION:
                menu.put(section, dishes);
                dishes = new ArrayList<>();
                break;

        }
    }

    public void warning(SAXParseException exception){
        System.err.println("WARNING: line " + exception.getLineNumber() + ": " +exception.getMessage());
    }

    public void error(SAXParseException exception){
        System.err.println("ERROR: line " + exception.getLineNumber() + ": " +exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXParseException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": " +exception.getMessage());
        throw exception;
    }
}
