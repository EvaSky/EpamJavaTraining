package by.epam.matrixmult;

import by.epam.matrixmult.domain.Matrix;
import by.epam.matrixmult.exception.MatrixException;

/**
 * Created by Olga Shahray on 02.07.2016.
 */
public class WorkThread implements Runnable {

    private volatile int rowNum;
    private volatile int[] row;
    private volatile Matrix matrix2;
    private volatile Matrix result;

    public WorkThread(int rowNum, int[] row, Matrix matrix2, Matrix result) {
        this.rowNum = rowNum;
        this.row = row;
        this.matrix2 = matrix2;
        this.result = result;
    }

    @Override
    public void run() {

        int size = matrix2.getSize();

        //использование кэша для лучшей производительности (п.2, 3 статьи https://habrahabr.ru/post/114797/)
        for (int i = 0; i < size; i++) {
            int value = 0;
            int[] column = new int[0];
            try {
                column = matrix2.getColumn(i);
            } catch (MatrixException e) {
                e.printStackTrace();
            }

            for (int j = 0; j < size; j++) {
                value += row[j] * column[j];
            }

            try {
                this.result.setElement(rowNum, i, value);
            } catch (MatrixException e) {
                e.printStackTrace();
            }
        }
    }
}
