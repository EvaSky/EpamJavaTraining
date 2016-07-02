package by.epam.news.service;

import by.epam.news.service.impl.NewsServiceImpl;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class ServiceFactory {

    private INewsService newsService = new NewsServiceImpl();
    private static ServiceFactory factory = new ServiceFactory();

    public static ServiceFactory getInstance(){
        return factory;
    }

    public INewsService getNewsService(){
        return newsService;
    }
}
