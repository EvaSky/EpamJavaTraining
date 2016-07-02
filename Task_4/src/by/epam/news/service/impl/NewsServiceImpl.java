package by.epam.news.service.impl;

import by.epam.news.dao.DAOFactory;
import by.epam.news.dao.INewsDAO;
import by.epam.news.dao.exception.DAOException;
import by.epam.news.entity.News;
import by.epam.news.service.INewsService;
import by.epam.news.service.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

import static by.epam.news.util.ExceptionUtil.check;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class NewsServiceImpl implements INewsService{
    public void saveNewNews(String...params) throws ServiceException {
        // validation
        if (params.length != 5){
            throw new ServiceException("incorrect params");
        }
        String newsName = params[0];
        String provider = params[1];
        String newsBody = params[2];
        String category = params[3];
        String subcategory = params[4];
        if (!check(newsName) && !check(provider) && !check(newsBody) && !check(category) && !check(subcategory) ){
            throw new ServiceException("incorrect params");
        }
        LocalDate date = LocalDate.now();

        News newNews = new News(newsName, provider, date, newsBody);

        try{
            DAOFactory factory = DAOFactory.getFactory();
            INewsDAO newsDAO = factory.getNewsDAO();
            newsDAO.save(newNews, category, subcategory);

        }catch(DAOException e){
           throw new ServiceException("Error saving new news", e);
        }

    }
    @Override
    public List<News> findNews(String param)throws ServiceException{

        String searchedText = param;
        if (searchedText== null){
            throw new ServiceException("incorrect params");
        }
        try{
            DAOFactory factory = DAOFactory.getFactory();
            INewsDAO newsDAO = factory.getNewsDAO();
            if (searchedText.isEmpty()){
                return newsDAO.getAllNews();
            }
            return newsDAO.find(searchedText);

        }catch(DAOException e){
            throw new ServiceException("Error during the action: find news", e);
        }
    }
}
