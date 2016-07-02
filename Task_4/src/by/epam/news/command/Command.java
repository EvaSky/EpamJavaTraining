package by.epam.news.command;

import by.epam.news.entity.request_response.Request;
import by.epam.news.entity.request_response.Response;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public interface Command {
    Response execute(Request request);
}
