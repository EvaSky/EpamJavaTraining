package by.epam.dinerphilosopher;

import by.epam.dinerphilosopher.domain.Fork;
import by.epam.dinerphilosopher.domain.Philosopher;
import by.epam.dinerphilosopher.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Olga Shahray on 12.07.2016.
 */
public class SemaphoreEngine {

    public static final int SECONDS = 2; //время работы потока-философа

    private final List<Philosopher> philosophers = new ArrayList<>(); //список всех философов
    private final int n;          //число философов, число вилок
    private final long thinkTime; // время для размышлений
    private final long eatTime;    // время на еду

    public SemaphoreEngine(int n, long eatTime, long thinkTime) {
        this.n = n;
        this.eatTime = eatTime;
        this.thinkTime = thinkTime;
    }

    public void newRun() {
        /*создаем официанта с допустимым количеством разрешений на доступ к ресурсу в количестве (число философов - 1).
        Т.е.одновременно в системе могут быть взяты n - 1 вилок
        это необходимо для избежания дедлока*/
        Semaphore waiter = new Semaphore(n - 1);

        //создаем и заполняем список вилок
        List<Fork> forks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            forks.add(new Fork());
        }

        //определяем нумерацию вилок, которые будут использовать философы
        //философ_0 (0, 1)
        //философ_1 (1, 2)
        //философ_2 (2, 3)
        //философ_3 (3, 4)
        //философ_4 (4, 0)
        AtomicInteger[] eaten = new AtomicInteger[n];
        for (int i = 0; i < n; i++) {
            eaten[i] = new AtomicInteger(0); //кол-во съеденной еды, первоначально 0
            Fork left = forks.get(i);
            Fork right = forks.get((i + 1) % n);

            //у всех философов общий официант и одинаковое время на еду и размышления
            philosophers.add(new Philosopher(waiter, eaten[i], eatTime, thinkTime, left, right));
        }

        //создаем потоки и запускаем
        List<Thread> philosopherThreads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread philosopherThread = new Thread(philosophers.get(i));
            philosopherThreads.add(philosopherThread);
            philosopherThread.start();
        }


        Util.waitMillis(1000L * SECONDS);
        philosopherThreads.stream().forEach(Thread::interrupt);

        Util.waitMillis(1000);
        Util.printResult(eaten);
    }
}
