package by.epam.port.port;

import by.epam.port.exception.PortException;
import by.epam.port.ship.Ship;
import by.epam.port.warehouse.Container;
import by.epam.port.warehouse.Warehouse;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Port {

    private final static Logger logger = Logger.getRootLogger();

    private BlockingQueue<Berth> berthQueue;// очередь причалов
    private Warehouse portWarehouse; // хранилище порта
    private ConcurrentMap<Ship, Berth> usedBerths; // какой корабль у какого причала стоит
    private Lock berthLocker; //блокировщик причала

    public Port(int berthSize, int warehouseSize) {

        portWarehouse = new Warehouse(warehouseSize); // создаем пустое хранилище
        berthQueue = new ArrayBlockingQueue<>(berthSize);// создаем очередь причалов
        for (int i = 0; i < berthSize; i++) { // заполняем очередь причалов непосредственно самими причалами
            berthQueue.add(new Berth(i, portWarehouse));
        }
        usedBerths = new ConcurrentHashMap<>(); // создаем объект, который будет хранить данные, у какого причала какой корабль
        berthLocker = new ReentrantLock();
        logger.debug("Порт создан.");
    }

    public void setContainersToWarehouse(List<Container> containerList){
        portWarehouse.addContainer(containerList);
    }

    public boolean lockBerth(Ship ship) {
        Berth berth;
        try {
            berth = berthQueue.take();
            usedBerths.put(ship, berth); // записываем, что данный корабль занял этот причал
            berthLocker.lock();
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unlockBerth(Ship ship) {
        Berth berth = usedBerths.get(ship);
        berthQueue.add(berth);
        usedBerths.remove(ship);
        berthLocker.unlock();
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
