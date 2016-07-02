package by.epam.parser.factory;

import by.epam.parser.service.IParser;
import by.epam.parser.service.MyDOMParser;

/**
 * Created by Olga Shahray on 24.05.2016.
 */
public class Factory {
    private static final Factory factory = new Factory();

    public static Factory getInstance(){
        return factory;
    }

    public IParser getParser(){
        return new MyDOMParser();
    }
}
