package by.epam.xmlparsers.domain;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != this.getClass()) return false;

        Dish dish = (Dish) o;

        if (!id.equals(dish.id)) return false;
        if (photo != null ? !photo.equals(dish.photo) : dish.photo != null) return false;
        if (!name.equals(dish.name)) return false;
        if (description != null ? !description.equals(dish.description) : dish.description != null) return false;
        if (helpingWeight != null ? !helpingWeight.equals(dish.helpingWeight) : dish.helpingWeight != null)
            return false;
        return !(price != null ? !price.equals(dish.price) : dish.price != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (helpingWeight != null ? helpingWeight.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
