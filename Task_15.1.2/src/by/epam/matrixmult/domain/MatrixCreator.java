package by.epam.matrixmult.domain;

import by.epam.matrixmult.domain.Matrix;
import by.epam.matrixmult.exception.MatrixException;

/**
 * Created by Olga Shahray on 30.06.2016.
 */
public class MatrixCreator {

    //создание матрицы и заполнение случайными числами
    public static Matrix fillRandomized(int size, int limit) throws MatrixException {
        Matrix matrix = new Matrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = (int) (Math.random() * limit);
                matrix.setElement(i, j, value);
            }
        }
        return  matrix;
    }
    //заполнение матрицы одним элементом
    public static Matrix fillOneElement(int size,int x) throws MatrixException {
        Matrix matrix = new Matrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.setElement(i, j, x);
            }
        }
        return matrix;
    }
    //заполнение матрицы массивом чисел
    public static Matrix fillFromArray(int size, int[][] array) throws MatrixException {
        Matrix matrix = new Matrix(size);
        if (size != array.length && size != array[0].length) {
            throw new MatrixException("Error creating matrix from array: unequal size");
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.setElement(i, j, array[i][j]);
            }
        }
        return matrix;
    }

}
