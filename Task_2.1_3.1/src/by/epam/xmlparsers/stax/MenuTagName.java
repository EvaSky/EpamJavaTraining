package by.epam.xmlparsers.stax;

/**
 * Created by Olga Shahray on 27.05.2016.
 */
public enum MenuTagName {
    MENU, SECTION, SECTION_NAME, DISHES, DISH, PHOTO, NAME, DESCRIPTION,HELPING_WEIGHT, PRICE;

    public static MenuTagName getElementTagName(String element){
        switch (element){
            case "menu":
                return MENU;
            case "section":
                return SECTION;
            case "section-name":
                return SECTION_NAME;
            case "dishes":
                return DISHES;
            case "dish":
                return DISH;
            case "photo":
                return PHOTO;
            case "name":
                return NAME;
            case "description":
                return DESCRIPTION;
            case "helping-weight":
                return HELPING_WEIGHT;
            case "price":
                return PRICE;
            default:
                throw new EnumConstantNotPresentException(MenuTagName.class, element);
        }
    }
}
