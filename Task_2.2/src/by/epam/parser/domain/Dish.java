package by.epam.parser.domain;

/**
 * Created by Olga Shahray on 22.05.2016.
 */
public class Dish {
    private String id;
    private String photo;
    private String name;
    private String description;
    private String helpingWeight;
    private String price;

    public Dish() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelpingWeight() {
        return helpingWeight;
    }

    public void setHelpingWeight(String helpingWeight) {
        this.helpingWeight = helpingWeight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", helpingWeight='" + helpingWeight + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
