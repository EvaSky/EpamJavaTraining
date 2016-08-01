package by.epam.port.port;

import by.epam.port.exception.PortException;
import by.epam.port.ship.Ship;
import by.epam.port.warehouse.Container;
import by.epam.port.warehouse.Warehouse;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Port {

    private final static Logger logger = Logger.getRootLogger();

    private BlockingQueue<Berth> berthQueue;// очередь причалов
    private Warehouse portWarehouse; // хранилище порта
    private ConcurrentMap<Ship, Berth> usedBerths; // какой корабль у какого причала стоит

    public Port(int berthSize, int warehouseSize) {

        portWarehouse = new Warehouse(warehouseSize); // создаем пустое хранилище
        berthQueue = new ArrayBlockingQueue<>(berthSize);// создаем очередь причалов
        for (int i = 0; i < berthSize; i++) { // заполняем очередь причалов непосредственно самими причалами
            berthQueue.add(new Berth(i, portWarehouse));
        }
        usedBerths = new ConcurrentHashMap<>(); // создаем объект, который будет хранить данные, у какого причала какой корабль
        logger.debug("Порт создан.");
    }
    //поместить контейнеры на склад порта
    public void setContainersToWarehouse(List<Container> containerList){
        portWarehouse.addContainer(containerList);
    }

    //блокируем причал кораблем. В случае успеха - true
    public boolean lockBerth(Ship ship) {
        Berth berth;
        try {
            // корабль займет свободный причал, если свободных причалов нет, корабль (поток) будет ждать
            berth = berthQueue.take();
            usedBerths.put(ship, berth); // записываем, что данный корабль занял этот причал
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    //снимаем блокировку с причала. Освобожденный причал добавлям в очередь причалов
    public boolean unlockBerth(Ship ship) throws PortException {
        Berth berth = getBerth(ship);
        //данные два метода не залочены, т.к. нет цели получить актуальное состояние usedBerth в каждую единицу времени.
        //на правильность работы приложения это не влияет. Однако если надо иметь актуальное состояние "журнала", то необходима блокировка
        berthQueue.add(berth);
        usedBerths.remove(ship);
        return true;
    }

    public Berth getBerth(Ship ship) throws PortException {
        Berth berth = usedBerths.get(ship);
        if (berth == null) {
            throw new PortException("Try to use Berth without blocking.");
        }
        return berth;
    }
}
