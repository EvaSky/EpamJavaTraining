package by.epam.dinerphilosopher.domain;

import by.epam.dinerphilosopher.util.Util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Olga Shahray on 12.07.2016.
 */
public class Philosopher implements Runnable{

    private final Semaphore waiter;    //в качестве официанта выступает семафор, он следит за философами
    private final AtomicInteger eaten; //количество еды, съеденной философом (в единицу времени 1 единица еды)
    private final long eatTime;   //время, в течение которого философ ест
    private final long thinkTime; //время, в течение которого философ думает
    private final Fork fork1; //левая вилка (условно)
    private final Fork fork2; //правая вилка (условно)
    private int count;

    public Philosopher(Semaphore waiter, AtomicInteger eaten,
                       long eatTime, long thinkTime,
                       Fork fork1, Fork fork2
    ) {
        this.waiter = waiter;
        this.eaten = eaten;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
        this.fork1 = fork1;
        this.fork2 = fork2;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //философ запрашивает у официанта-семафора разрешение поесть (на доступ к ресурсам)
                //пока разрешение не дано философ ждет (поток блокируется)
                waiter.acquire();

                //процесс еды: взять 2 вилки, поесть, положить 2 вилки
                take(fork1);
                take(fork2);
                eat();
                put(fork1);
                put(fork2);

                //после того как филосов поел, он думает
                think();
            } catch (InterruptedException e) {
                // do nothing
            } finally {
                //после окончания работы с ресурсом полученное ранее разрешение от семафора надо освободить
                waiter.release();
                think();
            }
        }
        eaten.set(count);
    }

    private void take(Fork fork) {
        fork.onTake();
    }

    private void put(Fork fork) {
        fork.onPut();
    }

    private void eat() {
        count++;
        Util.waitMillis(eatTime);
    }

    private void think() {
        Util.waitMillis(thinkTime);
    }
}
