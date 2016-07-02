package by.epam.matrixmult;

import by.epam.matrixmult.domain.MatrixCreator;
import by.epam.matrixmult.domain.Matrix;
import by.epam.matrixmult.exception.MatrixException;
/**
 * Необходимо разработать многопоточное приложение, позволяющее перемножать квадратные матрицы одного(любого) порядка
 * При необходимости для синхронизации использовать только инструменты, доступные в версии Java 1.4.
 *
 * 15.1.1 Приведите решение, когда количество дочерних потоков не ограничено
 * 15.1.2 Приведите решение, когда количество дочерних потоков ограничено двумя
 */

/**
 * Created by Olga Shahray on 30.06.2016.
 */
public class Main {
    public static final int[][] array1 =  {
            {1,2,5,-1,0},
            {2,4,-3,1,1},
            {2,0,-2,2,6},
            {1,7,5,-2,0},
            {0,4,-1,4,1}
    };
    public static final int[][] array2 =  {
            {1,1,1,-1,0},
            {3,2,-1,1,1},
            {4,6,-2,0,6},
            {1,2,3,-1,4},
            {0,0,-2,4,3}
    };
    public static final int[][] result = {
            {26,33,-14,2,28},
            {3,-6,5,5,-7},
            {-4,-6,0,20,14},
            {40,41,-22,8,29},
            {12,10,8,4,17}
    };

    public static void main(String[] args) {
        try {
            Matrix p = MatrixCreator.fillFromArray(5, array1);
            System.out.println("Matrix first is: " + p);
            Matrix q = MatrixCreator.fillFromArray(5, array2);
            System.out.println("Matrix second is: " + q);

            Multiplicator mult = new Multiplicator(p,q);
            Matrix res = mult.multiply();
            System.out.println("Matrices product is " + res);

            System.out.println("Is result correct? " + (res.equals(MatrixCreator.fillFromArray(5, result)) ? "yes" : "no"));

        } catch (MatrixException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
