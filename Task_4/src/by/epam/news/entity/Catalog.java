package by.epam.news.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {
    @XmlElement(name = "category")
    private List<Category> categoryList;

    public Catalog() {}

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public boolean add(Category category){
        return categoryList.add(category);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "categoryList=" + categoryList +
                '}';
    }
}
