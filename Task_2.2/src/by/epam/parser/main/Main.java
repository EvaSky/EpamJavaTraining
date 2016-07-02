package by.epam.parser.main;


import by.epam.parser.domain.Dish;
import by.epam.parser.domain.Document;
import by.epam.parser.domain.Element;
import by.epam.parser.factory.Factory;
import by.epam.parser.service.IParser;
import by.epam.parser.service.MyDOMParser;
import by.epam.parser.util.PrintDish;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Shahray on 23.05.2016.
 */
public class Main {
    public static void main(String[] args) throws SAXException, IOException {
        Factory f = Factory.getInstance();
        IParser myParser = f.getParser();
        String file = "Task_2.2_3.2/src/by/epam/parser/resources/menu.xml";
        Document doc = myParser.parse(file);
        Element root = doc.getRootElement();

        Map<String, List<Dish>> menu = new HashMap<>();
        List<Element> sectionNodes = root.getElementsByTagName("section");
        List<Element> dishNodes;
        Dish dish;

        for (Element sectionElement : sectionNodes) {
            String sectionName = getTextFromSingleChild(sectionElement, "section-name");
            dishNodes = sectionElement.getElementsByTagName("dish");
            List<Dish> dishes = new ArrayList<>();

            for (Element dishElement : dishNodes) {
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
        List<Element> nlist = element.getElementsByTagName(childName);
        return nlist.isEmpty() ? null : nlist.get(0);
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
