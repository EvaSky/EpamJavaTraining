package by.epam.matrixmult.domain;

import by.epam.matrixmult.exception.MatrixException;

/**
 * Created by Olga Shahray on 02.07.2016.
 */
public class Task{
    private int i;
    private int j;
    private Matrix matrix1;
    private Matrix matrix2;
    private volatile Matrix result;

    public Task(int i, int j, Matrix matrix1, Matrix matrix2, Matrix result) {
        this.i = i;
        this.j = j;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Matrix getMatrix1() {
        return matrix1;
    }

    public Matrix getMatrix2() {
        return matrix2;
    }

    public Matrix getResult() {
        return result;
    }

    public int[] getRow(){
        int size = matrix1.getSize();
        int[]row = new int[size];
        for (int k = 0; k < size; k++) {
            try {
                row[k] = matrix1.getElement(i,k);
            } catch (MatrixException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    public int[] getColumn(){
        int size = matrix2.getSize();
        int[]column = new int[size];
        for (int k = 0; k < size; k++) {
            try {
                column[k] = matrix2.getElement(k,j);
            } catch (MatrixException e) {
                e.printStackTrace();
            }
        }
        return column;
    }

    public void setResultElement(int value){
        try {
            result.setElement(i, j, value);
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }

}
