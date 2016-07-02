package by.epam.news.start;

import by.epam.news.controller.Controller;
import by.epam.news.entity.*;

import by.epam.news.entity.request_response.Request;
import by.epam.news.entity.request_response.Response;

import by.epam.news.util.CatalogUtil;
import by.epam.news.view.View;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;


/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {


       CatalogUtil.createCatalog();

        Controller controller = new Controller();
        View view = new View();

        Request request = view.doUserActionSave();
        Response response = controller.doAction(request);
        System.out.println(response.isStatus());
        view.printAnswer(response);

        Request request1 = view.doUserActionFind("");
        response = controller.doAction(request1);
        System.out.println(response.isStatus());
        System.out.println(response.getNews());

    }
}
