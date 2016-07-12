package by.epam.dinerphilosopher;

/**
 * Created by Olga Shahray on 12.07.2016.
 */
/*Проблема обедающих философов предполагает, что несколько потоком для выполнения своей работы должны захватить 2
разделяемых ресурса. Организовать работу системы требуется таким образом, чтобы
1. избежать deadlock
2. избежать livelock
3. производительность потоков была равномерной или близкой к этому

Пример: перевод денег со счета на счет, пул соединений с базами данных.
Если некоторым потокам необходим доступ одновременно к двум соединениям*/

public class Main {
    public static int N = 5;

    public static void main(String[] args) throws InterruptedException {
        run(0, 0); //философы 0 секунду едят, 0 думают
        run(0, 1); //философы 0 секунд едят, 1 думают
        run(1, 0); //философы 1 секунду едят, 0 думают
        run(1, 1); //философы 1 секунду едят, 1 думают
    }

    private static void run(long eatTime, long thinkTime) {
        System.out.println("eat= " + eatTime + "ms, think = " + thinkTime + "ms");
        new SemaphoreEngine(N, eatTime, thinkTime).newRun();
    }
}
