package by.epam.news.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="category", propOrder = {"nameCategory", "subcategoryList"})
public class Category {
    @XmlAttribute(name = "name", required = true)
    String nameCategory;
    @XmlElement(name = "subcategory")
    List<Subcategory> subcategoryList;

    public Category() {}

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
        this.subcategoryList = new ArrayList<>();
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Subcategory> getSubcategoryList() {
        return subcategoryList;
    }

    public void setSubcategoryList(List<Subcategory> subcategoryList) {
        this.subcategoryList = subcategoryList;
    }

    public boolean add(Subcategory subcategory){ return subcategoryList.add(subcategory);}

    @Override
    public String toString() {
        return "Category{" +
                "nameCategory='" + nameCategory + '\'' +
                ", subcategoryList=" + subcategoryList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (nameCategory != null ? !nameCategory.equals(category.nameCategory) : category.nameCategory != null)
            return false;
        return !(subcategoryList != null ? !subcategoryList.equals(category.subcategoryList) : category.subcategoryList != null);

    }

    @Override
    public int hashCode() {
        int result = nameCategory != null ? nameCategory.hashCode() : 0;
        result = 31 * result + (subcategoryList != null ? subcategoryList.hashCode() : 0);
        return result;
    }
}
