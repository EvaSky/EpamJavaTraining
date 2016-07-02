package by.epam.parser.service;

import by.epam.parser.domain.Attribute;
import by.epam.parser.domain.Document;
import by.epam.parser.domain.Element;
import by.epam.parser.domain.Node;
import by.epam.parser.util.DOMReader;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Olga Shahray on 25.05.2016.
 */
public class MyDOMParser implements IParser{
    public static final char OPEN_ANGLE_BRACKET = '<';
    public static final char CLOSED_ANGLE_BRACKET = '>';
    public static final char SLASH = '/';

    public Document parse(String fileName) throws SAXException, IOException {
        DOMReader reader = DOMReader.getDOMReader(fileName);
        char[] text = reader.getNextReadLines();
        String firstTag = getFirstTag(text);
        if (!isXMLDocument(firstTag)){
            throw new SAXException();
        }
        text = getTextWithoutFirstTag(text);

        boolean isOpenTag=false;
        boolean isCloseTag=false;
        boolean isContent=false;
        String openTag;
        StringBuilder sb = new StringBuilder();
        Element currentElement = null;
        Element nextElement;

        while (true) {
            for (int i = 0; i < text.length; i++) {

                if (text[i] == OPEN_ANGLE_BRACKET) {
                    isOpenTag = true;
                    isCloseTag = false;

                    if (isContent) {
                        isContent = false;
                    }

                } else if (text[i] == SLASH && text[i-1] == '<') {
                    isCloseTag = true;
                    isOpenTag = false;
                    if (!sb.toString().isEmpty()) {
                        currentElement.setTextContent(sb.toString());
                        sb = new StringBuilder();
                    }
                } else if (text[i] == CLOSED_ANGLE_BRACKET) {
                    if (isOpenTag) {
                        openTag = sb.toString().trim();
                        nextElement = new Element();
                        nextElement.setTagName(getTagName(openTag));
                        nextElement.setAttributes(getAttributes(openTag));
                        nextElement.setParent(currentElement);
                        if (currentElement != null) {
                            currentElement.addChild(nextElement);
                        }
                        currentElement = nextElement;

                        sb = new StringBuilder();
                        isOpenTag = false;
                        isContent = true;
                    } else if (isCloseTag) {
                        if (currentElement != null) {
                            Element parent = currentElement.getParent();
                            if(parent!=null){ currentElement = parent;}
                        }
                        sb = new StringBuilder();
                    }
                } else {
                    sb.append(text[i]);
                }
            }
            text = reader.getNextReadLines();
            if (text == null || text.length == 0){
                reader.close();
                break;
            }
        }
        Document document = new Document(currentElement);
        return  document;

    }

    private static String getFirstTag(char[] buf){
        boolean isOpen=false;
        StringBuilder sb = new StringBuilder();
        for (char aBuf : buf) {
            if (aBuf == '<') {
                isOpen = true;
            } else if (aBuf == '>' && isOpen) {
                return sb.toString();
            } else if (isOpen) {
                sb.append(aBuf);
            }
        }
        return null;
    }
    private static char[] getTextWithoutFirstTag(char[] buf){
        String firstTag = getFirstTag(buf);
        if(firstTag != null){
            int from = firstTag.length()+2;
            int to = buf.length;
            return Arrays.copyOfRange(buf, from, to);
        }
        return new char[0];
    }


    private boolean isXMLDocument(String text){
        return text.matches("^\\?xml.*\\?$");
    }
    private static List<Attribute> getAttributes(String s){
        List<Attribute> attributes = new ArrayList<>();
        String[] tempArray = s.split(" +");
        if (tempArray.length>1){
            for (int i = 1; i < tempArray.length; i++){
                String[] attrib = tempArray[i].split(" *= *");
                if (attrib.length > 1) {
                    attributes.add(new Attribute(attrib[0], attrib[1].replace("\"", "")));
                }
            }
        }
        return attributes;
    }

    private static String getTagName(String s){return s.split(" +")[0];}






}
