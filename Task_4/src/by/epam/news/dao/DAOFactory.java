package by.epam.news.dao;

import by.epam.news.dao.exception.DAOException;
import by.epam.news.dao.impl.NewsDAOImpl;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class DAOFactory {
    private INewsDAO newsDAO;

    private static DAOFactory factory = new DAOFactory();

    public static DAOFactory getFactory(){
        return factory;
    }

    public INewsDAO getNewsDAO() throws DAOException {
        if (newsDAO == null){
            newsDAO = new NewsDAOImpl();
        }
        return newsDAO;
    }
}
