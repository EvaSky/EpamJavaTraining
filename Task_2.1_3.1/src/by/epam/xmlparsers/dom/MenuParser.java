package by.epam.xmlparsers.dom;

import by.epam.xmlparsers.domain.Dish;
import by.epam.xmlparsers.util.PrintDish;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Shahray on 21.05.2016.
 */
public class MenuParser {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File f = new File("Task2_1/src/by/epam/xmlparsers/resources/menu.xml");
        Document doc = builder.parse(f);*/
        DOMParser parser = new DOMParser();
        parser.parse("Task2_1/src/by/epam/xmlparsers/resources/menu.xml");
        Document doc = parser.getDocument();
        Element root = doc.getDocumentElement();

        Map<String, List<Dish>> menu = new HashMap<>();
        NodeList sectionNodes = root.getElementsByTagName("section");
        NodeList dishNodes;
        Dish dish;

        for (int i = 0; i < sectionNodes.getLength(); i++){
            Element sectionElement = (Element)sectionNodes.item(i);
            String sectionName = getSingleChild(sectionElement, "section-name").getTextContent().trim();
            dishNodes = sectionElement.getElementsByTagName("dish");
            List<Dish> dishes = new ArrayList<>();

            for(int j = 0; j < dishNodes.getLength(); j++){
                Element dishElement = (Element)dishNodes.item(j);
                dish = new Dish();
                dish.setId(dishElement.getAttribute("id"));
                dish.setPhoto(getTextFromSingleChild(dishElement, "photo"));
                dish.setName(getTextFromSingleChild(dishElement, "name"));
                dish.setDescription(getTextFromSingleChild(dishElement, "description"));
                dish.setHelpingWeight(getTextFromSingleChild(dishElement, "helping-weight"));
                dish.setPrice(getTextFromSingleChild(dishElement, "price"));

                dishes.add(dish);
            }
            menu.put(sectionName, dishes);

        }

        for(Map.Entry<String, List<Dish>> pair : menu.entrySet()){
            System.out.println(pair.getKey());
            for (Dish d : pair.getValue()){
                PrintDish.print(d);
            }
        }


    }

    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        return (Element)nlist.item(0);
    }
    private static String getTextFromSingleChild(Element element, String childName){
        Element child = getSingleChild(element, childName);
        if (child != null){
            return child.getTextContent().trim();
        }
        else
            return "";
    }











}
