package by.epam.matrixmult;

import by.epam.matrixmult.domain.Task;
import by.epam.matrixmult.exception.MatrixException;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Olga Shahray on 02.07.2016.
 */
public class WorkThread implements Runnable{

    private volatile TaskQueue queue;
    private volatile boolean stopped;

    public WorkThread(TaskQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(stopped && !queue.isEmpty()) {
            Task task = queue.take(); //the thread takes a task and remove it

            int size = task.getMatrix2().getSize();
            int[]row = task.getRow();
            int rowNum = task.getRowNum();

            //using cashe for  efficiency (p.2, 3 article https://habrahabr.ru/post/114797/)
            for (int i = 0; i < size; i++) {
                int value = 0;
                int[] column = new int[size];
                try {
                    column = task.getMatrix2().getColumn(i);
                } catch (MatrixException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < size; j++) {
                    value += row[j] * column[j];
                }

                try {
                    task.setResultElement(i, value);
                } catch (MatrixException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stop() {
        stopped = true;
    }


}
