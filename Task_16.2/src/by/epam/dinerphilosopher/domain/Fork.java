package by.epam.dinerphilosopher.domain;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Olga Shahray on 12.07.2016.
 */
//Вилка - разделяемый ресурс для философов (потоков)
public class Fork {

    private final Lock lock = new ReentrantLock();

    //взять вилку, т.е. заблокировать ресурс
    public void onTake() {
        lock.lock();
    }

    //положить вилку, т.е. разблокировать ресурс
    public void onPut() {
        lock.unlock();
    }
}
