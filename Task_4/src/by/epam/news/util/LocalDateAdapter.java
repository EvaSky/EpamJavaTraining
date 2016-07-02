package by.epam.news.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by Olga Shahray on 01.06.2016.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String str) throws Exception {
        return LocalDate.parse(str);
    }

    @Override
    public String marshal(LocalDate ld) throws Exception {
        return ld.toString();
    }
}
