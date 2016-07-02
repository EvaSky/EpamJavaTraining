package by.epam.xmlparsers.stax;

import by.epam.xmlparsers.domain.Dish;
import by.epam.xmlparsers.util.PrintDish;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Olga Shahray on 27.05.2016.
 */
public class StaxMenuParser {
    public static void main(String[] args) throws FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try{
            InputStream input = new FileInputStream("Task2_1/src/by/epam/xmlparsers/resources/menu.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            Map<String, List<Dish>> menu = process(reader);

            for(Map.Entry<String, List<Dish>> pair : menu.entrySet()){
                System.out.println(pair.getKey());
                for (Dish d : pair.getValue()){
                    PrintDish.print(d);
                }
            }
        }catch (XMLStreamException e){
            e.printStackTrace();
        }
    }

    private static Map<String,List<Dish>> process(XMLStreamReader reader) throws XMLStreamException {
        Map<String, List<Dish>> menu = new HashMap<>();
        List<Dish> dishes = new ArrayList<>();
        Dish dish = null;
        String section = null;
        MenuTagName elementName = null;
        while (reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName){
                        case DISH:
                            dish = new Dish();
                            dish.setId(reader.getAttributeValue(null, "id"));
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()){
                        break;
                    }
                    switch (elementName){
                        case PHOTO:
                            dish.setPhoto(text);
                            break;
                        case NAME:
                            dish.setName(text);
                            break;
                        case DESCRIPTION:
                            dish.setDescription(text);
                            break;
                        case HELPING_WEIGHT:
                            dish.setHelpingWeight(text);
                            break;
                        case PRICE:
                            dish.setPrice(text);
                            break;
                        case SECTION_NAME:
                            section = text;
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case DISH:
                            dishes.add(dish);
                            break;
                        case SECTION:
                            menu.put(section, dishes);
                            dishes = new ArrayList<>();
                            break;
                    }
                }

            }
        return menu;
        }
    }
