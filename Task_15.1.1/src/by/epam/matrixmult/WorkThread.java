package by.epam.matrixmult;

import by.epam.matrixmult.domain.Matrix;
import by.epam.matrixmult.exception.MatrixException;

/**
 * Created by Olga Shahray on 02.07.2016.
 */
public class WorkThread implements Runnable{
    int i;
    int j;
    Matrix matrix1;
    Matrix matrix2;
    Matrix result;

    public WorkThread(int i, int j, Matrix matrix1, Matrix matrix2, Matrix result) {
        this.i = i;
        this.j = j;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
    }

    @Override
    public void run() {
        int value = 0;
        int size = matrix1.getSize();
        for (int k = 0; k < size; k++) {
            try {
                value += matrix1.getElement(i, k) * matrix2.getElement(k, j);
            } catch (MatrixException e) {
                e.printStackTrace();
            }
        }
        try {
            this.result.setElement(i, j, value);
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }
}
