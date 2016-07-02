package by.epam.news.util;

import by.epam.news.entity.Catalog;
import by.epam.news.entity.Category;
import by.epam.news.entity.News;
import by.epam.news.entity.Subcategory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Olga Shahray on 31.05.2016.
 */
public class CatalogUtil {
    public static final String FILE = "Task_4/src/by/epam/news/resources/catalog.xml";

    public static void createCatalog() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Catalog.class);

        Marshaller m = context.createMarshaller();
        Catalog catalog = new Catalog();
        Category category1 = new Category("Фильмы");
        Category category2 = new Category("Книги");

        catalog.setCategoryList(new ArrayList<>(Arrays.asList(category1, category2)));

        Subcategory subcategory11 = new Subcategory("Триллеры");
        Subcategory subcategory12 = new Subcategory("Мультфильмы");
        List<Subcategory> subList1 = new ArrayList<>(Arrays.asList(subcategory11, subcategory12));
        category1.setSubcategoryList(subList1);

        News news1 = new News();

        news1.setNewsName("Николь Кидман готовит вампирский триллер");
        news1.setProvider("Д.Гренич");
        news1.setDateOfIssue(LocalDate.of(2016, 5, 26));
        news1.setNewsBody("Вампирский триллер «Объятия» исследует странные формы любви\n" +
                " и пытается ответить на вопрос: каково это, жить с монстром,\n" +
                " когда ты с ним одной крови.");

        subcategory11.setNewsList(new ArrayList<>(Arrays.asList(news1)));

        News news2 = new News();

        news2.setNewsName("«Angry Birds в кино» сместил «Первого мстителя» с вершины чарта");
        news2.setProvider("Д.Гренич");
        news2.setDateOfIssue(LocalDate.of(2016, 4, 20));
        news2.setNewsBody("«Angry Birds в кино» возглавил уикендный чарт в американском прокате с результатом $39 млн.");

        subcategory12.setNewsList(new ArrayList<>(Arrays.asList(news2)));

        Subcategory subcategory21 = new Subcategory("Психология");
        List<Subcategory> subList2 = new ArrayList<>(Arrays.asList(subcategory21));
        category2.setSubcategoryList(subList2);

        News news3 = new News();

        news3.setNewsName("Будь лучшей версией себя");
        news3.setProvider("Дэн Вальдшмидт");
        news3.setDateOfIssue(LocalDate.of(2016, 2, 10));
        news3.setNewsBody("Единственное, что разделяет вас и выдающийся успех, — непрерывный прогресс.\n" +
                " 126 простых шагов, чтобы стать лучше.");

        subcategory21.setNewsList(new ArrayList<>(Arrays.asList(news3)));

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(catalog, new FileOutputStream(FILE));
        m.marshal(catalog, System.out);
    }
}
