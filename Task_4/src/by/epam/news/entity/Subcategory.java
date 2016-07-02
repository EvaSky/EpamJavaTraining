package by.epam.news.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="subcategory", propOrder = {"nameSubcategory", "newsList"})
public class Subcategory {
    @XmlAttribute(name = "name", required = true)
    String nameSubcategory;
    @XmlElement(name = "news")
    List<News> newsList;

    public Subcategory() {}

    public Subcategory(String nameSubcategory) {
        this.nameSubcategory = nameSubcategory;
        this.newsList = new ArrayList<>();
    }

    public String getNameSubcategory() {
        return nameSubcategory;
    }

    public void setNameSubcategory(String nameSubcategory) {
        this.nameSubcategory = nameSubcategory;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public boolean add(News news){
        return newsList.add(news);
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "nameSubcategory='" + nameSubcategory + '\'' +
                ", newsList=" + newsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subcategory that = (Subcategory) o;

        if (nameSubcategory != null ? !nameSubcategory.equals(that.nameSubcategory) : that.nameSubcategory != null)
            return false;
        return !(newsList != null ? !newsList.equals(that.newsList) : that.newsList != null);

    }

    @Override
    public int hashCode() {
        int result = nameSubcategory != null ? nameSubcategory.hashCode() : 0;
        result = 31 * result + (newsList != null ? newsList.hashCode() : 0);
        return result;
    }
}
