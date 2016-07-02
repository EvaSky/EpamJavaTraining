package by.epam.news.command.impl;

import by.epam.news.command.Command;
import by.epam.news.entity.request_response.Request;
import by.epam.news.entity.request_response.Response;
import by.epam.news.service.INewsService;
import by.epam.news.service.ServiceFactory;
import by.epam.news.service.exception.ServiceException;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class SaveNewNewsCommand implements Command {
    @Override
    public Response execute(Request request) {
        String newsName = request.getNewsName();
        //String date = request.getDate();
        String provider = request.getProvider();
        String newsBody = request.getNewsBody();
        String category = request.getCategory();
        String subcategory = request.getSubcategory();

        ServiceFactory factory = ServiceFactory.getInstance();
        INewsService service = factory.getNewsService();

        Response response = new Response();
        try{
            service.saveNewNews(newsName, provider, newsBody, category, subcategory);
            response.setStatus(true);
            response.setMessage("News was added");
        }catch(ServiceException e){
            // logging
            response.setStatus(false);
            response.setErrorMessage("errot saving news!");
        }
        return response;
    }
}
