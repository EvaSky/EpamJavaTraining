package by.epam.matrixmult;

import by.epam.matrixmult.domain.Task;
import by.epam.matrixmult.exception.MatrixException;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Olga Shahray on 02.07.2016.
 */
public class WorkThread implements Runnable{
    private Queue<Task> queue = new PriorityQueue<>();
    private Boolean isCompleted;

    public WorkThread(Queue<Task> queue, Boolean isCompleted) {
        this.queue = queue;
        this.isCompleted = isCompleted;
    }

    @Override
    public void run() {
        while(!isCompleted && !queue.isEmpty()) {
            while(queue.isEmpty()){ //если очередь пуста, ждем добавления таска
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Task task = queue.remove(); //берем таск из очереди и удаляем его
            int value = 0;
            for (int k = 0; k < task.getMatrix1().getSize(); k++) {
                try {
                    value += task.getMatrix1().getElement(task.getI(), k) * task.getMatrix2().getElement(k, task.getJ());
                } catch (MatrixException e) {
                    e.printStackTrace();
                }
            }
            try {
                task.getResult().setElement(task.getI(), task.getJ(), value);
            } catch (MatrixException e) {
                e.printStackTrace();
            }
        }
    }
}
