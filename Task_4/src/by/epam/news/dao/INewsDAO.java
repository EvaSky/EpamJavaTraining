package by.epam.news.dao;

import by.epam.news.dao.exception.DAOException;
import by.epam.news.entity.Catalog;
import by.epam.news.entity.Category;
import by.epam.news.entity.News;
import by.epam.news.entity.Subcategory;

import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public interface INewsDAO {

    void save(News news, String category, String subcategory) throws DAOException;

    List<News> find(String searchedText) throws DAOException;

    List<News> getAllNews() throws DAOException;
}
