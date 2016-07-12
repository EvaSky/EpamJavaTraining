package by.epam.port.ship;

import by.epam.port.exception.PortException;
import by.epam.port.port.Berth;
import by.epam.port.port.Port;

import by.epam.port.warehouse.Container;
import by.epam.port.warehouse.Warehouse;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Ship implements Runnable{

    private final static Logger logger = Logger.getRootLogger();
    private volatile boolean stopShip = false;

    private String name;
    private Port port;
    private Warehouse shipWarehouse;

    public Ship() {
    }

    public Ship(String name, Port port, int shipWarehouseSize) {
        this.name = name;
        this.port = port;
        this.shipWarehouse = new Warehouse(shipWarehouseSize);
    }

    //заполение трюма корабля контейнерами
    public void setContainersToWarehouse(List<Container> containerList) {
        shipWarehouse.addContainer(containerList);
    }

    public void stopShip() {
        stopShip = true;
    }

    public void run() {
        try {
            while (!stopShip) {
                atSea();
                inPort();
            }
        } catch (InterruptedException e) {
            logger.error("С кораблем случилась неприятность и он уничтожен.", e);
        } catch (PortException e) {
            logger.error("Кораблю не соответствует ни один причал", e);
        }
    }
    //в море корабль плавает определенное время
    private void atSea() throws InterruptedException {
        Thread.sleep(1000);
    }

    //действия корабля в порту
    private void inPort() throws PortException, InterruptedException {
        boolean isLockedBerth = false;
        Berth berth = null;
        try {
            isLockedBerth = port.lockBerth(this); //показывает, смог ли корабль занять причал

            if (isLockedBerth) {  //да, причал занял. варианты действий - загрузка, выгрузка
                berth = port.getBerth(this);
                ShipAction action = getNextAction();
                executeAction(action, berth);
            } else {
                logger.info("Кораблю " + name + " отказано в швартовке к причалу ");
            }
        } finally {  //по окончании всех действий снимаем блокировку с причала, если он был заблокирован
            if (isLockedBerth) {
                port.unlockBerth(this);
            }
        }
    }

    private void executeAction(ShipAction action, Berth berth) throws InterruptedException {
        switch (action) {
            case LOAD_TO_PORT:
                loadToPort(berth);
                break;
            case LOAD_FROM_PORT:
                loadFromPort(berth);
                break;
        }
    }
    //выгружаем контейнеры в порт
    private boolean loadToPort(Berth berth) throws InterruptedException {

        //количество контейнеров, которое хотим выгрузить в порт. НЕ БОЛЬШЕ НАХОДЯЩИХСЯ НА КОРАБЛЕ!
        int containersNumberToMove = containersCount(shipWarehouse.getRealSize());
        boolean result = false;

        logger.debug("Корабль " + name + " хочет выгрузить " + containersNumberToMove
                + " контейнеров на склад порта.");
        //выгрузка контейнеров
        result = berth.add(shipWarehouse, containersNumberToMove);

        if (!result) {
            logger.debug("Недостаточно места на складе порта для выгрузки кораблем "
                    + name + " " + containersNumberToMove + " контейнеров.");
        } else {
            logger.debug("Корабль " + name + " выгрузил " + containersNumberToMove
                    + " контейнеров в порт.");

        }
        return result;
    }

    //загружаем корабль с хранилища порта
    private boolean loadFromPort(Berth berth) throws InterruptedException {

        //количество контейнеров, которое хотим загрузить на корабль. НЕ БОЛЬШЕ СВОБОДНОГО МЕСТА НА КОРАБЛЕ!
        int containersNumberToMove = containersCount(shipWarehouse.getFreeSize());
        boolean result = false;

        logger.debug("Корабль " + name + " хочет загрузить " + containersNumberToMove
                + " контейнеров со склада порта.");
        //загрузка корабля контейнерами
        result = berth.get(shipWarehouse, containersNumberToMove);

        if (result) {
            logger.debug("Корабль " + name + " загрузил " + containersNumberToMove
                    + " контейнеров из порта.");
        } else {
            logger.debug("Недостаточно места на на корабле " + name + " для погрузки "
                    + containersNumberToMove + " контейнеров из порта.");
        }

        return result;
    }

    //случайное число контейнеров, не превышаюшее заданную величину
    private int containersCount(int maxCount) {
        Random random = new Random();
        return random.nextInt(maxCount);
    }


    private ShipAction getNextAction() {
        Random random = new Random();
        int value = random.nextInt(4000);
        if (value < 2000) {
            return ShipAction.LOAD_TO_PORT;
        } else {
            return ShipAction.LOAD_FROM_PORT;
        }
    }


    enum ShipAction {
        LOAD_TO_PORT, LOAD_FROM_PORT
    }
}
