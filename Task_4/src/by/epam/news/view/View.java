package by.epam.news.view;
import by.epam.news.entity.request_response.Request;
import by.epam.news.entity.request_response.Response;


/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class View {

    public Request doUserActionSave(){

        Request request = new Request();
        request.setCommandName("SAVE_NEW_NEWS");

        request.setNewsName("Ты можешь больше, чем ты думаешь");
        request.setProvider("Томас Армстронг");
        request.setNewsBody("Но все же, что значит быть умным? Интеллект  — это не  только хорошие оценки,\n" +
                "высокие результаты в  тестах и  умение запоминать. Каждая из девяти глав книги «Ты можешь больше,\n" +
                "чем ты думаешь» подробно рассказывает о каждом из девяти видов интеллекта.");
        request.setCategory("Книги");
        request.setSubcategory("Психология");

        return request;
    }

    public Request doUserActionFind(String s){

        Request request = new Request();
        request.setCommandName("FIND_NEWS");
        request.setSearchedText(s);
        return request;
    }

    public void printAnswer(Response response){
        System.out.println(response.getMessage());
    }

    public void printAnswer(String message){
        System.out.println(message);
    }

}
