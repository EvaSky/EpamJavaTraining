package by.epam.matrixmult.util;

import by.epam.matrixmult.TaskQueue;
import by.epam.matrixmult.WorkThread;
import by.epam.matrixmult.domain.Matrix;
import by.epam.matrixmult.domain.Task;
import by.epam.matrixmult.exception.MatrixException;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Olga Shahray on 30.06.2016.
 */
public class Multiplicator{

    public static Matrix multiply(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException{
        // check possibility of multiplying matrices
        if (firstMatrix.getSize() != secondMatrix.getSize()) {
            throw new MatrixException("Incompatible matrices");
        }
        final int v = firstMatrix.getSize();

        // creation of result matrix
        Matrix result = new Matrix(v);
        // creation of task queue
        TaskQueue queue = new TaskQueue();
        //multiplying using 2 child threads
        WorkThread workThread1 = new WorkThread(queue);
        WorkThread workThread2 = new WorkThread(queue);

        for (int i = 0; i < v; i++) {
            Task task = new Task(i, firstMatrix.getRow(i), secondMatrix, result);
            queue.put(task);
        }

        Thread thread1 = new Thread(workThread1);
        Thread thread2 = new Thread(workThread2);
        thread1.start();
        thread2.start();
        workThread1.stop();
        workThread2.stop();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
