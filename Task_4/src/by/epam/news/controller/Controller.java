package by.epam.news.controller;

import by.epam.news.command.Command;
import by.epam.news.entity.request_response.Request;
import by.epam.news.entity.request_response.Response;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class Controller {
    CommandHelper helper = new CommandHelper();

    public Response doAction(Request request){
        String commandNAme = request.getCommandName();
        Command command = helper.getCommand(commandNAme);
        Response response = command.execute(request);
        return response;
    }
}
