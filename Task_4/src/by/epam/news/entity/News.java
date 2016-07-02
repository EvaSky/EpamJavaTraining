package by.epam.news.entity;

import by.epam.news.util.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="News", propOrder = {"newsName", "provider", "dateOfIssue", "newsBody"})
public class News {

    @XmlElement(name = "name", required = true)
    private String newsName;
    @XmlElement(required = true)
    private String provider;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "date", required = true)
    private LocalDate dateOfIssue;
    @XmlElement(name = "body", required = true)
    private String newsBody;

    public News() {}

    public News(String newsName, String provider, LocalDate dateOfIssue, String newsBody) {
        this.newsName = newsName;
        this.provider = provider;
        this.dateOfIssue = dateOfIssue;
        this.newsBody = newsBody;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsName='" + newsName + '\'' +
                ", provider='" + provider + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", newsBody='" + newsBody + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (newsName != null ? !newsName.equals(news.newsName) : news.newsName != null) return false;
        if (provider != null ? !provider.equals(news.provider) : news.provider != null) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(news.dateOfIssue) : news.dateOfIssue != null) return false;
        return !(newsBody != null ? !newsBody.equals(news.newsBody) : news.newsBody != null);

    }

    @Override
    public int hashCode() {
        int result = newsName != null ? newsName.hashCode() : 0;
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (newsBody != null ? newsBody.hashCode() : 0);
        return result;
    }
}
