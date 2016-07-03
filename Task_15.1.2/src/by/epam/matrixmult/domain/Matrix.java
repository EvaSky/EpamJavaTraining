package by.epam.matrixmult.domain;

import by.epam.matrixmult.exception.MatrixException;

import java.util.Arrays;

/**
 * Created by Olga Shahray on 30.06.2016.
 */
public class Matrix {
    private volatile int[][] a;

    public Matrix(int n) throws MatrixException {
        // check negative range of matrix
        if (n < 1) {
            throw new MatrixException("Matrix size must be positive number");
        }
        a = new int[n][n];
    }

    public int getSize() {
        return a.length;
    }

    public int getElement(int i, int j) throws MatrixException {
        if (checkRange(i, j)) { // check i and j
            return a[i][j];
        }
        throw new MatrixException();
    }

    public synchronized void setElement(int i, int j, int value) throws MatrixException {
        if (checkRange(i, j)) { // check i and j
            a[i][j] = value;
        } else {
            throw new MatrixException("Position of element does't agree with matrix size");
        }
    }

    // check if i,j is not out of bounds of matrix
    private boolean checkRange(int i, int j) {
        return (i >= 0 && i < a.length && j >= 0 && j < a[0].length);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : " + a.length + "x" + a[0].length + "\n");
        for (int[] row : a) {
            for (int value : row) {
                s.append(value + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != this.getClass()) return false;

        Matrix matrix = (Matrix) o;

        return Arrays.deepEquals(a, matrix.a);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(a);
    }
}
