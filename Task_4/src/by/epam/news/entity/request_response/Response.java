package by.epam.news.entity.request_response;

import by.epam.news.entity.Catalog;
import by.epam.news.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class Response {

    private boolean status;
    private String message;
    private String errorMessage;
    private List<News> newsList = new ArrayList<>();
    private Catalog catalog;

    public Response() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<News> getNews() {
        return newsList;
    }

    public void setNews(List<News> news) {
        this.newsList = news;
    }

    public boolean add(News news){
        return newsList.add(news);
    }
}
