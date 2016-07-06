package by.epam.aggregator;

/**
 * Created by Olga Shahray on 06.07.2016.
 */
public enum AggregateMethod {

    SUM(1),
    MULTIPLICATION(2),
    SQUARES_SUM(3);

    private int id;

    AggregateMethod(int id) {
        this.id = id;
    }

    public static AggregateMethod valueOf(int id) {
        if (id == SUM.id) {
            return AggregateMethod.SUM;
        } else if (id == MULTIPLICATION.id) {
            return AggregateMethod.MULTIPLICATION;
        } else if (id == SQUARES_SUM.id) {
            return AggregateMethod.SQUARES_SUM;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
