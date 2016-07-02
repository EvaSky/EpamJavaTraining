package by.epam.news.command.impl;

import by.epam.news.command.Command;
import by.epam.news.entity.News;
import by.epam.news.entity.request_response.Request;
import by.epam.news.entity.request_response.Response;
import by.epam.news.service.INewsService;
import by.epam.news.service.ServiceFactory;
import by.epam.news.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class FindNewsCommand implements Command {
    @Override
    public Response execute(Request request) {

        String searchedText = request.getSearchedText();
        ServiceFactory factory = ServiceFactory.getInstance();
        INewsService service = factory.getNewsService();

        Response response = new Response();
        try{
            List<News> newsList = service.findNews(searchedText);
            if(!newsList.isEmpty()){
                response.setNews(newsList);
                response.setStatus(true);
                response.setMessage("Search is ended");
            }
            else throw new ServiceException("I cant find news");

        }catch(ServiceException e){
            // logging
            response.setStatus(false);
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }
}
