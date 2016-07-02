package by.epam.news.dao.impl;

import by.epam.news.dao.INewsDAO;
import by.epam.news.dao.exception.DAOException;
import by.epam.news.entity.Catalog;
import by.epam.news.entity.Category;
import by.epam.news.entity.News;
import by.epam.news.entity.Subcategory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class NewsDAOImpl implements INewsDAO{

    private JAXBContext context ;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private final static String FILE = "Task_4/src/by/epam/news/resources/catalog.xml";

    public NewsDAOImpl() throws DAOException {
        try {
            context = JAXBContext.newInstance(Catalog.class);
            unmarshaller = context.createUnmarshaller();
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            throw new DAOException("error", e);
        }
    }

    @Override
    public void save(News news, String categoryName, String subcategoryName) throws DAOException {
        Catalog catalog = getCatalog();
        Category category = getOrCreateCategoryByName(catalog, categoryName);
        Subcategory subcategory = getOrCreateSubcategoryByName(category, subcategoryName);
        if (!subcategory.add(news)) throw new DAOException("Error during the action: save news");
        updateCatalog(catalog);
    }

    @Override
    public List<News> find(String searchedText) throws DAOException {
        List<News> allNews = getAllNews();
        List<News> searchedNews = new ArrayList<>();
        for (News news : allNews) {
            if (news.getNewsName().contains(searchedText) ||
                    news.getProvider().contains(searchedText) ||
                    news.getNewsBody().contains(searchedText)) {
                searchedNews.add(news);
            }
        }
        return searchedNews;
    }

    @Override
    public List<News> getAllNews() throws DAOException {
        Catalog catalog = getCatalog();
        List<News> allNews = new ArrayList<>();
        for (Category category : catalog.getCategoryList()) {
            for (Subcategory subcategory : category.getSubcategoryList()) {
                for (News news : subcategory.getNewsList()) {
                    allNews.add(news);
                }
            }
        }
        return allNews;
    }

    private Catalog getCatalog() throws DAOException {
        Catalog catalog = null;
        try {
            catalog = (Catalog)unmarshaller.unmarshal(new FileReader(FILE));
        } catch (Exception e) {
            throw new DAOException("Error during the action: get catalog", e);
        }
        if (catalog != null) return catalog;
        else return new Catalog();
    }

    private Category getOrCreateCategoryByName(Catalog catalog, String name) throws DAOException {
        for(Category category : catalog.getCategoryList()){
            if(category.getNameCategory().equals(name)){
                return category;
            }
        }
        Category newCategory = new Category(name);
        catalog.add(newCategory);
        return newCategory;
    }


    private Subcategory getOrCreateSubcategoryByName(Category category, String name){
        for(Subcategory subcategory : category.getSubcategoryList()){
            if(subcategory.getNameSubcategory().equals(name)){
                return subcategory;
            }
        }
        Subcategory newSubcategory = new Subcategory(name);
        category.add(newSubcategory);
        return newSubcategory;
    }

    private void updateCatalog(Catalog catalog) throws DAOException {
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(catalog, new FileOutputStream(FILE));
        } catch (Exception e) {
            throw new DAOException("Error during the action: save catalog", e);
        }
    }


}
