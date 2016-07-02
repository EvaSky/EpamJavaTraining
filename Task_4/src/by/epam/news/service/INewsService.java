package by.epam.news.service;

import by.epam.news.entity.News;
import by.epam.news.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public interface INewsService {

    void saveNewNews(String... params) throws ServiceException;

    List<News> findNews(String text) throws ServiceException;
}
