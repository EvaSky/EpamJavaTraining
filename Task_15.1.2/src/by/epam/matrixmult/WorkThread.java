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

            int[]row = task.getRow();
            int[]col = task.getColumn();

            int value = 0;
            int size = task.getMatrix1().getSize();
            for (int k = 0; k < size; k++) {
                value += row[k] * col[k];
            }
            task.setResultElement(value);
        }
    }

    public void stop() {
        stopped = true;
    }


}
