package by.epam.matrixmult;

import by.epam.matrixmult.domain.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Olga Shahray on 03.07.2016.
 */
public class TaskQueue {
    private volatile List<Task> tasks = new LinkedList<>();

    public synchronized void put(Task task)
    {
        tasks.add(task);
        this.notifyAll();
    }

    public synchronized Task take()
    {
        while (isEmpty())
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return tasks.remove(0);
    }

    public boolean isEmpty(){
        return tasks.size()==0;
    }

    public synchronized int size(){
        return tasks.size();
    }
}
