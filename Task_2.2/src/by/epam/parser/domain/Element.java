package by.epam.parser.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 22.05.2016.
 */
public class Element extends Node{
    String tagName;
    String textContent;
    List<Attribute> attributes = new ArrayList<>();
    Element parent;
    List<Element> children;

    public Element(){}

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    public List<Element> getChildren() {
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    public void addChild(Element element){
        if (children != null){children.add(element);}
        else{
            children = new ArrayList<>();
            children.add(element);
        }
    }

    public String getAttribute(String name){
        for (Attribute attr: attributes) {
            if (name.equals(attr.getName())){
                return attr.getValue();
            }
        }
        return null;
    }
    public List<Element> getElementsByTagName(String name){
        List<Element> elements = new ArrayList<>();
        for (Element e: children){
            if(name.equals(e.getTagName())){
                elements.add(e);
            }
            if(e.getChildren() != null && !e.getChildren().isEmpty()){
                elements.addAll(e.getElementsByTagName(name));
            }
        }
        return elements;
    }



}
