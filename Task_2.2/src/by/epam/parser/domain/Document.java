package by.epam.parser.domain;

/**
 * Created by Olga Shahray on 22.05.2016.
 */
public class Document extends Node{
    String name;
    Element rootElement;

    public Document(Element rootElement) {
        this.rootElement = rootElement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getRootElement() {
        return rootElement;
    }

    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }
}
