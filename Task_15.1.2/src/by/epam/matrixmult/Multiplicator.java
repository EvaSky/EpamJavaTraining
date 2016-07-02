package by.epam.matrixmult;

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

    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private volatile Queue<Task> queue = new PriorityQueue<>();
    private volatile Boolean isCompleted;

    public Multiplicator(Matrix firstMatrix, Matrix secondMatrix) throws MatrixException {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        // проверка возможности умножения
        if (firstMatrix.getSize() != secondMatrix.getSize()) {
            throw new MatrixException("Incompatible matrices");
        }
        isCompleted = false;
    }

    public Matrix multiply() throws MatrixException{
        int v = firstMatrix.getSize();
        List<Thread> threads = new ArrayList<>();

        // создание матрицы результата
        Matrix result = new Matrix(v);

        //умножение с использованием 2-х дочерних потоков
        Thread th1 = new Thread(new WorkThread(queue, isCompleted));
        Thread th2 = new Thread(new WorkThread(queue, isCompleted));
        th1.start();
        th2.start();

        Task task;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                task = new Task(i, j, firstMatrix, secondMatrix, result);
                queue.offer(task);
            }
        }
        isCompleted = true;

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
