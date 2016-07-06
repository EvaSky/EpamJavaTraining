package by.epam.matrixmult.domain;

import by.epam.matrixmult.exception.MatrixException;

import java.util.Arrays;

/**
 * Created by Olga Shahray on 02.07.2016.
 */
public class Task{

    private volatile int rowNum;
    private volatile int[] row;
    private volatile Matrix matrix2;
    private volatile Matrix result;

    public Task(int rowNum, int[]row, Matrix matrix2, Matrix result) {
        this.rowNum = rowNum;
        this.row = row;
        this.matrix2 = matrix2;
        this.result = result;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int[] getRow() {
        return row;
    }

    public Matrix getMatrix2() {
        return matrix2;
    }

    public Matrix getResult() {
        return result;
    }

    public void setResultElement(int j, int value) throws MatrixException {
        result.setElement(rowNum, j, value);
    }

}
