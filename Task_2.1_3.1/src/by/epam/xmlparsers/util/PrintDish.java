package by.epam.xmlparsers.util;

import by.epam.xmlparsers.domain.Dish;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class PrintDish {
    public static void print(Dish dish){

        System.out.printf("Блюдо id=%s: фото-%s, название-%s, описание-%s, порция- %s, цена-%s\n",
                dish.getId(), dish.getPhoto(), dish.getName(), dish.getDescription(), dish.getHelpingWeight(), dish.getPrice());
    }
}
