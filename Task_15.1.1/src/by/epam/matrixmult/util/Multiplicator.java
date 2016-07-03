package by.epam.matrixmult.util;

import by.epam.matrixmult.WorkThread;
import by.epam.matrixmult.domain.Matrix;
import by.epam.matrixmult.exception.MatrixException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 30.06.2016.
 */
public class Multiplicator{

    public static Matrix multiply(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException{
        // проверка возможности умножения
        if (firstMatrix.getSize() != secondMatrix.getSize()) {
            throw new MatrixException("Incompatible matrices");
        }
        int v = firstMatrix.getSize();
        List<Thread> threads = new ArrayList<>();

        // создание матрицы результата
        Matrix result = new Matrix(v);

        //умножение с использованием дочерних потоков
        Thread th;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                th = new Thread(new WorkThread(i, j, firstMatrix, secondMatrix, result));
                threads.add(th);
                th.start(); //запускаем потоки
            }
        }

        for (Thread thread : threads){
            try {
                thread.join();  //дожидаемся исполнения всех потоков
            } catch (InterruptedException e) {
                throw new MatrixException("Error multiplying matrices", e);
            }
        }

        return result;
    }

}
