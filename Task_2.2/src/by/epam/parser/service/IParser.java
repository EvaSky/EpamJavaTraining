package by.epam.parser.service;

import by.epam.parser.domain.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by Olga Shahray on 24.05.2016.
 */
public interface IParser {
    Document parse(String fileName) throws SAXException, IOException;
}
